package br.com.api.ecommerce.service;

import br.com.api.ecommerce.entity.ProdutoPedido;
import br.com.api.ecommerce.entity.ProdutoPedidoPK;
import br.com.api.ecommerce.exception.NaoEncontradoException;
import br.com.api.ecommerce.repository.ProdutoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoPedidoService {

    @Autowired
    private ProdutoPedidoRepository produtoPedidoRepository;

    public List<ProdutoPedido> listarTodos() {
        return produtoPedidoRepository.findAll();
    }

    public ProdutoPedido buscarPorId(ProdutoPedidoPK id) {
        return produtoPedidoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("ProdutoPedido não encontrado para o ID: " + id));
    }

    public ProdutoPedido criarProdutoPedido(ProdutoPedido produtoPedido) {
        return produtoPedidoRepository.save(produtoPedido);
    }

    public ProdutoPedido atualizarProdutoPedido(ProdutoPedidoPK id, ProdutoPedido produtoPedido) {
        ProdutoPedido existente = produtoPedidoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("ProdutoPedido não encontrado para o ID: " + id));

        existente.setQuantidade(produtoPedido.getQuantidade());
        return produtoPedidoRepository.save(existente);
    }

    public void deletarProdutoPedido(ProdutoPedidoPK id) {
        ProdutoPedido existente = produtoPedidoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("ProdutoPedido não encontrado para o ID: " + id));
        produtoPedidoRepository.delete(existente);
    }
}

