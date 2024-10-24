package br.com.api.ecommerce.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import br.com.api.ecommerce.entity.Pedido;
import br.com.api.ecommerce.entity.Produto;

public class PedidoResponseDTO {

	private Long id;
	private String nomeCliente;
	private Set<String> nomesProdutos = new HashSet<>();
	private double valor;

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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public PedidoResponseDTO() {
	}

	public PedidoResponseDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.nomeCliente = pedido.getCliente().getNome();
		this.valor = pedido.getProduto().stream().mapToDouble(Produto::getValor).sum();
		this.nomesProdutos = pedido.getProduto().stream()
				.map(Produto::getNome)
				.collect(Collectors.toSet());
	}
}

