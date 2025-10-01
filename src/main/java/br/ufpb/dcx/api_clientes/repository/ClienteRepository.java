package br.ufpb.dcx.api_clientes.repository;

import br.ufpb.dcx.api_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public boolean existsByCPF(String CPF);
}
