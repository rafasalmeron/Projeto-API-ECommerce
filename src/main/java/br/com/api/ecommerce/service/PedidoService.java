package br.com.api.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.ecommerce.dto.PedidoResponseDTO;
import br.com.api.ecommerce.entity.Pedido;
import br.com.api.ecommerce.exception.NaoEncontradoException;
import br.com.api.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public List<PedidoResponseDTO> listar() {
		List<Pedido> pedidos = repository.findAll();
		List<PedidoResponseDTO> dtos = new ArrayList<>();
		for (Pedido pedido : pedidos) {
			dtos.add(new PedidoResponseDTO(pedido));
		}
		return dtos;
	}
	
	public PedidoResponseDTO listarById (Long id) {
		Optional<Pedido> pedidosOptional = repository.findById(id);
		if(pedidosOptional.isEmpty()) {
			throw new NaoEncontradoException("Lancamento venda não encontrado");
		}
		return new PedidoResponseDTO(pedidosOptional.get());
	}
	
	public PedidoResponseDTO buscar(Long id) {
		Optional<Pedido> pedido = repository.findById(id);
		if (pedido.isPresent()) {
			return new PedidoResponseDTO(pedido.get());
		}
		throw new NaoEncontradoException("Usuário não encontrado");
	}
	
	
}
