package com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers;


import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.AtualizarStatusPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Cartao;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Pagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.gateways.PagamentoGateway;
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
class AtualizarStatusPagamentoHandlerTest {

    @Mock
    private PagamentoGateway pagamentoGateway;

    @InjectMocks
    private AtualizarStatusPagamentoHandler atualizarStatusPagamentoHandler;

    @Test
    void atualizarStatus_deveAtualizarStatusQuandoPagamentoExistir() {
        UUID pagamentoId = UUID.randomUUID();
        AtualizarStatusPagamentoRequest request = new AtualizarStatusPagamentoRequest(StatusPagamento.APROVADO);

        // Criando instância real de Pagamento
        Pagamento pagamento = new Pagamento(
                pagamentoId,
                UUID.randomUUID(),
                new Cartao("123456789", "TESTE", "12/30", "123"),
                BigDecimal.valueOf(100.0),
                StatusPagamento.EM_PROCESSAMENTO
        );

        // Simulando o comportamento do gateway
        when(pagamentoGateway.buscarPorId(pagamentoId)).thenReturn(Optional.of(pagamento));
        when(pagamentoGateway.atualizar(pagamento)).thenReturn(pagamento);

        // Executando o método
        PagamentoResponse response = atualizarStatusPagamentoHandler.atualizarStatus(pagamentoId, request);

        // Verificando o resultado
        assertNotNull(response);
        assertEquals(pagamentoId, response.id());
        assertEquals(StatusPagamento.APROVADO, response.status());
    }

    @Test
    void atualizarStatus_deveLancarExcecaoQuandoPagamentoNaoExistir() {
        UUID pagamentoId = UUID.randomUUID();
        AtualizarStatusPagamentoRequest request = new AtualizarStatusPagamentoRequest(StatusPagamento.APROVADO);

        when(pagamentoGateway.buscarPorId(pagamentoId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                atualizarStatusPagamentoHandler.atualizarStatus(pagamentoId, request)
        );

        assertEquals("Pagamento não encontrado", exception.getMessage());
        verify(pagamentoGateway).buscarPorId(pagamentoId);
        verifyNoMoreInteractions(pagamentoGateway);
    }
}