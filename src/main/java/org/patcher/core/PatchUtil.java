package org.patcher.core;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import org.patcher.dto.models.FontFamily;
import org.patcher.dto.models.FontStyle;
import org.patcher.dto.viewmodels.ActionDTO;
import org.patcher.dto.models.FontFamilies;
import org.patcher.dto.viewmodels.FontDTO;
import org.patcher.dto.viewmodels.FontFamilyDTO;
import org.patcher.enums.FontTypeEnum;
import org.patcher.utility.AppLogger;
import org.patcher.utility.Constants;
import org.patcher.utility.FileUtil;
import org.patcher.utility.Util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class PatchUtil {
    public static ActionDTO createPatch(List<String> filePaths, String newFileName, String appName, String customName) {
        ActionDTO result = new ActionDTO();
        result.setIsSuccessful(false);
        result.setMessage(Constants.MESSAGES.DEFAULT_PATCH_CREATION_FAILED_MESSAGE.getValue());
        try {
            AppContext.createdPatchName = newFileName;
            newFileName = String.join(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue(), PropManager.getDefaultPatchesFolder(), newFileName);
            addFilesToZip(filePaths, newFileName, appName, customName);
            result.setIsSuccessful(true);
            result.setMessage(PropManager.getPatchCreateSuccessMsg());
        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.createPatch", e);
        }
        return result;
    }

    public static void addFilesToZip(List<String> filePaths, String zipFilePath, String appName, String customName) throws Exception {
        String userDir = System.getProperty("user.dir");
        Path directoryPath = Paths.get(userDir, PropManager.getDefaultPatchesFolder());
        Files.createDirectories(directoryPath);
        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilePath)))) {
            for (String filePath : filePaths) {
                File file = new File(filePath);
                if (file.exists()) {
                    String relativePath = getRelativePathToApp(filePath, appName);
                    relativePath = replaceAppNameIfCustomName(relativePath, appName, customName);

                    ZipEntry zipEntry = new ZipEntry(relativePath);
                    zipOut.putNextEntry(zipEntry);

                    try (FileInputStream fis = new FileInputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zipOut.write(buffer, 0, length);
                        }
                    }
                    zipOut.closeEntry();
                } else {
                    throw new Exception("File added in patch is not found");
                }
            }
        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.addFilesToZip", e);
            throw e;
        }
    }

    public static String getRelativePathToApp(String filePath, String appName) {
        String relativePath = filePath;
        if (Util.areStringsValid(filePath)) {
            relativePath = filePath.substring(filePath.indexOf(appName));
        }
        return relativePath;
    }

    public static String replaceAppNameIfCustomName(String filePath, String appName, String customName) {
        String newFilePath = filePath;
        if (Util.areStringsValid(filePath, appName, customName)) {
            newFilePath = filePath.replace(appName, customName);
        }
        return newFilePath;
    }

    public static List<String> readPatch(String patchPath, boolean selectedJarFontExt) {
        List<String> patchFiles = new ArrayList<>();
        try (ZipFile zipFile = new ZipFile(patchPath)) {
            // Get the entries (files) inside the zip
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            // Iterate through the entries and print the names
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if(selectedJarFontExt && entry.getName().toLowerCase()
                        .endsWith(Constants.FONT_EXTENSION_CONSTANTS.FONTS_XML.getValue())) {
                    loadFontsXml(zipFile, entry);
                }
                patchFiles.add(entry.getName());
            }

        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.readPatch", e);
        }
        return patchFiles;
    }

    public static ActionDTO verifyPatch(String patchDir, String appDir, boolean matchAppRoot) {
        ActionDTO actionDTO = new ActionDTO();
        actionDTO.setIsSuccessful(false);
        actionDTO.setMessage(Constants.MESSAGES.PATCH_VERIFY_FAILED_MESSAGE.getValue());
        try {
            List<String> patchFiles = readPatch(patchDir, false);
            actionDTO = verifyPatchFiles(patchFiles, appDir, matchAppRoot);
            if(actionDTO.getIsSuccessful()){
                actionDTO.setMessage(Constants.MESSAGES.PATCH_VERIFIED_SUCCESS_MESSAGE.getValue());
            }
        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.verifyPatch", e);
            actionDTO.setMessage(Constants.MESSAGES.PATCH_VERIFY_FAILED_EXCEPTION_MESSAGE.getValue());
        }
        return actionDTO;
    }

    private static ActionDTO verifyPatchFiles(List<String> patchFiles, String appPath, boolean matchAppRoot) {
        ActionDTO actionDTO = new ActionDTO();
        actionDTO.setIsSuccessful(true);
        try {
            if (Util.isListNotEmpty(patchFiles)) {
                String appName = FileUtil.getLastFolderFromPath(appPath);
                for (String filePath : patchFiles) {
                    if (matchAppRoot) {
                        String patchFileRoot = FileUtil.getFirstFolderFromPatchFilePath(filePath);
                        if (!appName.equalsIgnoreCase(patchFileRoot)) {
                            actionDTO.messageList.add("Root path not matched with " + filePath);
                            actionDTO.setIsSuccessful(false);
                        }
                    }
                    String patchFilePath = FileUtil.getPathExcludingLastFileOrFolder(filePath);
                    String appDirFilePath = String.join(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue(), appPath, patchFilePath);
                    if (!folderExists(appDirFilePath)) {
                        actionDTO.messageList.add("Path for file not matched in app " + filePath);
                        actionDTO.setIsSuccessful(false);
                    }
                }
            }
        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.verifyPatchFiles", e);
            throw e;
        }
        return actionDTO;
    }

    private static boolean folderExists(String folderPath) {
        try {
            Path path = Paths.get(folderPath);
            return Files.exists(path);
        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.folderExists", e);
            return false;
        }
    }


    public static ActionDTO applyPatch(String appPath, String patchPath, boolean matchRootDir) {
        ActionDTO actionDTO = new ActionDTO();
        try {
            extractPatch(appPath, patchPath, matchRootDir);
            actionDTO.setIsSuccessful(true);
            actionDTO.setMessage(Constants.MESSAGES.PATCH_APPLY_SUCCESS_MESSAGE.getValue());
        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.applyPatch", e);
            actionDTO.setMessage(Constants.MESSAGES.PATCH_APPLY_FAIL_EXCEPTION_MESSAGE.getValue());
            actionDTO.setIsSuccessful(false);
        }
        return actionDTO;
    }

    public static void extractPatch(String appPath, String patchPath, boolean matchRootDir) throws Exception {
        try {
            File patchFile = new File(patchPath);

            if (!patchFile.exists()) {
                throw new Exception("Patch file not found at: " + patchPath);
            }

            try (ZipFile zipFile = new ZipFile(patchPath)) {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();

                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    String entryName = entry.getName();
                    String targetPath;
                    if (matchRootDir) {
                        targetPath = String.join(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue(), FileUtil.getPathExcludingLastFileOrFolder(appPath), entryName);
                    } else {
                        targetPath = String.join(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue(), appPath, FileUtil.getPathExcludingFirstFolder(entryName));
                    }

                    File targetFile = new File(targetPath);
                    if (entry.isDirectory()) {
                        if (!targetFile.exists()) {
                            targetFile.mkdirs();
                        }
                    } else {
                        File parentDir = targetFile.getParentFile();
                        if (parentDir != null && !parentDir.exists()) {
                            parentDir.mkdirs();
                        }

                        try (InputStream inputStream = zipFile.getInputStream(entry);
                             OutputStream outputStream = Files.newOutputStream(targetFile.toPath())) {
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.extractPatch", e);
            throw e;
        }
    }

    public static ActionDTO verifyFontExtension(String extensionPath) {
        ActionDTO actionDTO = new ActionDTO();
        try{
            boolean verified = false;
            List<String> fileList = readPatch(extensionPath, true);
            if(Util.isListNotEmpty(fileList)) {
                verified = fileList.contains(Constants.FONT_EXTENSION_CONSTANTS.PROPERTIES_FILE.getValue());

                verified = verified && fileList.stream()
                        .anyMatch(file -> (file.toLowerCase().startsWith("fonts/family") &&
                                file.toLowerCase().endsWith(Constants.FONT_EXTENSION_CONSTANTS.FONTS_XML.getValue()))
                        );

                verified = verified && fileList.stream()
                        .anyMatch(file -> (file.toLowerCase().startsWith("fonts/family") &&
                                file.toLowerCase().endsWith(Constants.FILE_CONSTANTS.TTF_FILE_TYPE.getValue())));
            }
            actionDTO.setIsSuccessful(verified);
            actionDTO.setMessage("Verification of font extension is successful.");
        } catch (Exception e){
            AppLogger.logSevere("Error in PatchUtil.verifyFontExtension", e);
            actionDTO.setIsSuccessful(false);
            actionDTO.setMessage("Verification of font extension failed, check logs for more details.");
        }
        return actionDTO;
    }

    public static ActionDTO addFontsInExtension(String extensionPath) {
        ActionDTO actionDTO = new ActionDTO();
        try {
            actionDTO.setMessage("Adding font extensions is unsucessful");
            String fontExtTempFolder = "font_extensions\\tempfiles";

            String extName = FileUtil.getLastFolderFromPath(extensionPath);


            extName = extName.substring(0, extName.lastIndexOf(Constants.STRING_SEPARATOR.DOT.getValue()));

            String fontExtTempFolderPath = FileUtil.joinPath(fontExtTempFolder, extName);

            extractPatch(fontExtTempFolderPath, extensionPath, false);

            String fontFolder = getFontFolderPathFromExtension(extensionPath);

            String fontExtFontFolderTempPath = FileUtil.joinPath(fontExtTempFolderPath, fontFolder);

            FontFamilyDTO newFontFamily = AppContext.newFontFamily;

            //copy ttf files
            List<String> ttfList = newFontFamily.fontDTOList.stream().map(FontDTO::getTtfFile)
                    .filter(Util::areStringsValid).collect(Collectors.toList());

            for (int i = 0; i < ttfList.size(); i++) {
                Path sourcePath = Paths.get(ttfList.get(i)).toAbsolutePath();
                String destStrPath = FileUtil.joinPath(fontExtFontFolderTempPath, FileUtil.getLastFolderFromPath(sourcePath.toString()));
                Path destPath = Paths.get(destStrPath).toAbsolutePath();

                String[] destStrPaths = FileUtil.breakPath(destStrPath);

                ttfList.set(i, String.join(Constants.STRING_SEPARATOR.PATH_SEPARATOR_BACK_SLASH.getValue(), Arrays.copyOfRange(destStrPaths, 3, destStrPaths.length)));

                try (FileChannel sourceChannel = FileChannel.open(sourcePath, StandardOpenOption.READ);
                     FileChannel destChannel = FileChannel.open(destPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                    destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
                    AppLogger.logInfo("--------Copied-------- from " + sourcePath + " to " + destPath);

                } catch (Exception e) {
                    AppLogger.logSevere("Error in PatchUtil.addFontsInExtension", e);
                }
            }

            //copy ends


            //fonts xml

            if (Util.isListNotEmpty(ttfList)) {
                //create dto
                FontFamily newFontFam = new FontFamily();
                newFontFam.setName(newFontFamily.getFontFamilyName());

                for (int i = 0; i < ttfList.size(); i++) {
                    FontDTO fontDTO = newFontFamily.fontDTOList.get(i);
                    FontStyle newFontStyle = new FontStyle();
                    newFontStyle.setTtf(FileUtil.makePathCompatible(ttfList.get(i), false));
                    newFontStyle.setPdf(fontDTO.getPdfFontName());
                    if (FontTypeEnum.REGULAR.equals(fontDTO.getType())) {
                        newFontFam.setNormal(newFontStyle);
                    } else if (FontTypeEnum.BOLD.equals(fontDTO.getType())) {
                        newFontFam.setBold(newFontStyle);
                    } else if (FontTypeEnum.ITALIC.equals(fontDTO.getType())) {
                        newFontFam.setItalic(newFontStyle);
                    } else if (FontTypeEnum.BOLD_ITALIC.equals(fontDTO.getType())) {
                        newFontFam.setBoldItalic(newFontStyle);
                    }
                }

                String fontsXMLFolder = FileUtil.getPathExcludingLastFileOrFolder(fontExtFontFolderTempPath);
                String fontsXMLPath = FileUtil.joinPath(fontsXMLFolder, "fonts.xml");
                newFontFam.setPdfEmbedded(newFontFamily.getIsEmbedded() ? "true" : "false");
                AppContext.currentFontFamilies.getFontFamilies().add(newFontFam);
                makeFontsXML(AppContext.currentFontFamilies, fontsXMLPath, true);
            }

            //fonts xml ends
            String newFontExt = FileUtil.joinPath("font_ext_jars", extName);
            JarPacker.packToJar(new File(fontExtTempFolderPath), new File(newFontExt));

            actionDTO.setMessage("Adding font extensions is done successfully");
            actionDTO.setIsSuccessful(true);

        }catch (Exception e){
            AppLogger.logSevere("Error in PatchUtil.addFontsInExtension", e);
            actionDTO.setMessage("Adding font extensions failed, check logs for more details");
            actionDTO.setIsSuccessful(false);
        } finally {
            //clean up
            AppContext.currentFontFamilies = null;
            AppContext.newFontFamily = null;
            FileUtil.deleteFolder(new File("font_extensions"));
        }
        return actionDTO;
    }

    public static String getFontFolderPathFromExtension(String extensionPath) {
        String path = "";
        try{
            List<String> filesInExtension = readPatch(extensionPath, true);

            if(Util.isListNotEmpty(filesInExtension)) {
                String fontFile = filesInExtension.stream()
                        .filter(file -> file.endsWith(Constants.FILE_CONSTANTS.TTF_FILE_TYPE.getValue()))
                        .findFirst()
                        .orElse(""); // or handle it in another way
                if(Util.areStringsValid(fontFile) && fontFile.contains("/")) {
                    path = fontFile.substring(0, fontFile.lastIndexOf("/"));
                }
            }

        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.getFontFamilyNameFromExtension", e);
        }
        return path;
    }

    private static void loadFontsXml(ZipFile zipFile, ZipEntry entry) {
        try (InputStream inputStream = zipFile.getInputStream(entry)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(FontFamilies.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            FontFamilies fontFamilies = (FontFamilies) unmarshaller.unmarshal(inputStream);
            AppContext.currentFontFamilies = fontFamilies;
            System.out.println(fontFamilies);
        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.loadFontsXml", e);
        }
    }

    public static String makeFontsXML(FontFamilies fontFamilies, String filePath, boolean writeToFile) {
        String xmlString = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FontFamilies.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            marshaller.setProperty("com.sun.xml.internal.bind.characterEscapeHandler",
                    (CharacterEscapeHandler) (ch, start, length, isAttVal, writer1) -> writer1.write(ch, start, length));

            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

            if(writeToFile) {
                marshaller.marshal(fontFamilies, new File(filePath));
            } else {
                StringWriter stringWriter = new StringWriter();
                marshaller.marshal(fontFamilies, stringWriter);

                xmlString = stringWriter.toString();
            }
        } catch (Exception e) {
            AppLogger.logSevere("Error in PatchUtil.saveFontsXml", e);
        }
        return xmlString;
    }
}