package br.com.api.ecommerce.repository;

import br.com.api.ecommerce.entity.ProdutoPedido;
import br.com.api.ecommerce.entity.ProdutoPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, ProdutoPedidoPK> {
    List<ProdutoPedido> findByProdutoId(Long produtoId);
}
