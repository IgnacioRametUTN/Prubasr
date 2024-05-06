package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
public class Domicilio extends Base{

    private String calle;
    private Integer numero;
    private Integer cp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;


    @ManyToMany(mappedBy = "domicilios")
    @Builder.Default
    @ToString.Exclude
    private Set<Cliente> clientes = new HashSet<>();

}
