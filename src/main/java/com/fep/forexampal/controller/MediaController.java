package com.fep.forexampal.controller;

import com.fep.forexampal.common.model.MediaContainer;
import com.fep.forexampal.service.media.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> loadDocument(@PathVariable String fileName) {
        MediaContainer mediaResource = mediaService.getMediaResource(fileName);
        return ResponseEntity.ok().contentType(mediaResource.of()).body(mediaResource.getSource());
    }

    @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<byte[]> loadImage(@PathVariable String fileName) {
        MediaContainer container = mediaService.getMediaResource(fileName);
        return ResponseEntity.ok().contentType(container.of()).body(container.getSource());
    }
}
