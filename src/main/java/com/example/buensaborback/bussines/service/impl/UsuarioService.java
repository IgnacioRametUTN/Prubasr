package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.domain.entities.Usuario;
import com.example.buensaborback.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> login(String nombreUsuario, String clave) {
        return usuarioRepository.findByAuth0IdAndUsername(clave, nombreUsuario);
    }

    public Usuario register(Usuario usuario) {
        //usuario.setAuth0Id(encriptarClaveSHA256(usuario.getAuth0Id()));
        return usuarioRepository.save(usuario);
    }
    public boolean existeUsuario(String nombreUsuario) {
        return usuarioRepository.findByUsername(nombreUsuario).isPresent();
    }
    public String encriptarClaveSHA256(String clave) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] array = md.digest(clave.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
