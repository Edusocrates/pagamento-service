package com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Pagamento;

import java.util.UUID;

public interface BuscarPagamentoPorPedidoIdUseCase {

    PagamentoResponse consultar(UUID pedidoId);
}
