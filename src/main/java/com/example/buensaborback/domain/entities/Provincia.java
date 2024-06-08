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
public class Provincia extends Base{

    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "provincia", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private Set<Localidad>localidades=new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais")
    private Pais pais;

}
