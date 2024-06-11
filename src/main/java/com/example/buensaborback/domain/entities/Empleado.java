package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.entities.enums.Rol;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder

@JsonIgnoreProperties({"hibernateLazyInitializer", "usuario"})
public class Empleado extends Base {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;


}
