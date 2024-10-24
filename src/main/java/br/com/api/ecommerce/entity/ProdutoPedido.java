package br.com.api.ecommerce.entity;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ProdutoPedido {

	@EmbeddedId
	private ProdutoPedidoPK id = new ProdutoPedidoPK();
	
	@NotBlank
	@Schema(description = "Quatidade do item")
	private Long quantidade;
	

	public ProdutoPedido(ProdutoPedidoPK id, @NotBlank Long quantidade) {
		
		this.id = id;
		this.quantidade = quantidade;
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