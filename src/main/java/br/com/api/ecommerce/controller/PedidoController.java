package br.com.api.ecommerce.controller;

import br.com.api.ecommerce.dto.PedidoCreateDTO;
import br.com.api.ecommerce.dto.PedidoResponseDTO;
import br.com.api.ecommerce.entity.Pedido;
import br.com.api.ecommerce.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> listarPedidoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.listarById(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarPedido(@Valid @RequestBody PedidoCreateDTO pedidoCreateDTO) {
        try {
            Pedido novoPedido = pedidoService.criarPedido(pedidoCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar pedido: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        try {
            Pedido pedido = pedidoService.atualizarPedido(id, pedidoAtualizado);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
