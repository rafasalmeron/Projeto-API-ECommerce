package br.com.api.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.ecommerce.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	Endereco findByCep(String cep);
}
