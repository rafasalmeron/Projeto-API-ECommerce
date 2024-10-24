package br.com.api.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProdutoPedidoPK implements Serializable {

	@Column(name = "pedido_id")
	private Long pedidoId;
	@Column(name = "produto_id")
	private Long produtoId;

	public ProdutoPedidoPK() {
	}

	public ProdutoPedidoPK(Long pedidoId, Long produtoId) {
		this.pedidoId = pedidoId;
		this.produtoId = produtoId;
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProdutoPedidoPK)) return false;
		ProdutoPedidoPK that = (ProdutoPedidoPK) o;
		return Objects.equals(getPedidoId(), that.getPedidoId()) &&
				Objects.equals(getProdutoId(), that.getProdutoId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPedidoId(), getProdutoId());
	}
}
