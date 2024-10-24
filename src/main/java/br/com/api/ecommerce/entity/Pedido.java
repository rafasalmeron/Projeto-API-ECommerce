package br.com.api.ecommerce.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProdutoPedido> produtosPedidos = new HashSet<>();

	public Set<ProdutoPedido> getProdutosPedidos() {
		return produtosPedidos;
	}

	public void setProdutosPedidos(Set<ProdutoPedido> produtosPedidos) {
		this.produtosPedidos = produtosPedidos;
	}

	private LocalDate dataPedido;

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Pedido() {

	}

	public Pedido(Long id, Cliente cliente, LocalDate dataPedido) {
		this.id = id;
		this.cliente = cliente;
		this.dataPedido = dataPedido;
	}

	@PrePersist
	private void onCreate() {
		this.dataPedido = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



}