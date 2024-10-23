package br.com.api.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.ecommerce.entity.Cliente;
import br.com.api.ecommerce.repository.ClienteRepository;
import br.com.api.ecommerce.service.ClienteService;



@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	 	@Autowired
	    private final ClienteService clienteService;
	    private final ClienteRepository clienteRepository;

	    public ClienteController(ClienteService clienteService, ClienteRepository clienteRepository) {
	        this.clienteService = clienteService;
	        this.clienteRepository = clienteRepository;
	    }

	    @GetMapping
	    public ResponseEntity<List<Cliente>> listarClientes() {
	        return ResponseEntity.ok(clienteService.listarClientes());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
	        return clienteService.buscarPorId(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @PostMapping("/criar")
	    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
	        return ResponseEntity.ok(clienteService.criarCliente(cliente));
	    }

	    @PostMapping("/criar-varias")
	    public ResponseEntity<List<Cliente>> criarVariasClientes(@RequestBody List<Cliente> clientes) {
	        List<Cliente> novasClientes = clienteRepository.saveAll(clientes);
	        return ResponseEntity.status(HttpStatus.CREATED).body(novasClientes);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizada) {
	        try {
	            return ResponseEntity.ok(clienteService.atualizarCliente(id, clienteAtualizada));
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
	        clienteService.deletarCliente(id);
	        return ResponseEntity.noContent().build();
	    }

}
