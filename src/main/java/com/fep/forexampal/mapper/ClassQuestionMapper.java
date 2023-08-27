package com.fep.forexampal.mapper;

import com.fep.forexampal.dto.ClassQuestionAddRequestDto;
import com.fep.forexampal.dto.ClassQuestionItemDto;
import com.fep.forexampal.persistence.entity.ClassQuestion;
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
public interface ClassQuestionMapper {

    ClassQuestion toClassQuestion(ClassQuestionAddRequestDto requestDto);

    List<ClassQuestionItemDto> toClassQuestionItemList(List<ClassQuestion> classQuestions);

    @Mapping(target = "imagePath", expression = "java(classQuestion.getImage().getImagePath() != null ? " +
            "com.fep.forexampal.common.utils.MediaUtils.generateFileUrl(classQuestion.getImage().getImagePath()) : null)")
    @Mapping(target = "width", source = "image.width")
    @Mapping(target = "height", source = "image.height")
    @Mapping(target = "ownerName", source = "user.name")
    @Mapping(target = "ownerSurname", source = "user.surname")
    @Mapping(target = "totalTaggedUser", expression = "java(classQuestion.getTaggedUser().size())")
    @Mapping(target = "totalComment", expression = "java(classQuestion.getClassQuestionAnswer().size())")
    @Mapping(target = "subjects", expression = "java(classQuestion.getSubject().getOrderedSubjectNameWithParents())")
    ClassQuestionItemDto toClassQuestionDto(ClassQuestion classQuestion);

}
