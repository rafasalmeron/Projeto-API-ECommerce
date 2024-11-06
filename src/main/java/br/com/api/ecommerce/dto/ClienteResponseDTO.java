package br.com.api.ecommerce.dto;

import br.com.api.ecommerce.entity.Cliente;

public class ClienteResponseDTO {
	
	private String nome;
	private String email;
	private String senha;
	
	public ClienteResponseDTO(){
	}

	public ClienteResponseDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() { return senha; }

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
