package com.biblioteca.service;

import com.biblioteca.entities.Obra;
import com.biblioteca.entities.mapper.ObraMapper;
import com.biblioteca.json.ObraForm;
import com.biblioteca.json.ObraResponse;
import com.biblioteca.json.mapper.ObraResponseMapper;
import com.biblioteca.persistence.ObraRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ObraService {

    @Autowired
    private ObraRepository repository;

    public ObraResponse registrarObra(ObraForm obraForm) {
        try {
            Obra obra = ObraMapper.fromFormToEntity(obraForm);
            return ObraResponseMapper.fromEntityToResponse(repository.save(obra));
        } catch (Exception e) {
            log.error("Erro no registro de obra");
            e.printStackTrace();
            throw e;
        }

    }

    public Page<ObraResponse> findObras(Pageable pageable) {
        try {
            return repository.findAll(pageable).map(ObraResponseMapper::fromEntityToResponse);
        } catch (Exception e) {
            log.error("Erro ao encontrar obras");
            e.printStackTrace();
            throw e;
        }
    }

    public void atualizarObra(ObraForm obraForm) {
        try {
            Optional<Obra> obraInserida = repository.findById(obraForm.getId());

            if (obraInserida.isPresent()) {
                Obra obra = obraInserida.get();
                obra.setId(obraForm.getId());
                obra.setAutor(obraForm.getAutor());
                obra.setTitulo(obraForm.getTitulo());
                obra.setFoto(obraForm.getFoto());
                obra.setEditora(obraForm.getEditora());
                repository.save(obra);
            } else {
                throw new ResourceNotFoundException("Obra não encontrada.");
            }
        } catch (Exception e) {
            log.error("Erro ao atualizar obra.");
            e.printStackTrace();
            throw e;
        }
    }

    public void deletaObra(Long id) {
        try {
            Optional<Obra> obraInserida = repository.findById(id);
            if (obraInserida.isPresent()) {
                repository.deleteById(id);
            } else {
                throw new ResourceNotFoundException("Obra não encontrada.");
            }
        } catch (Exception e) {
            log.error("Erro ao deletar registro de obra");
            e.printStackTrace();
            throw e;
        }
    }
}
