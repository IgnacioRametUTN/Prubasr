package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Entity
public class Cliente extends Base{

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Imagen imagen;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    @Builder.Default
    @JsonManagedReference
    private Set<Pedido> pedidos = new HashSet<>();



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Cliente_domicilio",
            joinColumns = @JoinColumn(name = "Cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "domicilio_id"))
    @Builder.Default
    @JsonManagedReference
    private Set<Domicilio> domicilios = new HashSet<>();



}
