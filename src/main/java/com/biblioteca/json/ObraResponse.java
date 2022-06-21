package com.biblioteca.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObraResponse {

    private Long id;
    private String titulo;
    private String editora;
    private String autor;
}
