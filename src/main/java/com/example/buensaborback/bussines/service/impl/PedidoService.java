package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ArticuloManufacturadoRepository;
import com.example.buensaborback.repositories.PedidoRepository;
import com.example.buensaborback.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArticuloManufacturadoRepository articuloRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository,ArticuloManufacturadoRepository articuloRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.articuloRepository=articuloRepository;
    }

    public Pedido getPedidoById(Long id){
        return this.pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Pedido con ID %d no encontrado", id)));
    }

    public boolean existsPedidoById(Long id){
        return this.pedidoRepository.existsById(id);
    }
    @Transactional
    public Pedido guardarPedido(Pedido pedido) {
        Optional<Usuario> usuarioOp = usuarioRepository.findByUsername(pedido.getCliente().getUsuario().getUsername());
        if (usuarioOp.isPresent()) {
            pedido.setCliente(usuarioOp.get().getCliente());
            for (DetallePedido detalle : pedido.getDetallePedidos()) {
                ArticuloManufacturado articulo = articuloRepository.findById(detalle.getArticulo().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Artículo no encontrado"));

                detalle.setArticulo(articulo);
                detalle.setPedido(pedido);
            }
            return pedidoRepository.save(pedido);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Pedido> obtenerTodosPedidos() {
        return pedidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Pedido> obtenerPedidosPorFecha(LocalDate fecha) {
        return pedidoRepository.findByFechaPedido(fecha);
    }

    @Transactional
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

}
