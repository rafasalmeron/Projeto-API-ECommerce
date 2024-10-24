package br.com.api.ecommerce.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.api.ecommerce.dto.PedidoResponseDTO;
import br.com.api.ecommerce.entity.Pedido;
import br.com.api.ecommerce.service.PedidoService;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<Page<PedidoResponseDTO>> listarPedidos(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(pedidoService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        PedidoResponseDTO pedido = pedidoService.listarById(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/criar")
    public ResponseEntity<Pedido> criarPedido(@Valid @RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PostMapping("/criar-varias")
    public ResponseEntity<List<Pedido>> criarVariosPedidos(@RequestBody List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Pedido> novosPedidos = pedidoService.criarVariosPedidos(pedidos);
        return ResponseEntity.status(HttpStatus.CREATED).body(novosPedidos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
        Pedido atualizado = pedidoService.atualizarPedido(id, pedido);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
