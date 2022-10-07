package com.biblioteca.service;

import com.biblioteca.entities.Obra;
import com.biblioteca.entities.mapper.ObraMapper;
import com.biblioteca.exceptions.RequestException;
import com.biblioteca.json.ObraForm;
import com.biblioteca.json.ObraResponse;
import com.biblioteca.persistence.ObraRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ObraServiceTest {

    @InjectMocks
    private ObraService service;

    @Mock
    private ObraRepository repository;

    public static final Long ID = 1L;

    private static final String EDITORA = "Editora 1";

    public static final String AUTOR = "Autor A";

    public static final String TITULO = "Titulo Legal";

    public static final String FOTO = "www.fotolegal.com.br.png";

    @Test
    void testRegistrarObra() {
        doReturn(getObra()).when(repository).save(ObraMapper.fromFormToEntity(getObraForm()));
        ObraResponse chamandoService = service.registrarObra(getObraForm());
        assertNotNull(chamandoService);
        verify(repository).save(ObraMapper.fromFormToEntity(getObraForm()));
    }

    @Test
    void testRegistrarObraException() {
        doThrow(RequestException.class).when(repository).save(getObra());
        Exception exception = assertThrows(Exception.class, () -> service.registrarObra(getObraForm()));
        assertEquals("Erro no registro de obra.", exception.getMessage());
    }

    @Test
    void testRegistrarObraSemAutor() {
        doThrow(ConstraintViolationException.class).when(repository).save(ObraMapper.fromFormToEntity(getObraFormSemAutor()));
        Exception exception = assertThrows(Exception.class, () -> service.registrarObra(getObraFormSemAutor()));
        assertNotNull(exception);
    }

    @Test
    void testRegistrarObraSemTitulo() {
        doThrow(ConstraintViolationException.class).when(repository).save(ObraMapper.fromFormToEntity(getObraFormSemTitulo()));
        Exception exception = assertThrows(RequestException.class, () -> service.registrarObra(getObraFormSemTitulo()));
        assertNotNull(exception);
    }

    @Test
    void testFindObras() {
        doReturn(getObraList()).when(repository).findAll();
        List<Obra> obras = service.findObras();
        assertNotNull(obras);
        verify(repository).findAll();
    }

    @Test
    void testFindObrasException() {
        doThrow(RequestException.class).when(repository).findAll();
        Exception exception = assertThrows(Exception.class, () -> service.findObras());
        assertEquals("Erro ao encontrar obras.", exception.getMessage());
    }

    @Test
    void testFindObra(){
        doReturn(getObraOptional()).when(repository).findById(ID);
        Optional<ObraResponse> obra = service.findObra(ID);
        assertNotNull(obra);
    }

    @Test
    void testAtualizarObra() {
        doReturn(getObraOptional()).when(repository).findById(getObraForm().getId());
        String atualizacao = service.atualizarObra(getObraForm());
        verify(repository).findById(getObraForm().getId());
        assertEquals("Obra atualizada.", atualizacao);

    }

    @Test
    void testAtuaLizarObraNaoExistente() {
        doThrow(ResourceNotFoundException.class).when(repository).findById(getObraForm().getId());
        Exception exception = assertThrows(Exception.class, () -> service.atualizarObra(getObraForm()));
        assertNotNull(exception);
        assertEquals("Obra não encontrada.", exception.getMessage());
    }

    @Test
    void testAtualizarObraException() {
        doThrow(RequestException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.atualizarObra(getObraForm()));
        assertEquals("Erro ao atualizar obra.", exception.getMessage());
    }

    @Test
    void testDeletaObra() {
        doReturn(getObraOptional()).when(repository).findById(ID);
        service.deletaObra(ID);
        verify(repository).findById(ID);
    }

    @Test
    void testDeletaObraNaoExistente() {
        doThrow(ResourceNotFoundException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.deletaObra(ID));
        assertEquals("Obra não encontrada.", exception.getMessage());
    }

    @Test
    void testDeletaObraException() {
        doThrow(RequestException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.deletaObra(ID));
        assertEquals("Erro ao deletar registro de obra.", exception.getMessage());
    }

    private ObraForm getObraForm() {
        return ObraForm.builder()
                .autor(AUTOR)
                .titulo(TITULO)
                .editora(EDITORA)
                .foto(FOTO)
                .build();
    }

    private ObraForm getObraFormSemAutor() {
        return ObraForm.builder()
                .titulo(TITULO)
                .editora(EDITORA)
                .foto(FOTO)
                .build();
    }

    private ObraForm getObraFormSemTitulo() {
        return ObraForm.builder()
                .autor(AUTOR)
                .editora(EDITORA)
                .foto(FOTO)
                .build();
    }

    private Obra getObra() {
        return Obra.builder()
                .id(ID)
                .foto(FOTO)
                .editora(EDITORA)
                .titulo(TITULO)
                .autor(AUTOR)
                .build();
    }

    private Optional<Obra> getObraOptional() {
        return Optional.ofNullable(Obra.builder()
                .id(ID)
                .foto(FOTO)
                .editora(EDITORA)
                .titulo(TITULO)
                .autor(AUTOR)
                .build());
    }

    private List<Obra> getObraList() {
        return List.of(Obra.builder()
                .id(ID)
                .autor(AUTOR)
                .titulo(TITULO)
                .editora(EDITORA)
                .foto(FOTO)
                .build());
    }
}
