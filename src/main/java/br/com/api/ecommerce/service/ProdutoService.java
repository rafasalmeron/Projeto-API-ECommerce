package br.com.api.ecommerce.service;

import br.com.api.ecommerce.dto.ProdutoDTO;
import br.com.api.ecommerce.entity.Categoria;
import br.com.api.ecommerce.entity.Produto;
import br.com.api.ecommerce.repository.CategoriaRepository;
import br.com.api.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public List<ProdutoDTO> buscarPorCategoria(Long categoriaId) {
        List<Produto> produtos = produtoRepository.findByCategoriaId(categoriaId);

        // Converter Produto para ProdutoDTO
        return produtos.stream()
                .map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getValor(),
                        produto.getCategoria().getId()
                ))
                .toList();
    }

    public Page<ProdutoDTO> listarTodos(Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findAll(pageable);

        return produtos.map(produto -> new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getCategoria().getId()
        ));
    }


    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto criarProduto(ProdutoDTO produtoDTO) {
        // Converter ProdutoDTO em Produto
        Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public List<Produto> criarVariosProdutos(List<ProdutoDTO> produtosDTO) {
        List<Produto> produtos = produtosDTO.stream().map(produtoDTO -> {
            Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

            Produto produto = new Produto();
            produto.setNome(produtoDTO.getNome());
            produto.setValor(produtoDTO.getValor());
            produto.setCategoria(categoria);

            return produto;
        }).toList();

        return produtoRepository.saveAll(produtos);
    }

    public Produto atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }
}
