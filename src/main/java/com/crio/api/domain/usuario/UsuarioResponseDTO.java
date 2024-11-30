package com.crio.api.domain.usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String fullName,
        String email,
        String senha,
        int tipo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public UsuarioResponseDTO(long l, String string) {
        //TODO Auto-generated constructor stub
    }

    public UsuarioResponseDTO(Usuario entity) {
        //TODO Auto-generated constructor stub
    }
}
