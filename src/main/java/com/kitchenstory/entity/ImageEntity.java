package com.kitchenstory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "images")
public class ImageEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(length = Integer.MAX_VALUE)
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    public ImageEntity(byte[] image) {
        this.image = image;
    }

    public static String bytesToImageConverter(byte[] imageInBytes) {
        return imageInBytes != null && imageInBytes.length > 0 ? Base64.getEncoder().encodeToString(imageInBytes) : "";
    }

    public static int randomInt() {
        return ((int) (Math.random() * (9 - 0))) + 0;
    }
}
