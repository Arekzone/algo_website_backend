package com.shop.api.controller.auth.Materialy;

import jakarta.persistence.Lob;

import java.sql.Blob;

public class MaterialyBody {
    private String kategoria;
    @Lob
    private Blob obrazek;
    private String shortDescription;
    private String longDescription;
}
