package com.fep.forexampal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskMaterialDto {

    private String path;
    private int width;
    private int height;
    private String fileType;
}
