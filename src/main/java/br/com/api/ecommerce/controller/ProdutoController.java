package br.com.api.ecommerce.controller;

import br.com.api.ecommerce.dto.ProdutoDTO;
import br.com.api.ecommerce.entity.Produto;
import br.com.api.ecommerce.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listarTodos(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<ProdutoDTO> produtosPaginados = produtoService.listarTodos(pageable);
        return ResponseEntity.ok(produtosPaginados);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProduto(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoService.buscarPorId(id);

        return produtoOptional
                .map(produto -> ResponseEntity.ok(new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getValor(),
                        produto.getCategoria().getId()
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar-por-categoria")
    public ResponseEntity<List<ProdutoDTO>> buscarPorCategoria(@RequestParam Long categoriaId) {
        List<ProdutoDTO> produtos = produtoService.buscarPorCategoria(categoriaId);
        if (produtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(produtos);
        }
    }



    @PostMapping("/criar")
    public ResponseEntity<?> criarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        try {
            Produto novoProduto = produtoService.criarProduto(produtoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar produto: " + e.getMessage());
        }
    }

    @PostMapping("/criar-varios")
    public ResponseEntity<?> criarVariosProdutos(@Valid @RequestBody List<ProdutoDTO> produtosDTO) {
        try {
            List<Produto> novosProdutos = produtoService.criarVariosProdutos(produtosDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novosProdutos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar produtos: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        try {
            Produto produto = produtoService.atualizarProduto(id, produtoDTO);
            return ResponseEntity.ok(produto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
