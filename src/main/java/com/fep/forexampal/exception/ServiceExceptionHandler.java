package com.fep.forexampal.exception;

import com.fep.forexampal.common.model.ServiceErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import static com.fep.forexampal.common.enums.ErrorMessage.MAX_UPLOAD_SIZE_ERROR;

@RestControllerAdvice
@RequiredArgsConstructor
public class ServiceExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceErrorResponse handle(SystemException exception) {
        return buildErrorResponse(exception);
    }

    @ExceptionHandler(CommonMediaException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServiceErrorResponse handle(CommonMediaException exception) {
        return buildErrorResponse(exception);
    }

    @ExceptionHandler(StudentTaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceErrorResponse handle(StudentTaskNotFoundException exception) {
        return buildErrorResponse(exception);
    }

    @ExceptionHandler(UnsupportedMediaTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceErrorResponse handle(UnsupportedMediaTypeException e) {
        return buildErrorResponse(e);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceErrorResponse handle(MaxUploadSizeExceededException e) {
        String message = messageSource.getMessage(MAX_UPLOAD_SIZE_ERROR.getMessage(), new String[] {}, LocaleContextHolder.getLocale());
        return ServiceErrorResponse.builder().message(message).code(MAX_UPLOAD_SIZE_ERROR.getCode()).build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceErrorResponse handle(UserNotFoundException e) {
        return buildErrorResponse(e);
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceErrorResponse handle(SubjectNotFoundException e) {
        return buildErrorResponse(e);
    }

    @ExceptionHandler(ClassQuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceErrorResponse handle(ClassQuestionNotFoundException e) {
        return buildErrorResponse(e);
    }

    @ExceptionHandler(ClassQuestionAnswerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServiceErrorResponse handle(ClassQuestionAnswerNotFoundException e) {
        return buildErrorResponse(e);
    }

    private ServiceErrorResponse buildErrorResponse(BaseException e) {
        String message = messageSource.getMessage(e.getMessage(), e.getArgs(), LocaleContextHolder.getLocale());
        return ServiceErrorResponse.builder().message(message).code(e.getCode()).build();
    }
}
