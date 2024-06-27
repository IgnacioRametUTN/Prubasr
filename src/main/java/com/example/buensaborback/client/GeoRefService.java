package com.example.buensaborback.client;

import com.example.buensaborback.domain.entities.Localidad;
import com.example.buensaborback.domain.entities.Provincia;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GeoRefService {

    private final ObjectMapper objectMapper;

    private Set<Provincia> provincias;
    private Set<Localidad> localidades;

    public GeoRefService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.provincias = getProvincias();
        this.localidades = getLocalidades();
    }


    public Set<Provincia> getProvincias() {
        Set<Provincia> provincias = new HashSet<>();
        ClassPathResource resource = new ClassPathResource("domicilio/provincias.json");
        try(InputStream inputStream = resource.getInputStream()){
            provincias =  objectMapper.readValue(inputStream, new TypeReference<>() {
            });

        }catch (IOException e){
            System.out.println("Hubo un error");
            System.out.println(e.getMessage());
        }

        return provincias;

    }

    private Set<Localidad> getLocalidades() {
        Set<Localidad> localidades = new HashSet<>();
        ClassPathResource resource = new ClassPathResource("domicilio/localidades.json");
        try(InputStream inputStream = resource.getInputStream()){
            localidades =  objectMapper.readValue(inputStream, new TypeReference<>() {
            });


        }catch (IOException e){
            System.out.println("Hubo un error");
            System.out.println(e.getMessage());
        }
        return localidades;

    }

    public Set<Localidad> getLocalidadesByProvincia(Provincia provincia) {
        return this.localidades.stream().filter(localidad -> localidad.getProvincia().getNombre().equals(provincia.getNombre())).collect(Collectors.toSet());

    }


}
