package br.com.api.ecommerce.Repository;

import br.com.api.ecommerce.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
