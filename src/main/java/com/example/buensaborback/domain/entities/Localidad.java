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
public class Localidad extends Base{

    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "localidad")
    @Builder.Default
    @ToString.Exclude
    private Set<Domicilio> domicilios = new HashSet<>();


}
