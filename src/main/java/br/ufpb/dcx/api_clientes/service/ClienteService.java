package br.ufpb.dcx.api_clientes.service;

import br.ufpb.dcx.api_clientes.exception.ClienteIsExistExpetion;
import br.ufpb.dcx.api_clientes.exception.ClienteNotFoundException;
import br.ufpb.dcx.api_clientes.model.Cliente;
import br.ufpb.dcx.api_clientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getClientes() {
        return this.clienteRepository.findAll();
    }

    public Cliente getClienteById(Long clienteID) {
        return this.clienteRepository.findById(clienteID).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado!"));
    }

    public Cliente criarCliente(Cliente cliente) {
        if (this.clienteRepository.existsByCPF(cliente.getCPF())) {
            throw new ClienteIsExistExpetion("Cliente já cadastrado, com esse CPF!");
        }
        return this.clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long clienteID, Cliente cliente) {
        Cliente toUpdate = this.clienteRepository.findById(clienteID).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado!"));
        toUpdate.setCPF(cliente.getCPF());
        toUpdate.setNome(cliente.getNome());
        toUpdate.setTelefone(cliente.getTelefone());
        toUpdate.setEmail(cliente.getEmail());
        return this.clienteRepository.save(toUpdate);
    }

    public void deletarCliente(Long clienteID) {
        Cliente toRemove = this.clienteRepository.findById(clienteID).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado!"));
        this.clienteRepository.delete(toRemove);
    }
}
