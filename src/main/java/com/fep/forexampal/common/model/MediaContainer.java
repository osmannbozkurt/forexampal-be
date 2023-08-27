package com.fep.forexampal.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaContainer {

    private String type;
    private String uri;
    private byte[] source;

    public MediaType of() {
        return switch (type) {
            case "pdf" -> MediaType.APPLICATION_PDF;
            case "ppt", "pptx", "doc", "docx", "PPT", "PPTX", "DOC", "DOCX" -> MediaType.APPLICATION_OCTET_STREAM;
            default -> MediaType.IMAGE_JPEG;
        };
    }
}
