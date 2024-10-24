package br.com.api.ecommerce.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@ManyToMany
	@JoinColumn(name = "id_produto")
	private List<Produto> produto;
	
	private LocalDate dataPedido;
	
	
    public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Pedido() {
		
	}

	
	public Pedido(Long id, Cliente cliente, List<Produto> produto, LocalDate dataPedido) {
		this.id = id;
		this.cliente = cliente;
		this.produto = produto;
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