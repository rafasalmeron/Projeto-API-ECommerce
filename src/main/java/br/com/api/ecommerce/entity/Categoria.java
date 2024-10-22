package br.com.api.ecommerce.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id categoria")
	private Long id;
	
	@Schema(description = "nome categoria")
	private String nomeCategoria;
	
	@OneToMany
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	public Categoria() {
		
	}
	public Categoria(Long id, String nomeCategoria, Produto produto) {
		super();
		this.id = id;
		this.nomeCategoria = nomeCategoria;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
	
	

}
