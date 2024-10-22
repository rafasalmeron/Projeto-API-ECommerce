package br.com.api.ecommerce.repository;

import br.com.api.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoPedidoRepository extends JpaRepository<Produto, Long> {
}
