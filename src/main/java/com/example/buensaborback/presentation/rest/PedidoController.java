package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.impl.PedidoService;
import com.example.buensaborback.domain.dtos.articulos.ArticuloDto;
import com.example.buensaborback.domain.dtos.cliente.ClienteFullDto;
import com.example.buensaborback.domain.dtos.domicilio.domicilioShort;
import com.example.buensaborback.domain.dtos.pedido.DetallePedidoDto;
import com.example.buensaborback.domain.dtos.pedido.PedidoShortDto;
import com.example.buensaborback.domain.dtos.unidadmedida.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Long> guardarPedido(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.guardarPedido(pedido);
            return ResponseEntity.ok(nuevoPedido.getId());
        } catch (Exception e) {
            System.err.println("Error al guardar el pedido: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1L); // Retorna -1 en caso de error
        }
    }

    @GetMapping("/traer/")
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerTodosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable("id") Long id) {
        return pedidoService.obtenerPedidoPorId(id)
                .map(pedido -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<PedidoShortDto>> obtenerPedidoPorDia(@PathVariable("fecha") LocalDate fecha) {
        System.out.println("FECHA " + fecha.toString());
        List<Pedido> pedidos = pedidoService.obtenerPedidosPorFecha(fecha);
        pedidos.forEach(pedido -> pedido.setEmpleado(null));
        System.out.println(pedidos.size());
        return new ResponseEntity<>(pedidos.stream().map(this::convertPedidoToPedidoShortDto).toList(), HttpStatus.OK);
    }

    private PedidoShortDto convertPedidoToPedidoShortDto(Pedido pedido){
       return  PedidoShortDto.builder()
               .id(pedido.getId())
               .fechaPedido(pedido.getFechaPedido())
                .domicilioShort(domicilioShort.builder()
                        .calle(pedido.getDomicilio().getCalle())
                        .localidad(pedido.getDomicilio().getLocalidad().getNombre())
                        .numero(pedido.getDomicilio().getNumero()).build())
               .total(pedido.getTotal())
               .cliente(ClienteFullDto.builder()
                       .nombre(pedido.getCliente().getNombre())
                       .apellido(pedido.getCliente().getApellido())
                       .build())
               .detallePedidos(pedido.getDetallePedidos().stream().map(this::converDetallePedidoToDetallePedidoDto).toList())
                .build();
    }

    private DetallePedidoDto converDetallePedidoToDetallePedidoDto(DetallePedido detalle){
        return DetallePedidoDto.builder()
                .articulo(ArticuloDto.builder()
                        .denominacion(detalle.getArticulo().getDenominacion())
                        .precioVenta(detalle.getArticulo().getPrecioVenta())
                        .unidadMedida(UnidadMedidaDto.builder()
                                .denominacion(detalle.getArticulo().getUnidadMedida().getDenominacion())
                                .build())
                        .build())
                .cantidad(detalle.getCantidad())
                .build();
    }
}

