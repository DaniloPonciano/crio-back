package com.crio.api.service;

import com.crio.api.domain.usuario.Usuario;
import com.crio.api.domain.usuario.UsuarioRequestDTO;
import com.crio.api.domain.usuario.UsuarioResponseDTO;
import com.crio.api.repositorie.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario createUsuario(UsuarioRequestDTO data){
        //cria um usuario vazio
        Usuario newUsuario = new Usuario();
        //preenche os dados do usuario
        newUsuario.setFullName(data.fullName());
        newUsuario.setEmail(data.email());
        newUsuario.setSenha(data.senha());
        newUsuario.setTipo(data.tipo());
        usuarioRepository.save(newUsuario);
        return newUsuario;
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Usuario getUserById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public Usuario updateUser(UUID id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario updateUsuario = getUserById(id);
        updateUsuario.setFullName(usuarioRequestDTO.fullName());
        updateUsuario.setEmail(usuarioRequestDTO.email());
        updateUsuario.setSenha(usuarioRequestDTO.senha());
        updateUsuario.setTipo(usuarioRequestDTO.tipo());
        updateUsuario.setUpdatedAt(LocalDateTime.now());

        return usuarioRepository.save(updateUsuario);
    }
    public void deleteUser(UUID id){
        Usuario usuario = getUserById(id);
        usuarioRepository.delete(usuario);
    }

    public Object findAllPaged(Pageable any) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllPaged'");
    }

    public Object insert(UsuarioResponseDTO any) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }
}
