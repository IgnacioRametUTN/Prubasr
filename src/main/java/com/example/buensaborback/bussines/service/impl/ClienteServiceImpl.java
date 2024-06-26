package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IClienteService;
import com.example.buensaborback.bussines.service.IDomicilioService;
import com.example.buensaborback.bussines.service.ILocalidadService;
import com.example.buensaborback.bussines.service.IUsuarioService;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.Localidad;
import com.example.buensaborback.domain.entities.Usuario;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements IClienteService {
    private final ClienteRepository clienteRepository;
    private final IUsuarioService usuarioService;
    private final IDomicilioService domicilioService;
    private  final ILocalidadService localidadService;

    public ClienteServiceImpl(ClienteRepository clienteRepository, UsuarioServiceImpl usuarioService, DomicilioServiceImpl domicilioService, ILocalidadService localidadService) {
        this.clienteRepository = clienteRepository;
        this.usuarioService = usuarioService;
        this.domicilioService = domicilioService;
        this.localidadService = localidadService;
    }
    public Cliente getClienteById(Long id){
        return this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cliente con ID %d no encontrado", id)));
    }

    public boolean existsClienteById(Long id){
        return this.clienteRepository.existsById(id);
    }

    @Override
    public Cliente create(Cliente entity) {
        System.out.println(entity);
        for (Domicilio domicilio:entity.getDomicilios()) {

            Localidad local = localidadService.getLocalidadById(domicilio.getLocalidad().getId());
            domicilio.setLocalidad(local);
        }
        return this.clienteRepository.save(entity);
    }

    @Override
    public Cliente update(Long id, Cliente entity) {
        this.getClienteById(id);
        entity.getDomicilios().forEach(System.out::println);
        Set<Domicilio> updatedDomicilios = entity.getDomicilios().stream().map(domicilio -> {
            if (domicilio.getId() != 0) {
                return domicilioService.getDomicilioById(domicilio.getId());
            }else{
                domicilio.setLocalidad(localidadService.getLocalidadById(domicilio.getLocalidad().getId()));
                domicilio.getClientes().add(entity);
                return domicilioService.create(domicilio);
            }
        }).collect(Collectors.toSet());

        entity.setDomicilios(updatedDomicilios);

        return this.clienteRepository.save(entity);
    }


    @Override
    public List<Cliente> getAll() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente delete(Long id) {
        Cliente cliente = this.getClienteById(id);
        cliente.setAlta(!cliente.isAlta());
        return this.clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> findClientes(String nombre, String apellido) {
        if (nombre != null && apellido != null) {
            return clienteRepository.findByNombreStartingWithIgnoreCaseAndApellidoStartingWithIgnoreCase(nombre, apellido);
        } else if (nombre != null) {
            return clienteRepository.findByNombreStartingWithIgnoreCase(nombre);
        } else if (apellido != null) {
            return clienteRepository.findByApellidoStartingWithIgnoreCase(apellido);
        } else {
            return clienteRepository.findAll();
        }
    }

    @Override
    public Cliente getClienteByUsername(String username){
        Usuario usuario = this.usuarioService.getUsuarioByUsername(username);
        return this.clienteRepository.findByUsuario(usuario);
    }
}
