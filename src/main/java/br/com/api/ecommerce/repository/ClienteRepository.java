package br.com.api.ecommerce.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.api.ecommerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Optional<Cliente> findByNome(String nome);

    Optional<Cliente> findByEmail(String email);

	

}
