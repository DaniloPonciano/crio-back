package com.crio.api.domain.endereco;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.util.function.IntPredicate;

@Table(name = "endereco")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue
    UUID id;

    String city;
    @Column(length = 2)
    String uf;
    public IntPredicate getCidade() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCidade'");
    }
    public Endereco(EnderecoResponseDTO data) {
        //TODO Auto-generated constructor stub
    }
}
