package com.crio.api.service;

import com.crio.api.domain.usuario.Usuario;
import com.crio.api.domain.usuario.UsuarioRequestDTO;
import com.crio.api.domain.usuario.UsuarioResponseDTO;
import com.crio.api.repositorie.UsuarioRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario createUsuario(UsuarioRequestDTO data) {
        // cria um usuario vazio
        Usuario newUsuario = new Usuario();
        // preenche os dados do usuario
        newUsuario.setFullName(data.fullName());
        newUsuario.setEmail(data.email());
        newUsuario.setSenha(data.senha());
        newUsuario.setTipo(data.tipo());
        usuarioRepository.save(newUsuario);
        return  newUsuario;
    }

    @Transactional 
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario getUserById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    @Transactional
    public Usuario updateUser(UUID id, UsuarioRequestDTO usuarioRequestDTO) {
        try{
            Usuario updateUsuario = getUserById(id);
            updateUsuario.setFullName(usuarioRequestDTO.fullName());
            updateUsuario.setEmail(usuarioRequestDTO.email());
            updateUsuario.setSenha(usuarioRequestDTO.senha());
            updateUsuario.setTipo(usuarioRequestDTO.tipo());
            updateUsuario.setUpdatedAt(LocalDateTime.now());

            return usuarioRepository.save(updateUsuario);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id Not Found " + id);
        }
    }

    @Transactional (propagation = Propagation.SUPPORTS)
    public void deleteUser(UUID id) {

        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException ("Id Não Encontrado " + id);
        }
        try{
            usuarioRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity violation");
        }
    }

    


}
