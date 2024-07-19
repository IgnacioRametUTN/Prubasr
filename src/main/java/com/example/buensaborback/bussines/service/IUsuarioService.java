package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
     Usuario getUsuarioById(Long id);
     Usuario getUsuarioByUsername(String username);
     List<Usuario> getAll();

     boolean existsUsuarioById(Long id);
     boolean existsUsuarioByUsername(String username);

     Usuario login(Usuario usuario);

     Usuario register(Usuario usuario);

     String encriptarClaveSHA256(String clave);

     void delete(Long id);

}
