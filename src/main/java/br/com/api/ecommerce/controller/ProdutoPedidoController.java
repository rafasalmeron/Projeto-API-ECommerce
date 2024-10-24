package br.com.api.ecommerce.controller;

import br.com.api.ecommerce.entity.ProdutoPedido;
import br.com.api.ecommerce.entity.ProdutoPedidoPK;
import br.com.api.ecommerce.service.ProdutoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produto-pedidos")
public class ProdutoPedidoController {

    @Autowired
    private ProdutoPedidoService produtoPedidoService;

    @GetMapping
    public ResponseEntity<List<ProdutoPedido>> listarTodos() {
        List<ProdutoPedido> produtoPedidos = produtoPedidoService.listarTodos();
        return ResponseEntity.ok(produtoPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoPedido> buscarPorId(@PathVariable ProdutoPedidoPK id) {
        ProdutoPedido produtoPedido = produtoPedidoService.buscarPorId(id);
        return ResponseEntity.ok(produtoPedido);
    }

    @PostMapping
    public ResponseEntity<ProdutoPedido> criarProdutoPedido(@RequestBody ProdutoPedido produtoPedido) {
        ProdutoPedido novoProdutoPedido = produtoPedidoService.criarProdutoPedido(produtoPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProdutoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoPedido> atualizarProdutoPedido(@PathVariable ProdutoPedidoPK id, @RequestBody ProdutoPedido produtoPedido) {
        ProdutoPedido atualizado = produtoPedidoService.atualizarProdutoPedido(id, produtoPedido);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoPedido(@PathVariable ProdutoPedidoPK id) {
        produtoPedidoService.deletarProdutoPedido(id);
        return ResponseEntity.noContent().build();
    }
}

