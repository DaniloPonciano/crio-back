package crio.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.crio.api.domain.endereco.Endereco;
import com.crio.api.domain.endereco.EnderecoResponseDTO;
import com.crio.api.domain.usuario.Usuario;
import com.crio.api.domain.usuario.UsuarioResponseDTO;
import com.crio.api.repositorie.UsuarioRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
    
    @Autowired
    EntityManager entityManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    Usuario createUsuario;

    private Usuario createUsuario(UsuarioResponseDTO data){
        Usuario usuario = new Usuario(data);
        this.entityManager.persist(usuario);
        return usuario;
    }

    @Test
    @DisplayName("Should get Usuario successfully from DB")
    void findUsuarioByNameDocumentCase1(){
        String fullName = "Danilo Quirino Ponciano";
        String email = "danilo@test.com.br";
        String senha = "daniloteste123";
        int tipo = 1;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        UsuarioResponseDTO data = new UsuarioResponseDTO(null, fullName, email, senha, tipo, createdAt, updatedAt);
        this.createUsuario(data);

        Optional<Usuario> result = this.usuarioRepository.findByFullName(fullName);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getFullName()).isEqualTo(fullName);
    }

    @Test
    @DisplayName("Should not get Usuario from DB when Usuario not exists")
    void findEnderecoByNameDocumentCase2() {
        String name = "UsuarioInexistente"; // nome do usuário que estamos procurando

        Optional<Usuario> result = this.usuarioRepository.findByFullName(name);

        assertThat(result.isEmpty()).isTrue();
    }

}
