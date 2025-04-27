package com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.CriarPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;

public interface CriarPagamentoUseCase {
    PagamentoResponse criar(CriarPagamentoRequest request);
}
