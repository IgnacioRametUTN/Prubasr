package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IUsuarioService;
import com.example.buensaborback.domain.entities.Usuario;
import com.example.buensaborback.domain.entities.enums.Rol;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.presentation.advice.exception.UnauthorizeException;
import com.example.buensaborback.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void delete(Long id) {
        if (!existsUsuarioById(id)) {
            throw new NotFoundException(String.format("Usuario con ID %d no encontrado", id));
        }
        usuarioRepository.deleteById(id);
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
    public Usuario login(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioOptional.isPresent()) {
            System.out.println("Usuario encontrado" );
            Usuario usuarios = usuarioOptional.get();
            if (usuario.getAuth0Id().equals(usuario.getAuth0Id())) {
                return usuarios;
            } else {
                throw new UnauthorizeException("Credenciales incorrectas");
            }
        } else {
            return this.register(usuario);
        }
    }

    @Override
    public Usuario register(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioExistente.isPresent()) {
            Usuario usuarioActualizado = usuarioExistente.get();
            usuarioActualizado.setAuth0Id(usuario.getAuth0Id());
            usuarioActualizado.setEmail(usuario.getEmail());
            return usuarioRepository.save(usuarioActualizado);
        } else {
            System.out.println("USUARIO A GUARDAR " + usuario);
            return usuarioRepository.save(usuario);
        }
    }

    @Override
    public String encriptarClaveSHA256(String clave) {
        // Implementación para encriptar la contraseña si fuera necesario
        return null;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> getUsuariosByRol(Rol rol) {
        return usuarioRepository.findByRol(rol);
    }

    @Override
    public Usuario updateUsuarioRol(Long id, Rol newRol) {
        Usuario usuario = getUsuarioById(id);
        usuario.setRol(newRol);
        return usuarioRepository.save(usuario);
    }
}