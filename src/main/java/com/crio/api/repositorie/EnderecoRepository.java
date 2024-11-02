package com.crio.api.repositorie;

import com.crio.api.domain.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    @Query("SELECT e FROM Endereco e WHERE e.uf = :uf")
    List<Endereco> findByUf(String uf);

    @Query("SELECT e FROM Endereco e WHERE e.city LIKE %:city%")
    List<Endereco> findByCityContaining(String city);

    Optional<Endereco> findByCity(String city);
}
