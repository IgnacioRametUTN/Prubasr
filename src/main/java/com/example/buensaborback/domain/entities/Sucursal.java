package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder

@JsonIgnoreProperties({"hibernateLazyInitializer", "domicilio","categorias","pedidos","promociones","imagenSucursal"})
public class Sucursal extends Base{

    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;

    @OneToOne(cascade = CascadeType.ALL)
    private Domicilio domicilio;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "sucursal_categoria",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sucursal", fetch = FetchType.LAZY)

    private Set<Pedido> pedidos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sucursal_promocion",
            joinColumns = @JoinColumn(name = "sucursal_id"),
            inverseJoinColumns = @JoinColumn(name = "promocion_id"))
    @Builder.Default
    private Set<Promocion> promociones = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "sucursales"})

    private Empresa empresa;

    @OneToOne(mappedBy = "sucursal", cascade = CascadeType.ALL)
    private ImagenSucursal imagenSucursal;

    @OneToMany
    @JoinColumn(name = "sucursal_id")
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();
}
