package br.ufpb.dcx.api_clientes.controller;

import br.ufpb.dcx.api_clientes.dto.ClienteDTO;
import br.ufpb.dcx.api_clientes.mapper.ClienteMapper;
import br.ufpb.dcx.api_clientes.model.Cliente;
import br.ufpb.dcx.api_clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/api")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @GetMapping(path = "/clientes")
    public List<Cliente> listaTodosClientes() {
        return clienteService.getClientes();
    }

    @GetMapping(path = "/clientes/{clienteID}")
    public ClienteDTO listaCliente(@PathVariable Long clienteID) {
        Cliente c = clienteService.getClienteById(clienteID);
        return clienteMapper.toDTO(c);
    }

    @DeleteMapping(path = "/clientes/{clienteID}")
    public void deletaCliente(@PathVariable Long clienteID) {
        clienteService.deletarCliente(clienteID);
    }

    @PostMapping(path = "/clientes")
    public ClienteDTO criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        this.clienteService.criarCliente(cliente);
        return  clienteMapper.toDTO(cliente);
    }

    @PutMapping(path = "/clientes/{clienteID}")
    public ClienteDTO atualizarCliente(@Valid @PathVariable Long clienteID, @RequestBody ClienteDTO clienteDTO) {
        Cliente c = clienteMapper.toEntity(clienteDTO);
        this.clienteService.atualizarCliente(clienteID, c);
        return clienteMapper.toDTO(c);
    }

}
