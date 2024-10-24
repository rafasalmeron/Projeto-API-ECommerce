package br.com.api.ecommerce.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id categoria")
	private Long id;
	
	@Schema(description = "nome categoria")
	@NotBlank
	private String nome;
	
	@OneToMany
	@JoinColumn(name = "id_produto")
	@JsonManagedReference
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
