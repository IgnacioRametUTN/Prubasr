package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IClienteService;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente,Long> implements IClienteService{

}
