package com.fep.forexampal.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessage {

    SYSTEM_ERROR(-1, "system.error"),
    MEDIA_ERROR(1, "media.common.error"),
    STUDENT_TASK_NOT_FOUND(2, "student.task.not.found.error"),
    UNSUPPORTED_MEDIA_TYPE_ERROR(3, "validation.unsupported.media.type.error"),
    MAX_UPLOAD_SIZE_ERROR(4, "validation.max.upload.size.error"),
    USER_NOT_FOUND(5, "user.not.found.error"),
    SUBJECT_NOT_FOUND(6, "subject.not.found.error"),
    CLASS_QUESTION_NOT_FOUND_ERROR(7, "class.question.not.found.error"),
    CLASS_QUESTION_ANSWER_NOT_FOUND_ERROR(8, "class.question.answer.not.found.error");

    @Getter
    private final int code;

    @Getter
    private final String message;
}
