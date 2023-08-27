package com.fep.forexampal.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MediaFileValidator.class})
public @interface ValidMedia {
    Class<? extends Payload> [] payload() default{};
    Class<?>[] groups() default {};
    String message() default "Only pdf, doc, ppt, jpeg, jpg, png are allowed";
}
