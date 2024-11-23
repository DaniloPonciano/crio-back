package crio.api.repositories;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.crio.api.domain.endereco.Endereco;
import com.crio.api.domain.endereco.EnderecoResponseDTO;
import com.crio.api.repositorie.EnderecoRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class EnderecoRepositoryTests {

    @Autowired
    EntityManager entityManager;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    Endereco creatEndereco;

    @Test
    @DisplayName("Should get Endereco successfully from DB")
    void findEnderecoByNameDocumentCase1() {
        String city = "Morro da Fumaça"; // nome da cidade que estamos procurando
        String uf = "SC"; //UF da cidade que o sistema solicita no ResponseDTO
        EnderecoResponseDTO data = new EnderecoResponseDTO(null, city, uf); // ID nulo para permitir a geração automática
        this.createEndereco(data);

        Optional<Endereco> result = this.enderecoRepository.findByCity(city);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getCidade()).isEqualTo(city);
    }

    @Test
    @DisplayName("Should not get Endereco from DB when Endereco not exists")
    void findEnderecoByNameDocumentCase2() {
        String name = "EndereçoInexistente"; // nome da categoria que estamos procurando

        Optional<Endereco> result = this.enderecoRepository.findByCity(name);

        assertThat(result.isEmpty()).isTrue();
    }

    private Endereco createEndereco(EnderecoResponseDTO data) {
        Endereco endereco = new Endereco(data);
        this.entityManager.persist(endereco);
        return endereco;
    }
}