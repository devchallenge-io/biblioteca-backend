package com.biblioteca.service;

import com.biblioteca.entities.Obra;
import com.biblioteca.entities.mapper.ObraMapper;
import com.biblioteca.json.ObraForm;
import com.biblioteca.json.ObraResponse;
import com.biblioteca.json.mapper.ObraResponseMapper;
import com.biblioteca.persistence.ObraRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testRegistrarObraSemAutor() {
        doThrow(ConstraintViolationException.class).when(repository).save(ObraMapper.fromFormToEntity(getObraFormSemAutor()));
        Exception exception = assertThrows(Exception.class, () -> service.registrarObra(getObraFormSemAutor()));
        assertNotNull(exception);
    }

    @Test
    void testRegistrarObraSemTitulo() {
        doThrow(ConstraintViolationException.class).when(repository).save(ObraMapper.fromFormToEntity(getObraFormSemTitulo()));
        Exception exception = assertThrows(Exception.class, () -> service.registrarObra(getObraFormSemTitulo()));
        assertNotNull(exception);
    }

//    @Test
//    void testFindObras(){
////        PagedListHolder page = new PagedListHolder(getObraList());
//        doReturn(convertToPage(getObraList(),PageRequest.of(0,10))).when(repository).findAll(PageRequest.of(0,10)).map(ObraResponseMapper::fromEntityToResponse);
//        Page<ObraResponse> findObras = service.findObras(PageRequest.of(0,10));
//        assertNotNull(findObras);
//        verify(repository).findAll(PageRequest.of(0,10)).map(ObraResponseMapper::fromEntityToResponse);
//    }

    @Test
    void testAtualizarObra() {
        doReturn(getObraOptional()).when(repository).findById(getObraForm().getId());
        service.atualizarObra(getObraForm());
        verify(repository).findById(getObraForm().getId());
    }

    @Test
    void testAtuaLizarObraNaoExistente() {
        doThrow(ResourceNotFoundException.class).when(repository).findById(getObraForm().getId());
        Exception exception = assertThrows(Exception.class, () -> service.atualizarObra(getObraForm()));
        assertNotNull(exception);
    }

    @Test
    void testDeletaObra(){
        doReturn(getObraOptional()).when(repository).findById(ID);
        service.deletaObra(ID);
        verify(repository).findById(ID);
    }

    @Test
    void testDeletaObraNaoExistente(){
        doThrow(ResourceNotFoundException.class).when(repository).findById(ID);
        Exception exception = assertThrows(Exception.class, () -> service.deletaObra(ID));
        assertNotNull(exception);
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

    private List<Obra>  getObraList(){
        return List.of(Obra.builder()
                .id(ID)
                .autor(AUTOR)
                .titulo(TITULO)
                .editora(EDITORA)
                .build());
    }

    public static<T> Page<T> convertToPage(List<T> objectList, Pageable pageable){
        int start = (int) pageable.getOffset();
        int end = Math.min(start+pageable.getPageSize(),objectList.size());
        List<T> subList = start>=end?new ArrayList<>():objectList.subList(start,end);
        return new PageImpl<>(subList,pageable,objectList.size());
    }
}
