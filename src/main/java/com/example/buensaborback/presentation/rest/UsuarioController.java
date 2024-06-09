package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.IUsuarioService;
import com.example.buensaborback.bussines.service.impl.UsuarioServiceImpl;
import com.example.buensaborback.domain.entities.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class UsuarioController {
    private final IUsuarioService usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.login(usuario.getUsername(), usuario.getAuth0Id()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        Usuario usuarioNuevo = usuarioService.register(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo);
    }

    @GetMapping("/validar")
    public ResponseEntity<Boolean> validarExistenciaUsuario(@RequestParam String nombreUsuario) {
        boolean usuarioExistente = usuarioService.existsUsuarioByUsername(nombreUsuario);
        return ResponseEntity.ok(usuarioExistente);
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.usuarioService.getAll());
    }
}
