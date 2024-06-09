package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IArtManufacturadoService;
import com.example.buensaborback.bussines.service.IPedidoService;
import com.example.buensaborback.bussines.service.IUsuarioService;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ArticuloManufacturadoRepository;
import com.example.buensaborback.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements IPedidoService {
    private final PedidoRepository pedidoRepository;
    private final IUsuarioService usuarioService;
    private final IArtManufacturadoService artManufacturadoService;
    private final ClienteServiceImpl clienteServiceImpl;
    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, UsuarioServiceImpl usuarioService, ArtManufacturadoServiceImpl artManufacturadoService, ClienteServiceImpl clienteServiceImpl) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioService = usuarioService;
        this.artManufacturadoService = artManufacturadoService;
        this.clienteServiceImpl = clienteServiceImpl;
    }
    @Override
    public Pedido getPedidoById(Long id){
        return this.pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Pedido con ID %d no encontrado", id)));
    }

    @Override
    public boolean existsPedidoById(Long id){
        return this.pedidoRepository.existsById(id);
    }
    @Transactional
    public Pedido save(Pedido pedido) {
        Usuario usuarioOp = usuarioService.getUsuarioByUsername(pedido.getCliente().getUsuario().getUsername());
            pedido.setCliente(usuarioOp.getCliente());
            for (DetallePedido detalle : pedido.getDetallePedidos()) {
                ArticuloManufacturado articulo = artManufacturadoService.getArticuloManufacturadoById(detalle.getArticulo().getId());
                detalle.setArticulo(articulo);
                detalle.setPedido(pedido);
            }
            return pedidoRepository.save(pedido);

    }
    @Override
    @Transactional(readOnly = true)
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> getAllByFecha(LocalDate fecha) {
        return pedidoRepository.findByFechaPedido(fecha);
    }

    @Override
    @Transactional
    public Pedido delete(Long id) {
        Pedido pedido = this.getPedidoById(id);
        pedido.setAlta(pedido.isAlta());
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> getAllByCliente(Long idCliente){
        Cliente cliente = this.clienteServiceImpl.getClienteById(idCliente);
        return this.pedidoRepository.findByAltaTrueAndCliente(cliente);
    }

}
