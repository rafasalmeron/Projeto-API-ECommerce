package br.com.api.ecommerce.repository;

import br.com.api.ecommerce.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository <Pedido, Long> {
	
}
