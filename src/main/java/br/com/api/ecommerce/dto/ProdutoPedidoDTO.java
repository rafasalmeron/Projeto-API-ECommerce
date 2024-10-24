package br.com.api.ecommerce.dto;

public class ProdutoPedidoDTO {
    private Long produtoId;
    private Long quantidade;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}

