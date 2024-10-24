package br.com.api.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoDTO {

    private Long id;

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @NotNull(message = "Valor é obrigatório")
    @Min(value = 1, message = "O valor deve ser maior que zero")
    private Double valor;

    @NotNull(message = "Categoria é obrigatória")
    private Long categoriaId;

    public ProdutoDTO() {}

    public ProdutoDTO(Long id, String nome, Double valor, Long categoriaId) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.categoriaId = categoriaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
