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
@ToString
@SuperBuilder
@Entity
public class Empresa extends Base {

    private String nombre;
    private String razonSocial;
    private Long cuil;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();




}
