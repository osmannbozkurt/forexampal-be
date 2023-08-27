package com.fep.forexampal.validator;

import com.fep.forexampal.exception.UnsupportedMediaTypeException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.fep.forexampal.common.enums.ErrorMessage.UNSUPPORTED_MEDIA_TYPE_ERROR;

@Slf4j
public class MediaFileValidator implements ConstraintValidator<ValidMedia, MultipartFile> {

    private static final List<String> supportedMediaTypes = Arrays.asList("image/jpeg", "image/png", "application/pdf", "application/msword",
            "application/vnd.ms-excel", "application/vnd.ms-powerpoint");

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(file)) {
            return true;
        }
        String contentType = file.getContentType();
        if (!isSupportedMediaType(contentType)) {
            throw new UnsupportedMediaTypeException(UNSUPPORTED_MEDIA_TYPE_ERROR.getMessage(), UNSUPPORTED_MEDIA_TYPE_ERROR.getCode());
        }
        return true;
    }

    private boolean isSupportedMediaType(String type) {
        return supportedMediaTypes.contains(type);
    }
}
