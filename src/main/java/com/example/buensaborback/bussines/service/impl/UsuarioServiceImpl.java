package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IUsuarioService;
import com.example.buensaborback.domain.entities.Usuario;
import com.example.buensaborback.presentation.advice.exception.DuplicateEntryException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.presentation.advice.exception.UnauthorizeException;
import com.example.buensaborback.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario getUsuarioById(Long id){
        return this.usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Usuario con ID %d no encontrado", id)));
    }

    public Usuario getUsuarioByUsername(String username){
        return this.usuarioRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(String.format("Usuario con Username %s no encontrado", username)));
    }

    @Override
    public List<Usuario> getAll() {
        return this.usuarioRepository.findAll();
    }

    public boolean existsUsuarioById(Long id){
        return this.usuarioRepository.existsById(id);
    }
    public boolean existsUsuarioByUsername(String username) {
        return this.usuarioRepository.findByUsername(username).isPresent();
    }

    public Usuario login(String username, String clave) {
       Usuario existente =  this.getUsuarioByUsername(username);
       if(existente.getAuth0Id().equals(clave)){
           return existente;
       }
       throw new UnauthorizeException("Credenciales incorrectas");
    }

    public Usuario register(Usuario usuario) {
        if(existsUsuarioByUsername(usuario.getUsername())){
            throw new DuplicateEntryException("Usuario ya registrado");
        }
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
