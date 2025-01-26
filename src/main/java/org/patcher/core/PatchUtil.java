package org.patcher.core;

import org.patcher.dto.ActionDTO;
import org.patcher.utility.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class PatchUtil {
    public static String createPatch(List<String> filePaths, String newFileName, String appName, String customName) {
        String output = "Patch successfully created in folder patches";
        try {
            newFileName = "patches/" + newFileName;
            addFilesToZip(filePaths, newFileName, appName, customName);
        } catch (Exception e) {
            output = "Patch can not be created: " + e.getMessage();
        }
        return output;
    }

    public static void addFilesToZip(List<String> filePaths, String zipFilePath, String appName, String customName) throws Exception {
        String userDir = System.getProperty("user.dir");
        Path directoryPath = Paths.get(userDir, "patches");
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
                    System.err.println("File does not exist: " + filePath);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public static List<String> readPatch(String patchPath) {
        List<String> patchFiles = new ArrayList<>();
        try (ZipFile zipFile = new ZipFile(patchPath)) {
            // Get the entries (files) inside the zip
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            // Iterate through the entries and print the names
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                patchFiles.add(entry.getName());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return patchFiles;
    }

    public static ActionDTO verifyPatch(String patchDir, String appDir, boolean matchAppRoot) {
        ActionDTO actionDTO = new ActionDTO();
        try {
            List<String> patchFiles = readPatch(patchDir);
            actionDTO = verifyPatch(patchFiles, appDir, matchAppRoot);
            if (!actionDTO.getSuccessful()) {
                return actionDTO;
            }
            actionDTO.setSuccessful(true);
            actionDTO.setMessage("Patch verified");
        } catch (Exception e) {
            actionDTO.setSuccessful(false);
            actionDTO.setMessage(e.getMessage());
        }
        return actionDTO;
    }

    private static ActionDTO verifyPatch(List<String> patchFiles, String appPath, boolean matchAppRoot) {
        ActionDTO actionDTO = new ActionDTO();
        actionDTO.setSuccessful(true);
        try {
            if (Util.isListNotEmpty(patchFiles)) {
                String appName = Util.getLastFolderFromPath(appPath);
                for (String filePath : patchFiles) {
                    if (matchAppRoot) {
                        String patchFileRoot = Util.getFirstFolderFromPatchFilePath(filePath);
                        if (!appName.equalsIgnoreCase(patchFileRoot)) {
                            actionDTO.messageList.add("Root path not matched with " + filePath);
                            actionDTO.setSuccessful(false);
                        }
                    }
                    String patchFilePath = Util.getPatchFilePathExcludingRootAndFileName(filePath);
                    String appDirFilePath = appPath + "\\" + patchFilePath;
                    if (!folderExists(appDirFilePath)) {
                        actionDTO.messageList.add("Path for file not matched in app " + filePath);
                        actionDTO.setSuccessful(false);
                    }
                }
            }
        } catch (Exception e) {
            actionDTO.setMessage("Error: " + e.getMessage());
            actionDTO.setSuccessful(false);
        }
        return actionDTO;
    }

    private static boolean folderExists(String folderPath) {
        try {
            Path path = Paths.get(folderPath);
            return Files.exists(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static ActionDTO applyPatch(String appPath, String patchPath, boolean matchRootDir) {
        ActionDTO actionDTO = new ActionDTO();
        try {
            boolean done = extractPatch(appPath, patchPath, matchRootDir);
            if (done) {
                actionDTO.setSuccessful(true);
                actionDTO.setMessage("Patch applied successfully");
            } else {
                throw new Exception("Patch not applied");
            }
        } catch (Exception e) {
            actionDTO.setMessage("Patch can not be applied");
            actionDTO.setSuccessful(false);
        }
        return actionDTO;
    }

    public static boolean extractPatch(String appPath, String patchPath, boolean matchRootDir) throws Exception {
        boolean success = false;
        try {
            File patchFile = new File(patchPath);

            if (!patchFile.exists()) {
                throw new FileNotFoundException("Patch file not found at: " + patchPath);
            }

            try (ZipFile zipFile = new ZipFile(patchPath)) {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();

                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    String entryName = entry.getName();

                    // If ignoring the root, remove the first level (root directory)
                    String targetPath;
                    if (matchRootDir) {
                        targetPath = appPath + File.separator + entryName;
                    } else {
                        targetPath = appPath + File.separator + entryName.substring(entryName.indexOf(File.separator) + 1);
                    }

                    File targetFile = new File(targetPath);
                    if (entry.isDirectory()) {
                        if (!targetFile.exists()) {
                            targetFile.mkdirs();
                        }
                    } else {
                        // Create parent directories if necessary
                        File parentDir = targetFile.getParentFile();
                        if (parentDir != null && !parentDir.exists()) {
                            parentDir.mkdirs();
                        }

                        // Extract the file
                        try (InputStream inputStream = zipFile.getInputStream(entry);
                             OutputStream outputStream = Files.newOutputStream(targetFile.toPath())) {
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        } catch (IOException e) {
                            System.out.println("Error extracting file: " + entryName);
                            throw e;
                        }
                    }
                }

                success = true;
            } catch (IOException e) {
                System.out.println("Error processing the zip file: " + e.getMessage());
                throw e;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            success = false;
        }
        return success;
    }
}