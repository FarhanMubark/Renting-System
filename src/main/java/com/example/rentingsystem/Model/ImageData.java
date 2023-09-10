package com.example.rentingsystem.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "image_data")
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String type;
    private Integer product_id;
    // convert the file in byte array and stored in db
    @Lob // to store any binary format in db
    @Column(name = "imagedata", length = 100000)
    private byte[] imageData;
}
