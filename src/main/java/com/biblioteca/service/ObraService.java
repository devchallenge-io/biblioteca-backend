package com.biblioteca.service;

import com.biblioteca.entities.Obra;
import com.biblioteca.entities.mapper.ObraMapper;
import com.biblioteca.json.ObraForm;
import com.biblioteca.json.ObraResponse;
import com.biblioteca.json.mapper.ObraResponseMapper;
import com.biblioteca.persistence.ObraRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ObraService {

    @Autowired
    private ObraRepository repository;

    public ObraResponse registrarObra(ObraForm obraForm) {
        Obra obra = ObraMapper.fromFormToEntity(obraForm);
        return ObraResponseMapper.fromEntityToResponse(repository.save(obra));
    }
}
