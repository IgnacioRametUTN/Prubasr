package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.dto.RedirectDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin("*")
public class EmpleadoController {

    @GetMapping ("/formulario/{empleado}")
    public ResponseEntity<RedirectDto> redirectToExternalUrl(@PathVariable("empleado") String email) {
        System.out.println("entre al redirect");
        try{
            return ResponseEntity.ok(RedirectDto.builder().urlRedirect("/formulario-empleado").build());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }



}
