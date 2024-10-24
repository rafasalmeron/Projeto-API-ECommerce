package br.com.api.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ecommerce.dto.PedidoResponseDTO;
import br.com.api.ecommerce.entity.Pedido;
import br.com.api.ecommerce.repository.PedidoRepository;
import br.com.api.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
    private final PedidoService pedidoService;
	
	@Autowired
    private final PedidoRepository pedidoRepository;
	
	
    
    public PedidoController(PedidoService pedidoService, PedidoRepository pedidoRepository) {
		this.pedidoService = pedidoService;
		this.pedidoRepository = pedidoRepository;
	}

	@GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
    	
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.listarById(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
    	Pedido novoPedido = pedidoRepository.save(pedido);
    	return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PostMapping("/criar-varias")
    public ResponseEntity<List<Pedido>> criarVariasClientes(@RequestBody List<Pedido> pedidos) {
        List<Pedido> novosPedidos = pedidoRepository.saveAll(pedidos);
        return ResponseEntity.status(HttpStatus.CREATED).body(novosPedidos);
    }

}
