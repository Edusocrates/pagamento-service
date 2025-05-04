package com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers;


import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.CriarPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Cartao;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Pagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.gateways.PagamentoGateway;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.mapper.PagamentoEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarPagamentoHandlerTest {

    @Mock
    private PagamentoGateway pagamentoGateway;

    @InjectMocks
    private CriarPagamentoHandler criarPagamentoHandler;

    @Test
    void criar_deveRetornarPagamentoResponse_quandoPagamentoForCriadoComSucesso() {
        CriarPagamentoRequest request = new CriarPagamentoRequest(
                UUID.randomUUID(),
                "1234567890123456",
                "TESTE",
                "12/30",
                "123",
                BigDecimal.valueOf(100.0),
                "APROVADO"
        );

        Pagamento pagamento = PagamentoEntityMapper.requestToDomain(request);

        Pagamento pagamentoSalvo = new Pagamento(
                UUID.randomUUID(),
                pagamento.getPedidoId(),
                pagamento.getCartao(),
                pagamento.getValor(),
                pagamento.getStatus()
        );

        PagamentoResponse pagamentoResponse = PagamentoEntityMapper.toResponse(pagamentoSalvo);

        when(pagamentoGateway.salvar(pagamento)).thenReturn(pagamentoSalvo);

        PagamentoResponse response = criarPagamentoHandler.criar(request);

        assertNotNull(response);
        assertEquals(pagamentoSalvo.getId(), response.id());
        assertEquals(pagamentoSalvo.getPedidoId(), response.pedidoId());
        verify(pagamentoGateway).salvar(pagamento);
    }

    @Test
    void criar_deveLancarExcecao_quandoPagamentoNaoForSalvo() {
        CriarPagamentoRequest request = new CriarPagamentoRequest(
                UUID.randomUUID(),
                "1234567890123456",
                "TESTE",
                "12/30",
                "123",
                BigDecimal.valueOf(100.0),
                "APROVADO"
        );

        Pagamento pagamento = PagamentoEntityMapper.requestToDomain(request);

        when(pagamentoGateway.salvar(pagamento)).thenThrow(new RuntimeException("Erro ao salvar pagamento"));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                criarPagamentoHandler.criar(request)
        );

        assertEquals("Erro ao salvar pagamento", exception.getMessage());
        verify(pagamentoGateway).salvar(pagamento);
    }
}