package com.biblioteca.controllers;

import com.biblioteca.entities.Obra;
import com.biblioteca.json.ObraForm;
import com.biblioteca.json.ObraResponse;
import com.biblioteca.service.ObraService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obra")
@AllArgsConstructor
public class ObraController {

    @Autowired
    private ObraService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<Obra> findObras() {
        return service.findObras();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/resgistra")
    ObraResponse registrarObra(@RequestBody ObraForm obraForm) {
        return service.registrarObra(obraForm);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("")
    String atualizarObra(@RequestBody ObraForm obraForm) {
        return service.atualizarObra(obraForm);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deletarObra(@PathVariable Long id) {
        service.deletaObra(id);
    }
}
