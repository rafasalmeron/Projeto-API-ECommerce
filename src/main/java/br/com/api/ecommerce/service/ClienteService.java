package br.com.api.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.api.ecommerce.dto.ClienteRequestDTO;
import br.com.api.ecommerce.dto.ClienteResponseDTO;
import br.com.api.ecommerce.entity.Cliente;
import br.com.api.ecommerce.entity.Endereco;
import br.com.api.ecommerce.exception.EmailException;
import br.com.api.ecommerce.exception.NaoEncontradoException;
import br.com.api.ecommerce.repository.ClienteRepository;
import br.com.api.ecommerce.repository.EnderecoRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {
	
	@Autowired
	private final ClienteRepository clienteRepository;
	@Autowired
	private final EnderecoRepository enderecoRepository;
	
	public ClienteService (ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
		this.clienteRepository = clienteRepository;
		this.enderecoRepository = enderecoRepository;
	}
	
	public List<ClienteResponseDTO> listarClientes() {
		List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponseDTO> dtos = new ArrayList<>();
        for (Cliente cliente : clientes) {
            dtos.add(new ClienteResponseDTO(cliente));
        }
        return dtos;
    }
	
	 public ClienteResponseDTO buscarPorId(Long id) {
		 Optional<Cliente> cliente = clienteRepository.findById(id);
	        if (cliente.isPresent()) {
	            return new ClienteResponseDTO(cliente.get());
	        }
	        throw new NaoEncontradoException("Cliente não encontrado");
	 }
	 
	 @Transactional
		public ClienteResponseDTO inserir(ClienteRequestDTO dto) throws IOException {
			Optional<Cliente> c = clienteRepository.findByEmail(dto.getEmail());
			if (c.isPresent()) {
				throw new EmailException("Email existente!");
			}

			Cliente cliente = new Cliente();
			cliente.setNome(dto.getNome());
			cliente.setEmail(dto.getEmail());
			cliente.setCpf(dto.getCpf());
			cliente.setCep(dto.getCep());

			Endereco endereco = enderecoRepository.findByCep(dto.getCep());
			if (endereco != null) {
				cliente.setEndereco(endereco);
			} else {
				RestTemplate rs = new RestTemplate();
				String uri = "https://viacep.com.br/ws/" + dto.getCep() + "/json/";

				try {
					Endereco enderecoViaCep = rs.getForObject(uri, Endereco.class);

					if (enderecoViaCep != null && enderecoViaCep.getCep() != null) {
						String cepSemTraco = enderecoViaCep.getCep().replaceAll("-", "");
						enderecoViaCep.setCep(cepSemTraco);

						endereco = new Endereco();
						endereco.setCep(enderecoViaCep.getCep());
						endereco.setBairro(enderecoViaCep.getBairro());
						endereco.setLocalidade(enderecoViaCep.getLocalidade());
						endereco.setLogradouro(enderecoViaCep.getLogradouro());

						enderecoRepository.save(endereco);
						cliente.setEndereco(endereco);
					} else {
						throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "CEP não encontrado");
					}
				} catch (HttpClientErrorException e) {
					throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Erro ao buscar CEP: " + dto.getCep());
				}
			}

			cliente = clienteRepository.save(cliente);
			return new ClienteResponseDTO(cliente);
		}
	 
	 
	 public ClienteResponseDTO atualizarCliente(Long id, Cliente clienteAtualizado) {
		    Cliente cliente = clienteRepository.findById(id)
		            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
		    
		    cliente.setNome(clienteAtualizado.getNome());
		    cliente.setTelefone(clienteAtualizado.getTelefone());
		    cliente.setEmail(clienteAtualizado.getEmail());
		    cliente.setCpf(clienteAtualizado.getCpf());

		    Endereco endereco = enderecoRepository.findByCep(clienteAtualizado.getCep());
		    if (endereco == null) {
		        RestTemplate restTemplate = new RestTemplate();
		        String uri = "https://viacep.com.br/ws/" + clienteAtualizado.getCep() + "/json/";
		        
		        try {
		            Endereco enderecoViaCep = restTemplate.getForObject(uri, Endereco.class);
		            if (enderecoViaCep != null && enderecoViaCep.getCep() != null) {
		                endereco = new Endereco();
		                endereco.setCep(enderecoViaCep.getCep().replaceAll("-", ""));
		                endereco.setBairro(enderecoViaCep.getBairro());
		                endereco.setLocalidade(enderecoViaCep.getLocalidade());
		                endereco.setLogradouro(enderecoViaCep.getLogradouro());
		                enderecoRepository.save(endereco);
		            } else {
		                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "CEP não encontrado");
		            }
		        } catch (Exception e) {
		            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Erro ao buscar CEP");
		        }
		    }

		    cliente.setEndereco(endereco);
		    cliente = clienteRepository.save(cliente);
		    return new ClienteResponseDTO(cliente);
		}

	 
	    public void deletarCliente(Long id) {
		clienteRepository.deleteById(id);
	    }
	 

}
