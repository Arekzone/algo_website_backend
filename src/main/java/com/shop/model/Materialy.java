package com.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Setter
@Getter
public class Materialy {
    @Id
    @GeneratedValue
    private int id;
    private String kategoria;



    @Column(name = "short_description", length = 2000)
    private String shortDescription;

    @Lob
    @Column(name = "long_description",length = 20000)
    private String longDescription;

    @OneToMany(mappedBy = "materialy", orphanRemoval = true)
    private Set<Image> images = new LinkedHashSet<>();

}
