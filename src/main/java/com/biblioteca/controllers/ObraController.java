package com.biblioteca.controllers;

import com.biblioteca.json.ObraForm;
import com.biblioteca.json.ObraResponse;
import com.biblioteca.service.ObraService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/obra")
@AllArgsConstructor
public class ObraController {

    @Autowired
    private ObraService service;

    @PostMapping(value = "", produces = APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<ObraResponse> registrarObra(@RequestBody ObraForm obraForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarObra(obraForm));
    }
}
