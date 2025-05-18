package com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pagamento_service.API.exceptions.PagamentoNotFoundException;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.BuscarPagamentoPorIdUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.gateways.PagamentoGateway;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.mapper.PagamentoEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BuscarPagamentoPorIdHandler implements BuscarPagamentoPorIdUseCase {

    private final PagamentoGateway pagamentoGateway;

    private final PagamentoEntityMapper pagamentoEntityMapper;


    @Override
    public PagamentoResponse buscarPorId(UUID pagamentoId) {
        log.info("Buscando pagamento com ID: {}", pagamentoId);

        var pagamento = pagamentoGateway.buscarPorId(pagamentoId)
                .orElseThrow(() -> new PagamentoNotFoundException("Pagamento não encontrado!"));

        var pagamentoResponse = pagamentoEntityMapper.toResponse(pagamento);

        log.info("Pagamento encontrado: {}", pagamentoResponse);

        return pagamentoResponse;
    }
}
