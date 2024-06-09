package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.entities.enums.Rol;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Usuario extends Base{

    private String auth0Id;
    private String username;
    private Rol rol;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonBackReference
    private Cliente cliente;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonBackReference
    private Empleado empleado;

}
