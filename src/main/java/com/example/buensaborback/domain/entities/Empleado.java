package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.entities.enums.Rol;
import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;


}
