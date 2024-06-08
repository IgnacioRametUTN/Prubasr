package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Domicilio extends Base{

    private String calle;
    private Integer numero;
    private Integer cp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_localidad")
    @JsonManagedReference
    private Localidad localidad;

    @ManyToMany(mappedBy = "domicilios", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @JsonBackReference
    @JsonIgnore
    private Set<Cliente> clientes = new HashSet<>();

}
