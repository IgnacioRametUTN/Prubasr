package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.*;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.presentation.advice.exception.InsufficientStock;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class PedidoServiceImpl implements IPedidoService {
    private final PedidoRepository pedidoRepository;
    private final IUsuarioService usuarioService;
    private final IArtManufacturadoService artManufacturadoService;
    private final IArticuloInsumoService artInsumoService;
    private final IClienteService clienteService;
    private final IFacturaService facturaService;
    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, UsuarioServiceImpl usuarioService, ArtManufacturadoServiceImpl artManufacturadoService, ClienteServiceImpl clienteServiceImpl, ArticuloInsumoServiceImpl artInsumoService, FacturaServiceImpl facturaService) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioService = usuarioService;
        this.artManufacturadoService = artManufacturadoService;
        this.clienteService = clienteServiceImpl;
        this.artInsumoService = artInsumoService;
        this.facturaService = facturaService;
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
        pedido.setEstado(Estado.Preparacion);

        System.out.println("Pedido recibido: " + pedido.toString());
        Usuario usuarioOp = usuarioService.getUsuarioByUsername(pedido.getCliente().getUsuario().getUsername());
            pedido.setCliente(usuarioOp.getCliente());
            for (DetallePedido detalle : pedido.getDetallePedidos()) {
                //Preguntar si existe, sino Falla
                ArticuloManufacturado articulo = artManufacturadoService.getArticuloManufacturadoById(detalle.getArticulo().getId());

                //Revisar cantidades actuales de los insumos
                articulo.getArticuloManufacturadoDetalles().forEach(detalleManufacturado -> {      //Cantidad que necesito para 1      //Cantidad que pide
                    double stockRestante =  detalleManufacturado.getArticuloInsumo().getStockActual() - detalleManufacturado.getCantidad() * detalle.getCantidad();
                    if(stockRestante < 0) {
                        throw new InsufficientStock("No hay suficiente cantidad de ");
                    }

                   //Hay que guardar el nuevo stock
                    detalleManufacturado.getArticuloInsumo().setStockActual(stockRestante);
                });
                pedido.setTotalCosto(calcularCostoTotal(pedido.getDetallePedidos()));
                detalle.setArticulo(articulo);
                detalle.setPedido(pedido);
            }
            return pedidoRepository.save(pedido);

    }

    private Double calcularCostoTotal(Set<DetallePedido> detallesPedido ) {
        double total = 0.0;
        for (DetallePedido detallePedido : detallesPedido) {
            Articulo articulo = getArticulo(detallePedido);

            if (articulo instanceof ArticuloManufacturado) {
                double subTotalManufacturados = ((ArticuloManufacturado) articulo)
                        .getArticuloManufacturadoDetalles()
                        .stream()
                        .mapToDouble(detalle -> detalle.getArticuloInsumo().getPrecioCompra() * detalle.getCantidad()*detallePedido.getCantidad())
                        .sum();
                total += subTotalManufacturados;

            } else if (articulo instanceof ArticuloInsumo) {
                System.out.println("TENGO UN INSUMO");
                total += ((ArticuloInsumo) articulo).getPrecioCompra() * detallePedido.getCantidad();
            }
        }
        System.out.println("TOTAL COSTO " + total);
        return total;
    }

    private Articulo getArticulo(DetallePedido detallePedido){
        if(artManufacturadoService.existsArticuloManufacturadoById(detallePedido.getArticulo().getId())){
            return artManufacturadoService.getArticuloManufacturadoById(detallePedido.getArticulo().getId());
        }else{
           return artInsumoService.getArticuloInsumoById(detallePedido.getArticulo().getId());
        }
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
        Cliente cliente = this.clienteService.getClienteById(idCliente);
        return this.pedidoRepository.findByAltaTrueAndCliente(cliente);
    }
    public List<Object> findTopProducts(LocalDate startDate, LocalDate endDate) {
        return pedidoRepository.findTopProducts(startDate, endDate);
    }

    public List<Pedido> findPedidosBetweenDates(LocalDate startDate, LocalDate endDate) {
        return pedidoRepository.findByFechaPedidoBetween(startDate, endDate);

    }
    public List<Pedido> findByEstado(Estado estado){
        return pedidoRepository.findByEstado(estado);
    }

    @Transactional
    public Pedido actualizarEstado(Long id, Estado estado){
        Pedido pedido = this.getPedidoById(id);
        pedido.setEstado(estado);
       if(estado == Estado.Entregado) facturaService.crearFactura(pedido);
        return  pedidoRepository.save(pedido);
    }

    @Override
    public Pedido findByFactura(Factura factura){
        return  pedidoRepository.findByFactura(factura);
    }


}
