package br.com.api.ecommerce.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id categoria")
	private Long id;
	
	@Schema(description = "nome categoria")
	private String nome;
	
	@OneToMany
	@JoinColumn(name = "id_produto")
	private List<Produto> produto;
	
	public Categoria() {
	}

	public Categoria(Long id, String nomeCategoria, Produto produto) {
		this.id = id;
		this.nome = nomeCategoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeCategoria) {
		this.nome = nomeCategoria;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
}
