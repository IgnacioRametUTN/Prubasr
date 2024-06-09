package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Usuario;

public interface IUsuarioService {
     Usuario getUsuarioById(Long id);

     boolean existsUsuarioById(Long id);
     boolean existsUsuarioByUsername(String username);

     Usuario login(String nombreUsuario, String clave);

     Usuario register(Usuario usuario);

     String encriptarClaveSHA256(String clave);
}
