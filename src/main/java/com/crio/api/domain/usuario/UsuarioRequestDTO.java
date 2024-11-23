package com.crio.api.domain.usuario;

public record UsuarioRequestDTO(
        String fullName,
        String email,
        String senha,
        int tipo
) {
}
