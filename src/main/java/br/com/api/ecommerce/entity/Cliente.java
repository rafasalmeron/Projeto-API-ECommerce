package br.com.api.ecommerce.entity;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 60)
	private String nome;
	
	@Schema(description = "CPF do cliente")
	@NotBlank(message = "Por favor insira um CPF")
	@CPF
	private String cpf;
	
	@Email
	@Schema(description = "Email do cliente")
	@NotBlank(message = "Por favor insira um email")
	private String email;

}