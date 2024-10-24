package br.com.api.ecommerce.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.api.ecommerce.entity.Cliente;
import br.com.api.ecommerce.entity.Pedido;
import br.com.api.ecommerce.entity.Produto;

public class PedidoResponseDTO {
	
	
	private Long id;
	private String nomeProduto;
	private String nomeCliente;
	private double valor;
	
	private Cliente cliente;
	private Set<Produto> produtos = new HashSet<>();
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNomeProduto() {
		return nomeProduto;
	}


	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}


	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}

	public PedidoResponseDTO() {
	
	}


	public PedidoResponseDTO( Cliente cliente,Produto produto,Pedido pedido) {
	
		this.id = pedido.getId();
		this.nomeCliente = cliente.getNome();
		this.valor =produto.getValor();
		
	}
	
	public PedidoResponseDTO( Pedido pedido) {
		
		this.id = pedido.getId();
		
	}

}
