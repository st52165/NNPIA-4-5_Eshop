package cz.upce.eshop.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;

public class AddOrEditProductDto {
    private long id;
    private String name;
    private String description;
    private MultipartFile image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
