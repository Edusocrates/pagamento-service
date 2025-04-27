package com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.AtualizarStatusPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.AtualizarStatusPagamentoUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.gateways.PagamentoGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class AtualizarStatusPagamentoHandler implements AtualizarStatusPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;



    @Override
    @Transactional
    public PagamentoResponse atualizarStatus(UUID pagamentoId, AtualizarStatusPagamentoRequest request) {
        log.info("Atualizando status do pagamento com ID: {}", pagamentoId);

        var pagamento = pagamentoGateway.buscarPorId(pagamentoId)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        pagamento.alterarStatus(request.statusPagamento());

        var pagamentoAtualizado = pagamentoGateway.atualizar(pagamento);

        log.info("Pagamento atualizado com sucesso: {}", pagamentoAtualizado);

        return PagamentoResponse.fromDomain(pagamentoAtualizado);
    }
}
