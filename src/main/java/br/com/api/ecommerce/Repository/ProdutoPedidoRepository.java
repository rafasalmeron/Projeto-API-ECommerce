package br.com.api.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.ecommerce.Entity.Produto;

public interface ProdutoPedidoRepository extends JpaRepository<Produto, Long> {

}
