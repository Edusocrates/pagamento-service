package com.fiap.rm358568.edusocrates.pagamento_service.dominio.gateways;

import java.util.UUID;

public interface ProvedorPagamentoGateway {

    boolean processarPagamento(UUID pagamentoId, String numeroCartao, String nomeTitular, String validade, String cvv, double valor);
    boolean cancelarPagamento(UUID pagamentoId);
}
