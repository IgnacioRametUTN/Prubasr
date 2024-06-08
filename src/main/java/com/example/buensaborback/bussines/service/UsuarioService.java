package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Usuario;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario getUsuarioById(Long id){
        return this.usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Unidad Medida con ID %d no encontrado", id)));
    }

    public boolean existsUsuarioById(Long id){
        return this.usuarioRepository.existsById(id);
    }
    public boolean existsUsuarioByUsername(String username) {
        return this.usuarioRepository.findByUsername(username).isPresent();
    }

    public Optional<Usuario> login(String nombreUsuario, String clave) {
        return usuarioRepository.findByAuth0IdAndUsername(clave, nombreUsuario);
    }

    public Usuario register(Usuario usuario) {
        //usuario.setAuth0Id(encriptarClaveSHA256(usuario.getAuth0Id()));
        return usuarioRepository.save(usuario);
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
