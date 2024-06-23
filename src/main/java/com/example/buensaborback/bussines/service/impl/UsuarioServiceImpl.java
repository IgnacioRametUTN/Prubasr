package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IUsuarioService;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.Usuario;
import com.example.buensaborback.presentation.advice.exception.DuplicateEntryException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.presentation.advice.exception.UnauthorizeException;
import com.example.buensaborback.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Usuario con ID %d no encontrado", id)));
    }

    @Override
    public Usuario getUsuarioByUsername(String username) {
        return this.usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("Usuario con Username %s no encontrado", username)));
    }

    @Override
    public List<Usuario> getAll() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public boolean existsUsuarioById(Long id) {
        return this.usuarioRepository.existsById(id);
    }

    @Override
    public boolean existsUsuarioByUsername(String username) {
        return this.usuarioRepository.findByUsername(username).isPresent();
    }

    @Override
    public Usuario login(String username, String clave) {
        Usuario existente = this.getUsuarioByUsername(username);
        if (existente.getAuth0Id().equals(clave)) {
            return existente;
        }
        throw new UnauthorizeException("Credenciales incorrectas");
    }

    @Override
    public Usuario register(Usuario usuario) {
        if (existsUsuarioByUsername(usuario.getUsername())) {
            throw new DuplicateEntryException("Usuario ya registrado");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public String encriptarClaveSHA256(String clave) {
        // Implementación para encriptar la contraseña si fuera necesario

        return null;
    }
}
