package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.entities.enums.Rol;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
public class Empleado extends Base {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Rol perfil;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Usuario usuario;


}
