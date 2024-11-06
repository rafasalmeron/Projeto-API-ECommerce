package br.com.api.ecommerce.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.api.ecommerce.entity.Cliente;

public class ClienteRequestDTO {
	
	private String nome;
    	private String telefone;
    	private String email;
    	private String cpf;
    	private String cep;
	private String senha;
    
    private Set<Cliente> cliente = new HashSet<>();

	public ClienteRequestDTO() {
	}

	public ClienteRequestDTO(String nome, String telefone, String email, String cpf, String cep, Set<Cliente> cliente, String senha) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.cpf = cpf;
		this.cep = cep;
		this.cliente = cliente;
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setNome(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
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

	public Set<Cliente> getCliente() {
		return cliente;
	}

	public void setCliente(Set<Cliente> cliente) {
		this.cliente = cliente;
	}
	
	

}
