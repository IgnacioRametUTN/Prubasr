package com.example.buensaborback.domain.dtos.empresa;
import com.example.buensaborback.domain.dtos.sucursal.SucursalFullDto;
import lombok.*;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @Builder


public class EmpresaDTO {
    private Long id;
    private String nombre;
    private String razonSocial;
    private Long cuil;
    private SucursalFullDto sucursalFullDto;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Long getCuil() {
        return cuil;
    }

    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }

    public SucursalFullDto getSucursalFullDto() {
        return sucursalFullDto;
    }

    public void setSucursalFullDto(SucursalFullDto sucursalFullDto) {
        this.sucursalFullDto = sucursalFullDto;
    }
}



