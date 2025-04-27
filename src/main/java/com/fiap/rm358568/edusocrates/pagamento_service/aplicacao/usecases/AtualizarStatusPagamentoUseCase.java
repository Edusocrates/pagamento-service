package com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.AtualizarStatusPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;

import java.util.UUID;

public interface AtualizarStatusPagamentoUseCase {
    PagamentoResponse atualizarStatus(UUID pagamentoId, AtualizarStatusPagamentoRequest request);
}
