package com.fep.forexampal.service.media;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fep.forexampal.common.utils.MediaUtils;
import com.fep.forexampal.dto.ImageSaveRequest;
import com.fep.forexampal.exception.SystemException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import static com.fep.forexampal.common.enums.ErrorMessage.SYSTEM_ERROR;

@Service
@RequiredArgsConstructor
public class ImageService {

    private static final Path rootPath = Paths.get("images");
    private static final String BASE_PATH = "https://imageserver.bisorumvarapp.com/imagecloud";

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

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

    public String saveAndGetImagePath(MultipartFile file) {
        try {
            byte[] encode = Base64.getEncoder().encode(file.getBytes());
            String encodedStr = new String(encode);
            String uriString = UriComponentsBuilder.fromHttpUrl(BASE_PATH).pathSegment("save-image").toUriString();
            ResponseEntity<String> response = restTemplate.postForEntity(uriString, new ImageSaveRequest(encodedStr), String.class);
            return handleResponse(response);
        } catch (IOException e) {
            throw new SystemException(SYSTEM_ERROR.getMessage(), SYSTEM_ERROR.getCode());
        }
    }

    private String handleResponse(ResponseEntity<String> response) {
        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        return response.getBody();
    }

}
