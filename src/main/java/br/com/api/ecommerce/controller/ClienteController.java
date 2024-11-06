package br.com.api.ecommerce.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.ecommerce.dto.ClienteRequestDTO;
import br.com.api.ecommerce.dto.ClienteResponseDTO;
import br.com.api.ecommerce.entity.Cliente;
import br.com.api.ecommerce.repository.ClienteRepository;
import br.com.api.ecommerce.service.ClienteService;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/clientes")
public class ClienteController {
		@Autowired
	    private  ClienteService clienteService;
		@Autowired
	    private  ClienteRepository clienteRepository;

	  

	    @GetMapping
	    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
	        return ResponseEntity.ok(clienteService.listarClientes());
	    }


	    /*@PostMapping("/criar-varias")
	    public ResponseEntity<List<ClienteResponseDTO>> criarVariasClientes(@RequestBody List<ClienteResponseDTO> clientes) {
	        List<ClienteResponseDTO> novasClientes = clienteRepository.saveAll(clientes);
	        return ResponseEntity.status(HttpStatus.CREATED).body(novasClientes);
	    }*/

	    @PutMapping("/{id}")
	    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizada) {
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
	    @PostMapping("/criar")
		public ResponseEntity<Object> inserir(@RequestBody ClienteRequestDTO dto) throws IOException {
			ClienteResponseDTO dtoResponse = clienteService.inserir(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
		}
		
		@GetMapping("{id}")
		public  ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id){
			return ResponseEntity.ok(clienteService.buscarPorId(id));
		}


}
