# Projeto Final - API ECommerce

Este projeto é uma API de E-commerce desenvolvida em Java com Spring Boot, com o objetivo de criar um sistema para a venda de produtos pela internet. O projeto foi desenvolvido em duas partes: uma em grupo e uma individual, cada aluno sendo responsável por adicionar funcionalidades relevantes.

## Funcionalidades

- **Categoria**: Inserção e edição de categorias de produtos.
- **Produto**: Inserção e edição de produtos. Cada produto deve obrigatoriamente estar associado a uma categoria.
- **Cliente**: Inserção e edição de clientes. O cadastro inclui validação de CEP através de um serviço externo (ViaCEP) e envio de e-mail ao cliente ao realizar alterações.
- **Pedido**: Inserção e edição de pedidos. Cada pedido deve estar associado a um cliente e conter produtos, com informações como valor da venda, desconto e quantidade. Os pedidos têm status que podem ser atualizados.
- **Listagem de Pedidos**: Listagem de pedidos pelo número, exibindo o total do pedido.

## Estrutura do Projeto

O projeto segue o padrão arquitetural em camadas, sendo organizado da seguinte forma:

- **Entity**: Representação das entidades do sistema.
- **Service**: Implementação da lógica de negócios.
- **Repository**: Interface para comunicação com o banco de dados.
- **Controller**: Camada responsável por expor os endpoints da API.
- **DTO**: Utilização de DTOs para transferência de dados entre camadas.
  
## Validações e Tratamento de Erros

- **Validações**: Validação de dados do cliente como nome, telefone, e-mail e CPF.
- **Tratamento de Exceções**: Implementação de uma classe com a anotação `@ControllerAdvice` para tratar erros globalmente. Além disso, exceções específicas foram criadas para os possíveis erros da API.
- **Enum**: Utilização de enums para representar valores constantes e tratamento de erros relacionados.

## Serviço Externo

O sistema utiliza o serviço [ViaCEP](https://viacep.com.br/ws/{cep}/json/) para consulta de endereço a partir do CEP fornecido pelo cliente.

## Documentação

A documentação da API foi feita utilizando o **Swagger**, proporcionando uma interface interativa para explorar os endpoints e testar as funcionalidades.

## Tecnologias Utilizadas

- **Java** com **Spring Boot** para desenvolvimento da API.
- **Swagger** para documentação da API.
- **ViaCEP** para consulta de endereço via CEP.

## Endpoints Disponíveis

- **GET**: Obtenção de informações (clientes, produtos, pedidos).
- **POST**: Inserção de novas informações.
- **PUT**: Atualização de informações existentes.
- **DELETE**: Remoção de informações.

## Parte Individual

Cada integrante do grupo implementou uma funcionalidade adicional para explorar sua criatividade e aplicar os conhecimentos adquiridos durante o curso. A apresentação dessas funcionalidades será feita no último dia de aula.

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/usuario/projeto-api-ecommerce.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   git clone https://github.com/usuario/projeto-api-ecommerce.git
   ```
3. Execute o projeto utilizando o Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

## Contribuição
  Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.

<h2 id="desenvolvedores">:construction_worker: Desenvolvedores</h2>

<table> 
  <tr>
    <td align="center">
      <a href="https://github.com/matheusgouvea">
        <img style="border-radius: 50%" src="https://avatars.githubusercontent.com/u/49907532?v=4" width="100px" alt="Foto Perfil"/>
        <br />
        <sub>
          <b>Matheus Gouvea</b>
        </sub>
      </a>
      <a href="https://github.com/matheusgouvea"></a>
    </td>
    <td align="center">
      <a href="https://github.com/Suru13">
        <img style="border-radius: 50%" src="https://avatars.githubusercontent.com/u/78802877?v=4" width="100px" alt="Foto Perfil"/>
        <br />
        <sub>
          <b>Suru</b>
        </sub>
      </a>
      <a href="https://github.com/Suru13"></a>
    </td>
    <td align="center">
      <a href="https://github.com/ricardo-lopes130">
        <img style="border-radius: 50%" src="https://avatars.githubusercontent.com/u/177360954?v=4" width="100px" alt="Foto Perfil"/>
        <br />
        <sub>
          <b>Ricardo Lopes</b>
        </sub>
      </a> 
      <a href="https://github.com/ricardo-lopes130"></a>
    </td>
    <td align="center">
      <a href="https://github.com/rafasalmeron">
        <img style="border-radius: 50%" src="https://avatars.githubusercontent.com/u/94733546?v=4" width="100px" alt="Foto Perfil"/>
        <br />
        <sub>
          <b>Rafael Salmeron</b>
        </sub>
      </a> 
      <a href="https://github.com/rafasalmeron"></a>
    </td>
    <td align="center">
      <a href="https://github.com/ryansouza9">
        <img style="border-radius: 50%" src="https://avatars.githubusercontent.com/u/178517635?v=4" width="100px" alt="Foto Perfil"/>
        <br />
        <sub>
          <b>Ryan</b>
        </sub>
      </a> 
      <a href="https://github.com/ryansouza9"></a>
  </tr>
</table>

👋🏽 Entre em contato!
