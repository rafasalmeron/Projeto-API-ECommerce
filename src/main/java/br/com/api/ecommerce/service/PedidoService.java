package br.com.api.ecommerce.service;

import br.com.api.ecommerce.dto.PedidoCreateDTO;
import br.com.api.ecommerce.dto.PedidoResponseDTO;
import br.com.api.ecommerce.dto.ProdutoPedidoDTO;
import br.com.api.ecommerce.entity.Cliente;
import br.com.api.ecommerce.entity.Pedido;
import br.com.api.ecommerce.entity.Produto;
import br.com.api.ecommerce.entity.ProdutoPedido;
import br.com.api.ecommerce.repository.ClienteRepository;
import br.com.api.ecommerce.repository.PedidoRepository;
import br.com.api.ecommerce.repository.ProdutoPedidoRepository;
import br.com.api.ecommerce.exception.NaoEncontradoException;
import br.com.api.ecommerce.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ProdutoPedidoRepository produtoPedidoRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoRepository produtoRepository;

	public PedidoService(PedidoRepository pedidoRepository,
						 ProdutoPedidoRepository produtoPedidoRepository,
						 ClienteRepository clienteRepository,
						 ProdutoRepository produtoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.produtoPedidoRepository = produtoPedidoRepository;
		this.produtoRepository = produtoRepository;
		this.clienteRepository = clienteRepository;
	}

	public List<PedidoResponseDTO> listar() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos.stream().map(PedidoResponseDTO::new).collect(Collectors.toList());
	}

	public PedidoResponseDTO listarById(Long id) {
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Pedido não encontrado para o ID: " + id));
		return new PedidoResponseDTO(pedido);
	}

	public Pedido criarPedido(PedidoCreateDTO pedidoCreateDTO) {
		Pedido pedido = new Pedido();

		Cliente cliente = clienteRepository.findById(pedidoCreateDTO.getClienteId())
				.orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado"));
		pedido.setCliente(cliente);

		Pedido novoPedido = pedidoRepository.save(pedido);


		for (ProdutoPedidoDTO produtoPedidoDTO : pedidoCreateDTO.getProdutosPedidos()) {
			Produto produto = produtoRepository.findById(produtoPedidoDTO.getProdutoId())
					.orElseThrow(() -> new NaoEncontradoException("Produto não encontrado"));

			ProdutoPedido produtoPedido = new ProdutoPedido();
			produtoPedido.setPedido(novoPedido);
			produtoPedido.setProduto(produto);
			produtoPedido.setQuantidade(produtoPedidoDTO.getQuantidade());

			produtoPedidoRepository.save(produtoPedido);
		}

		return novoPedido;
	}


	public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
		Pedido pedidoExistente = pedidoRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Pedido não encontrado para o ID: " + id));

		pedidoExistente.setCliente(pedidoAtualizado.getCliente());

		produtoPedidoRepository.deleteAll(pedidoExistente.getProdutosPedidos());
		for (ProdutoPedido produtoPedido : pedidoAtualizado.getProdutosPedidos()) {
			produtoPedido.setPedido(pedidoExistente);
			produtoPedidoRepository.save(produtoPedido);
		}

		return pedidoRepository.save(pedidoExistente);
	}

	public void deletarPedido(Long id) {
		Pedido pedidoExistente = pedidoRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Pedido não encontrado para o ID: " + id));

		produtoPedidoRepository.deleteAll(pedidoExistente.getProdutosPedidos());
		pedidoRepository.delete(pedidoExistente);
	}
}
