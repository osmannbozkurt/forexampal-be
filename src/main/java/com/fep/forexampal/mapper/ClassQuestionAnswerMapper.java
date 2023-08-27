package com.fep.forexampal.mapper;

import com.fep.forexampal.dto.ClassQuestionAnswerAddRequestDto;
import com.fep.forexampal.dto.ClassQuestionAnswerDto;
import com.fep.forexampal.persistence.entity.ClassQuestionAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface ClassQuestionAnswerMapper {

    @Mapping(target = "taggedUsers", source = "taggedUsers", ignore = true)
    ClassQuestionAnswer toClassQuestionAnswer(ClassQuestionAnswerAddRequestDto addRequestDto);

    @Mapping(target = "answerOwnerName", source = "user.name")
    @Mapping(target = "answerOwnerSurname", source = "user.surname")
    @Mapping(target = "answerDate", source = "createdDate")
    @Mapping(target = "imagePath", expression = "java(classQuestionAnswer.getImage().getImagePath() != null ? " +
            "com.fep.forexampal.common.utils.MediaUtils.generateFileUrl(classQuestionAnswer.getImage().getImagePath()) : null)")
    @Mapping(target = "width", source = "image.width")
    @Mapping(target = "height", source = "image.height")
    @Mapping(target = "answerOwnerId", source = "user.id")
    @Mapping(target = "answerOwnerType", source = "user.userType")
    ClassQuestionAnswerDto toClassQuestionAnswerDto(ClassQuestionAnswer classQuestionAnswer);

    List<ClassQuestionAnswerDto> toClassQuestionAnswerDtoList(List<ClassQuestionAnswer> classQuestionAnswers);
}
