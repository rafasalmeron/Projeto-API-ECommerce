package br.com.api.ecommerce.entity;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProdutoPedido {

	@EmbeddedId
	private ProdutoPedidoPK id = new ProdutoPedidoPK();

	@NotNull
	@Min(1)
	@Schema(description = "Quantidade do item")
	private Long quantidade;

	@ManyToOne
	@MapsId("produtoId")
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@ManyToOne
	@MapsId("pedidoId")
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	public ProdutoPedido(ProdutoPedidoPK id, @NotNull Long quantidade, Produto produto) {
		this.id = id;
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public ProdutoPedido() {
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public ProdutoPedidoPK getId() {
		return id;
	}

	public void setId(ProdutoPedidoPK id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoPedido other = (ProdutoPedido) obj;
		return Objects.equals(id, other.id);
	}
}
