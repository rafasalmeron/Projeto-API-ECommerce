package br.com.api.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome obrigatório")
	private String nome;
	@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve seguir o formato (XX) XXXXX-XXXX")
	private String telefone;
	
	@Email
	@Schema(description = "Email do cliente")
	@NotBlank(message = "Email obrigatório")
	private String email;

	@NotBlank(message = "Senha obrigatória")
	@Min(value = 6, message = "Senha deve ter no mínimo 6 caracteres")
	private String senha;

	@Schema(description = "CPF do cliente")
	@NotBlank(message = "Por favor insira um CPF")
	@CPF
	private String cpf;

	@Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve seguir o formato XXXXX-XXX")
	private String cep;

	private String senha;

	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private List<Pedido> pedidos;
	
	@ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

	public Cliente() {
	}

	public Cliente(String nome, String telefone, String email, String cpf, String cep, String senha) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.cpf = cpf;
		this.cep = cep;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public @NotBlank(message = "Senha obrigatória") @Min(value = 6, message = "Senha deve ter no mínimo 6 caracteres") String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
