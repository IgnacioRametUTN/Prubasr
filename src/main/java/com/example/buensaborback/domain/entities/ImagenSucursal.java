package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
public class ImagenSucursal extends Base {

    private String url;

    @OneToOne
    @JoinColumn(name = "sucursal_id")
    @JsonBackReference
    private Sucursal sucursal;
}
