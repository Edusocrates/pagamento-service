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
class BuscarPagamentoPorPedidoIdHandlerTest {

    @Mock
    private PagamentoGateway pagamentoGateway;

    @Mock
    private PagamentoEntityMapper pagamentoEntityMapper;

    @InjectMocks
    private BuscarPagamentoPorPedidoIdHandler buscarPagamentoPorPedidoIdHandler;

    @Test
    void consultar_deveRetornarPagamentoResponse_quandoPagamentoExistir() {
        UUID pedidoId = UUID.randomUUID();
        Pagamento pagamento = new Pagamento(
                UUID.randomUUID(),
                pedidoId,
                new Cartao("123456789", "TESTE", "12/30", "123"),
                BigDecimal.valueOf(100.0),
                StatusPagamento.APROVADO
        );
        PagamentoResponse pagamentoResponse = PagamentoEntityMapper.toResponse(pagamento); // Chamada direta ao método estático

        when(pagamentoGateway.buscarPorPedidoId(pedidoId)).thenReturn(Optional.of(pagamento));

        PagamentoResponse response = buscarPagamentoPorPedidoIdHandler.consultar(pedidoId);

        assertNotNull(response);
        assertEquals(pedidoId, response.pedidoId());
        verify(pagamentoGateway).buscarPorPedidoId(pedidoId);
    }

    @Test
    void consultar_deveLancarExcecao_quandoPagamentoNaoExistir() {
        UUID pedidoId = UUID.randomUUID();

        when(pagamentoGateway.buscarPorPedidoId(pedidoId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(PagamentoNotFoundException.class, () ->
                buscarPagamentoPorPedidoIdHandler.consultar(pedidoId)
        );

        assertEquals("Pagamento não encontrado para o pedido ID: " + pedidoId, exception.getMessage());
        verify(pagamentoGateway).buscarPorPedidoId(pedidoId);
        verifyNoInteractions(pagamentoEntityMapper);
    }
}