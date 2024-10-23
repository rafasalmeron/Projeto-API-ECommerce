package br.com.api.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.api.ecommerce.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
