package br.com.api.ecommerce.service;

import br.com.api.ecommerce.entity.Categoria;
import br.com.api.ecommerce.entity.Produto;
import br.com.api.ecommerce.repository.CategoriaRepository;
import br.com.api.ecommerce.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto criarProduto(Produto produto) {
        Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        produto.setCategoria(categoria);
        return produtoRepository.save(produto);
    }

    public List<Produto> criarVariosProdutos(List<Produto> produtos) {
        for (Produto produto : produtos) {
            if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
                throw new IllegalArgumentException("ID da categoria não pode ser nulo");
            }

            Categoria categoria = categoriaRepository.findById(produto.getCategoria().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

            produto.setCategoria(categoria);
        }

        return produtoRepository.saveAll(produtos);
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produto.setNome(produtoAtualizado.getNome());
        Categoria categoria = categoriaRepository.findById(produtoAtualizado.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        categoriaRepository.deleteById(id);
    }
}
