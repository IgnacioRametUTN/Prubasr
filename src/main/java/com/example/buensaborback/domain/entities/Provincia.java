package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Set<Localidad>localidades=new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pais")
    @JsonManagedReference
    private Pais pais;

}
