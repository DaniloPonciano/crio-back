package crio.api.repositories;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.crio.api.domain.evento.Evento;
import com.crio.api.domain.evento.EventoReponseDTO;
import com.crio.api.repositorie.EventoRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")

public class EventoRepositoryTests {

    @Autowired
    EntityManager entityManager;

    @Autowired
    EventoRepository enderecoRepository;

    @Autowired
    Evento  creatEvento;

    @Test
    @DisplayName("Should get Evento successfully from DB")
    void findEventoByNameDocumentCase1() {
        String titulo = "Morro da Fumaça"; // nome da cidade que estamos procurando
        String descricao = "SC"; //UF da cidade que o sistema solicita no ResponseDTO
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = LocalDateTime.now();
        String local = "Morro da Fumaça SC, Esperança, Centro multiplouso";
        boolean privado = true;
        String linkEvento = "localhost:8080/evento/endereco";
        String comoChegar = "Próximo ao supermercado Giassi, duas ruas após o trilho";
        String linkForms = "localhost:8080/evento/inscricao";
        String usuario = "Danilo";
        String endereco = "Morro da Fumaça SC, Jussara, 318";

        EventoReponseDTO data = new EventoReponseDTO(null, titulo, descricao, inicio, 
        fim, local, privado, linkEvento, comoChegar, linkForms, null, null); // ID nulo para permitir a geração automática
        this.createEvento(data);

        Optional<Evento> result = this.enderecoRepository.findByCity(city);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getCidade()).isEqualTo(city);
    }

    @Test
    @DisplayName("Should not get Evento from DB when Evento not exists")
    void findEventoByNameDocumentCase2() {
        String name = "CategoriaInexistente"; // nome da categoria que estamos procurando

        Optional<Evento> result = this.enderecoRepository.findByCity(name);

        assertThat(result.isEmpty()).isTrue();
    }

    private Evento createEvento(EventoReponseDTO data) {
            Evento category = new Evento(data);
        this.entityManager.persist(category);
        return category;
    }
}
