package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.UsuarioService;
import com.example.buensaborback.domain.entities.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Optional<Usuario> userOptional = usuarioService.login(usuario.getUsername(), usuario.getAuth0Id());
        System.out.println(usuario.getUsername()+usuario.getAuth0Id());
        if (userOptional.isPresent()) {
            Usuario user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            System.out.println("error");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        usuarioService.register(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }

    @GetMapping("/validar")
    public ResponseEntity<Boolean> validarExistenciaUsuario(@RequestParam String nombreUsuario) {
        boolean usuarioExistente = usuarioService.existsUsuarioByUsername(nombreUsuario);
        return ResponseEntity.ok(usuarioExistente);
    }
}
