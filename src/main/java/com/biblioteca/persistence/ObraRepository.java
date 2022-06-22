package com.biblioteca.persistence;

import com.biblioteca.entities.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepository extends JpaRepository<Obra ,Long> {
}
