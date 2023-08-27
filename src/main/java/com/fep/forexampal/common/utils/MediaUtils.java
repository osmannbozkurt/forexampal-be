package com.fep.forexampal.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class MediaUtils {

    private static final List<String> imageExtensions = Arrays.asList("jpg", "jpeg", "png", "JPG", "JPEG", "PNG");
    private static final String IMAGE_BASE_PATH = "http://localhost:9001/";

    public static boolean isImage(String fileName) {
        String type = FilenameUtils.getExtension(fileName);
        return imageExtensions.contains(type);
    }

    public static String generateFileName(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);
        String uniqueId = UUID.randomUUID().toString().replace('-', '_');
        return uniqueId + "." + extension;
    }

    public static String generateFileUrl(String imagePath) {
        return IMAGE_BASE_PATH + imagePath;
    }
}
