package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
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
public  class Articulo extends Base {


    protected String denominacion;
    protected Double precioVenta;

    @OneToMany(cascade = CascadeType.ALL )
    @JoinColumn(name= "articulo_id")
    @Builder.Default
    protected Set<Imagen> imagenes = new HashSet<Imagen>();

    @ManyToOne(cascade = CascadeType.ALL)
    protected UnidadMedida unidadMedida;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    protected Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    protected Set<PromocionDetalle> promocionDetalle;
}
