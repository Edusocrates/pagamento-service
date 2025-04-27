package com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.CriarPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.CriarPagamentoUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.gateways.PagamentoGateway;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.mapper.PagamentoEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CriarPagamentoHandler implements CriarPagamentoUseCase {

    private PagamentoGateway pagamentoGateway;

    private PagamentoEntityMapper mapper;

    @Override
    @Transactional
    public PagamentoResponse criar(CriarPagamentoRequest request) {
        log.info("Iniciando o cadastro do pagamento ");

        var pagamento = mapper.requestToDomain(request);
        var pagamentoSalvo = pagamentoGateway.salvar(pagamento);

        log.info("Pagamento cadastrado com sucesso: {}", pagamentoSalvo);

        return mapper.toResponse(pagamentoSalvo);
    }
}
