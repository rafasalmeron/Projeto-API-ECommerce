package br.com.api.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.ecommerce.Entity.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido, Long> {

}
