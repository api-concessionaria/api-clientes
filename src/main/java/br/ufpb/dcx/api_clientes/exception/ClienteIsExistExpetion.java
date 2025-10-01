package br.ufpb.dcx.api_clientes.exception;

public class ClienteIsExistExpetion extends RuntimeException {
    public ClienteIsExistExpetion(String mensagem) {
        super(mensagem);
    }
}
