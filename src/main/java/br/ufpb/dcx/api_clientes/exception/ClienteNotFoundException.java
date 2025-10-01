package br.ufpb.dcx.api_clientes.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String mensagem) {
        super(mensagem);
    }
}
