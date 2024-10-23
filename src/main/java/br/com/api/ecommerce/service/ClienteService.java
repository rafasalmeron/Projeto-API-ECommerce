package br.com.api.ecommerce.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.ecommerce.entity.Cliente;
import br.com.api.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private final ClienteRepository clienteRepository;
	
	public ClienteService (ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
	
	 public Optional<Cliente> buscarPorId(Long id) {
	        return clienteRepository.findById(id);
	 }
	 
	 public Cliente criarCliente(Cliente cliente) {
	        return clienteRepository.save(cliente);
	    }       
	 
	 public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
	        Cliente cliente = clienteRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
	        cliente.setNome(clienteAtualizado.getNome());
	        cliente.setTelefone(clienteAtualizado.getTelefone());
	        cliente.setEmail(clienteAtualizado.getEmail());
	        cliente.setCpf(clienteAtualizado.getCpf());
	        cliente.setCep(clienteAtualizado.getCep());
	        return clienteRepository.save(cliente);
	 }
	 
	    public void deletarCliente(Long id) {
	        clienteRepository.deleteById(id);
	    }
}
