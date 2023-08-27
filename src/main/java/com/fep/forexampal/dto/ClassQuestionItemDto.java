package com.fep.forexampal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassQuestionItemDto {

    private Long id;
    private String ownerName;
    private String ownerSurname;
    private List<String> subjects;
    private String description;
    private int reward;
    private String imagePath;
    private int width;
    private int height;
    private String correctAnswer;
    private int totalComment;
    private int totalTaggedUser;
    private boolean solved;
    private Date createdDate;
}
