package com.example.buensaborback.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Pais extends Base{

    private String nombre;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "pais")
    @Builder.Default
    private Set<Provincia> provincias = new HashSet<>();

}