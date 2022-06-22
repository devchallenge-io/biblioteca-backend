package com.biblioteca.json;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObraForm {

    private Long id;
    @NotBlank
    private String titulo;
    private String editora;
    private String foto;
    @NotBlank
    private String autor;
}
