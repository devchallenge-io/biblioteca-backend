package com.biblioteca.controllers;

import com.biblioteca.json.ObraForm;
import com.biblioteca.json.ObraResponse;
import com.biblioteca.service.ObraService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/obra")
@AllArgsConstructor
public class ObraController {

    @Autowired
    private ObraService service;

    @GetMapping
    ResponseEntity<Page<ObraResponse>> findObras(Pageable pageable) {
        System.out.println("ENtrou aqui");
        return ResponseEntity.status(HttpStatus.OK).body(service.findObras(pageable));
    }

    @PostMapping
    ResponseEntity<ObraResponse> registrarObra(@RequestBody ObraForm obraForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarObra(obraForm));
    }

    @PutMapping
    ResponseEntity<Object> atualizarObra(@RequestBody ObraForm obraForm) {
        service.atualizarObra(obraForm);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    ResponseEntity<Object> deletarObra(@RequestParam Long id) {
        try {
            service.deletaObra(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
