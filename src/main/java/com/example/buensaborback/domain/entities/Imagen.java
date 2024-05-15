package com.example.buensaborback.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
public class Imagen extends Base{
    private String url;

}
