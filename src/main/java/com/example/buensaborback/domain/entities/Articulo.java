package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    protected String denominacion;
    protected Double precioVenta;

    @OneToMany(cascade = CascadeType.ALL )
    @JoinColumn(name= "articulo_id")
    @Builder.Default
    protected Set<Imagen> imagenes = new HashSet<Imagen>();

    @ManyToOne(cascade = CascadeType.ALL)
    protected UnidadMedida unidadMedida;

    @ManyToMany(mappedBy = "articulos", cascade = CascadeType.ALL)
    @Builder.Default
    protected Set<Promocion> estaEnPromociones = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
