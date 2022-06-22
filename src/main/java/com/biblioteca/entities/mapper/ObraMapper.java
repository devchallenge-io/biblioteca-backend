package com.biblioteca.entities.mapper;

import com.biblioteca.entities.Obra;
import com.biblioteca.json.ObraForm;

public class ObraMapper {

    public static Obra fromFormToEntity(ObraForm obraForm) {
        return Obra.builder()
                .titulo(obraForm.getTitulo())
                .editora(obraForm.getEditora())
                .foto(obraForm.getFoto())
                .autor(obraForm.getAutor())
                .build();
    }
}
