package br.com.api.ecommerce.dto;

import java.util.List;

public class PedidoCreateDTO {
    private Long clienteId;
    private List<ProdutoPedidoDTO> produtosPedidos;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ProdutoPedidoDTO> getProdutosPedidos() {
        return produtosPedidos;
    }

    public void setProdutosPedidos(List<ProdutoPedidoDTO> produtosPedidos) {
        this.produtosPedidos = produtosPedidos;
    }
}