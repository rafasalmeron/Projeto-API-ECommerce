package br.com.api.ecommerce.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import br.com.api.ecommerce.entity.Pedido;
import br.com.api.ecommerce.entity.ProdutoPedido;

public class PedidoResponseDTO {

	private Long id;
	private String nomeCliente;
	private Set<String> nomesProdutos = new HashSet<>();
	private double valorTotal;

	public PedidoResponseDTO() {
	}

	public PedidoResponseDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.nomeCliente = pedido.getCliente().getNome();
		this.valorTotal = pedido.getProdutosPedidos().stream()
				.mapToDouble(pp -> pp.getProduto().getValor() * pp.getQuantidade())
				.sum();
		this.nomesProdutos = pedido.getProdutosPedidos().stream()
				.map(pp -> pp.getProduto().getNome() + " (Quantidade: " + pp.getQuantidade() + ")")
				.collect(Collectors.toSet());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Set<String> getNomesProdutos() {
		return nomesProdutos;
	}

	public void setNomesProdutos(Set<String> nomesProdutos) {
		this.nomesProdutos = nomesProdutos;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
