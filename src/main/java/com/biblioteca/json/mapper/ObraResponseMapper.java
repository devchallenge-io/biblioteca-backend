package com.biblioteca.json.mapper;

import com.biblioteca.entities.Obra;
import com.biblioteca.json.ObraResponse;

public class ObraResponseMapper {

    public static ObraResponse fromEntityToResponse(Obra obra) {
        return ObraResponse.builder()
                .id(obra.getId())
                .titulo(obra.getTitulo())
                .editora(obra.getEditora())
                .autor(obra.getAutor())
                .build();
    }

    private ObraResponseMapper() {
    }
}
