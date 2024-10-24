package br.com.api.ecommerce.service;

import br.com.api.ecommerce.dto.PedidoResponseDTO;
import br.com.api.ecommerce.entity.Pedido;
import br.com.api.ecommerce.exception.NaoEncontradoException;
import br.com.api.ecommerce.repository.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

	private final PedidoRepository repository;

	public PedidoService(PedidoRepository repository) {
		this.repository = repository;
	}

	public Page<PedidoResponseDTO> listar(Pageable pageable) {
		Page<Pedido> pedidos = repository.findAll(pageable);
		return pedidos.map(PedidoResponseDTO::new);
	}


	public PedidoResponseDTO listarById(Long id) {
		Optional<Pedido> pedido = repository.findById(id);
		if (pedido.isEmpty()) {
			throw new NaoEncontradoException("Pedido não encontrado para o ID: " + id);
		}
		return new PedidoResponseDTO(pedido.get());
	}

	public Pedido criarPedido(Pedido pedido) {
		return repository.save(pedido);
	}

	public List<Pedido> criarVariosPedidos(List<Pedido> pedidos) {
		return repository.saveAll(pedidos);
	}

	public Pedido atualizarPedido(Long id, Pedido pedido) {
		Pedido existente = repository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Pedido não encontrado para o ID: " + id));

		existente.setCliente(pedido.getCliente());
		existente.setProduto(pedido.getProduto());
		existente.setDataPedido(pedido.getDataPedido());

		return repository.save(existente);
	}

	public void deletarPedido(Long id) {
		Pedido existente = repository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Pedido não encontrado para o ID: " + id));

		repository.delete(existente);
	}
}
