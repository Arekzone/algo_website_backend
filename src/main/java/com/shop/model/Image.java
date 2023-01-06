package com.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] data;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "materialy_id")
    private Materialy materialy;

}