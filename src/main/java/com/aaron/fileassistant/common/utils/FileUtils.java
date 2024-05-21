package com.aaron.fileassistant.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class FileUtils {

    public static  boolean isMedia(File file) {
        return FileUtils.isImgFile(file) || FileUtils.isVideoFile(file);
    }

    public static boolean isImgFile(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") ||
                extension.equals("gif") || extension.equals("bmp") || extension.equals("webp");
    }

    public static boolean isVideoFile(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("mp4") || extension.equals("avi") || extension.equals("mov") ||
                extension.equals("wmv") || extension.equals("flv") || extension.equals("mkv") ||
                extension.equals("webm") || extension.equals("mpeg") || extension.equals("mpg");
    }

    public static String fileToBase64(File file) throws IOException {
        // 读取文件内容
        byte[] fileBytes = readFileToByteArray(file);
        // 将文件内容编码为Base64字符串
        String base64String = Base64.getEncoder().encodeToString(fileBytes);
        return base64String;
    }

    public static String fileToBase64(File file, int width, int height) throws IOException {
        BufferedImage originalImage = ImageIO.read(file);
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedResizedImage.createGraphics();
        graphics2D.drawImage(resizedImage, 0, 0, null);
        graphics2D.dispose();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedResizedImage, "jpg", outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        byte[] base64Bytes = Base64.getEncoder().encode(imageBytes);
        return new String(base64Bytes);
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        inputStream.read(bytes);
        inputStream.close();
        return bytes;
    }

    public static String formatFileSize(Long fileSize) {
        if (fileSize == null) {
            return "0";
        }
        if (fileSize < 1024) {
            return fileSize + " B";
        } else if (fileSize < 1024 * 1024) {
            return String.format("%.2f KB", (double) fileSize / 1024);
        } else if (fileSize < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", (double) fileSize / (1024 * 1024));
        } else {
            return String.format("%.2f GB", (double) fileSize / (1024 * 1024 * 1024));
        }
    }

}
