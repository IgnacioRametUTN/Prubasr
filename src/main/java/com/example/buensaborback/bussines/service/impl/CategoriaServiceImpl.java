package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IArticuloManufacturadoService;
import com.example.buensaborback.bussines.service.ICategoriaService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl  extends BaseServiceImpl<Categoria,Long> implements ICategoriaService {
}
