package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.impl.SucursalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sucursales")
public class SucursalController  {


    private final SucursalServiceImpl sucursalService;

    @Autowired
    public SucursalController(SucursalServiceImpl sucursalService) {
        this.sucursalService = sucursalService;
    }


}
