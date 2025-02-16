package org.patcher.core;

import org.patcher.utility.AppLogger;

import java.io.*;
import java.util.Objects;
import java.util.jar.*;

public class JarPacker {
    public static void packToJar(File sourceDir, File jarFile) throws Exception {
        try {
            File parentDir = jarFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            try (FileOutputStream fos = new FileOutputStream(jarFile);
                 JarOutputStream jos = new JarOutputStream(fos)) {

                addToJar(sourceDir, sourceDir, jos);
            }
        } catch (Exception e) {
            AppLogger.logSevere("Error in JarPacker.packToJar", e);
            throw e;
        }
    }

    private static void addToJar(File rootDir, File sourceFile, JarOutputStream jos) throws Exception {
        if (sourceFile.isDirectory()) {
            for (File file : Objects.requireNonNull(sourceFile.listFiles())) {
                addToJar(rootDir, file, jos);
            }
        } else {
            String entryName = rootDir.toURI().relativize(sourceFile.toURI()).getPath();
            JarEntry jarEntry = new JarEntry(entryName);
            jos.putNextEntry(jarEntry);

            try (FileInputStream fis = new FileInputStream(sourceFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    jos.write(buffer, 0, bytesRead);
                }
            }

            jos.closeEntry();
        }
    }
}
