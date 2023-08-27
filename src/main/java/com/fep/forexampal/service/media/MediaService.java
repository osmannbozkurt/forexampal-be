package com.fep.forexampal.service.media;

import com.fep.forexampal.common.model.MediaContainer;
import com.fep.forexampal.common.utils.MediaUtils;
import com.fep.forexampal.exception.CommonMediaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.fep.forexampal.common.enums.ErrorMessage.MEDIA_ERROR;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaService {

    private final DocumentService documentService;
    private final ImageService imageService;

    public MediaContainer getMediaResource(String fileName) {
        try {
            return MediaContainer.builder()
                    .type(FilenameUtils.getExtension(fileName))
                    .source(loadResource(fileName))
                    .build();
        } catch (Exception e) {
            log.error("Occurred an error while loading document from resource. File name is {}", fileName, e);
            throw new CommonMediaException(MEDIA_ERROR.getMessage(), MEDIA_ERROR.getCode());
        }
    }

    private byte[] loadResource(String fileName) throws IOException {
        if (MediaUtils.isImage(fileName)) {
            return imageService.loadResource(fileName);
        }
        return documentService.loadResource(fileName);
    }
}
