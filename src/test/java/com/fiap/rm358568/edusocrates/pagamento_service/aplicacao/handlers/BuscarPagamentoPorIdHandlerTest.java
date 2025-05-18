package com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers;


import com.fiap.rm358568.edusocrates.pagamento_service.API.exceptions.PagamentoNotFoundException;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarPagamentoPorIdHandlerTest {

    @Mock
    private PagamentoGateway pagamentoGateway;

    @Mock
    private PagamentoEntityMapper pagamentoEntityMapper;

    @InjectMocks
    private BuscarPagamentoPorIdHandler buscarPagamentoPorIdHandler;

    @Test
    void buscarPorId_deveRetornarPagamentoResponse_quandoPagamentoExistir() {
        UUID pagamentoId = UUID.randomUUID();
        Pagamento pagamento = new Pagamento(
                pagamentoId,
                UUID.randomUUID(),
                new Cartao("123456789", "TESTE", "12/30", "123"),
                BigDecimal.valueOf(100.0),
                StatusPagamento.APROVADO
        );
        PagamentoResponse pagamentoResponse = PagamentoResponse.fromDomain(pagamento);

        when(pagamentoGateway.buscarPorId(pagamentoId)).thenReturn(Optional.of(pagamento));

        PagamentoResponse response = buscarPagamentoPorIdHandler.buscarPorId(pagamentoId);

        assertNotNull(response);
        assertEquals(pagamentoId, response.id());
        assertEquals(StatusPagamento.APROVADO, response.status());
        verify(pagamentoGateway).buscarPorId(pagamentoId);
    }

    @Test
    void buscarPorId_deveLancarExcecao_quandoPagamentoNaoExistir() {
        UUID pagamentoId = UUID.randomUUID();

        when(pagamentoGateway.buscarPorId(pagamentoId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(PagamentoNotFoundException.class, () ->
                buscarPagamentoPorIdHandler.buscarPorId(pagamentoId)
        );

        assertEquals("Pagamento não encontrado!", exception.getMessage());
        verify(pagamentoGateway).buscarPorId(pagamentoId);
        verifyNoInteractions(pagamentoEntityMapper);
    }
}