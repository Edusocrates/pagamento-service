package com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pagamento_service.API.exceptions.PagamentoNotFoundException;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.BuscarPagamentoPorPedidoIdUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Pagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.gateways.PagamentoGateway;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.mapper.PagamentoEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BuscarPagamentoPorPedidoIdHandler implements BuscarPagamentoPorPedidoIdUseCase {

    private final PagamentoGateway pagamentoGateway;

    private final PagamentoEntityMapper pagamentoEntityMapper;


    @Override
    public PagamentoResponse consultar(UUID pedidoId) {
        log.info("Buscando pagamento para o pedido ID: {}", pedidoId);

        Pagamento pagamento = pagamentoGateway.buscarPorPedidoId(pedidoId)
                .orElseThrow(() -> new PagamentoNotFoundException("Pagamento não encontrado para o pedido ID: " + pedidoId));

        log.info("Pagamento encontrado: {}", pagamento);

        return pagamentoEntityMapper.toResponse(pagamento);
    }
}
