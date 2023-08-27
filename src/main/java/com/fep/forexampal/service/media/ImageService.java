package com.fep.forexampal.service.media;

import com.fep.forexampal.common.utils.MediaUtils;
import com.fep.forexampal.exception.SystemException;
import jakarta.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.fep.forexampal.common.enums.ErrorMessage.SYSTEM_ERROR;

@Service
public class ImageService {

    private static final Path rootPath = Paths.get("images");

    @PostConstruct
    public void init() {
        if (Files.exists(rootPath)) {
            return;
        }
        try {
            Files.createDirectories(rootPath);
        } catch (IOException e) {
            throw new SystemException(e.getMessage(), -1);
        }
    }

    public String uploadAndGetPath(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileName = MediaUtils.generateFileName(originalFilename);
        try (InputStream inputStream = file.getInputStream()) {
            Path destinationPath = rootPath.resolve(fileName).normalize().toAbsolutePath();
            Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            return rootPath + "/" + fileName;
        } catch (IOException e) {
            throw new SystemException(SYSTEM_ERROR.getMessage(), SYSTEM_ERROR.getCode());
        }
    }

    public byte[] loadResource(String fileName) throws IOException {
        Path file = rootPath.resolve(fileName);
        UrlResource resource = new UrlResource(file.toUri());
        return IOUtils.toByteArray(resource.getInputStream());
    }

}
