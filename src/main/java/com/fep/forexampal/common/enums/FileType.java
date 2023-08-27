package com.fep.forexampal.common.enums;

import lombok.Getter;

@Getter
public enum FileType {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif"),
    PDF("pdf"),
    HTML("html"),
    SVG("svg"),
    CSV("csv"),
    XLS("xls"),
    XLSX("xlsx");

    private final String format;

    FileType(String format) {
        this.format = format;
    }

    public static String getExtension(FileType fileType) {
        return "." + fileType.getFormat();
    }
}
