package com.fep.forexampal.persistence.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
@AttributeOverride( name = "width", column = @Column(name = "WIDTH"))
@AttributeOverride( name = "height", column = @Column(name = "HEIGHT"))
@AttributeOverride( name = "imagePath", column = @Column(name = "IMAGE_PATH"))
public class Image {

    private int width;
    private int height;
    private String imagePath;
}
