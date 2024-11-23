package com.crio.api.repositorie;

import com.crio.api.domain.evento.Evento;
import com.crio.api.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface EventoRepository extends
        JpaRepository<Evento, UUID> {
    // findEventosbyTitle() implementar
    //findEventosbyDate()

    //OK  buscar todos eventos de um usuário
    @Query("SELECT e FROM Evento e WHERE e.usuario.id = :usuarioId")
    List<Evento> findByUsuarioId(UUID usuarioId);
    //ok
    @Query("SELECT e FROM Evento e WHERE e.inicio BETWEEN :inicio AND :fim")
    List<Evento> findByIntervaloData(LocalDateTime inicio, LocalDateTime fim);
    //ok consulta por local
    @Query("SELECT e FROM Evento e WHERE e.local = :local")
    List<Evento> findByLocalContainig(String local);
    //consulta por local e intervalo de data
    @Query("SELECT e FROM Evento e WHERE e.local = :local AND e.inicio BETWEEN :inicio AND :fim")
    List<Evento> findByLocalAndIntervaloData(String local, LocalDateTime inicio, LocalDateTime fim);

    Optional<Evento> findByLocal (String local);


}