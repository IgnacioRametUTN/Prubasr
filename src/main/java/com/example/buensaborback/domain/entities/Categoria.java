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
public class Categoria extends Base {

    private String denominacion;

    @ManyToMany(mappedBy = "categorias")
    @Builder.Default
    @JsonIgnore //Se utilizo JsonIgnore porque no respondia bien a JsonBackReference y JsonManagedReference
    private Set<Sucursal> sucursales = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "categoria", fetch = FetchType.LAZY)
    @Builder.Default
    @JsonBackReference(value = "categoria-articulos")
    @JsonIgnore
    private Set<Articulo> articulos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    @JsonBackReference(value = "categoria-categorias")
    @ToString.Exclude
    private Categoria categoriaPadre;

    @OneToMany(mappedBy = "categoriaPadre", cascade = CascadeType.ALL)
    @Builder.Default
    @JsonManagedReference(value = "categoria-categorias")
    private Set<Categoria> subCategorias = new HashSet<>();

}
