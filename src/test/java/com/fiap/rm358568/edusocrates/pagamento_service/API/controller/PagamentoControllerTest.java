package com.fiap.rm358568.edusocrates.pagamento_service.API.controller;


import com.fiap.rm358568.edusocrates.pagamento_service.API.controllers.PagamentoController;
import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.AtualizarStatusPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.CriarPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.AtualizarStatusPagamentoUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.BuscarPagamentoPorIdUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.BuscarPagamentoPorPedidoIdUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.CriarPagamentoUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoControllerTest {

    @Mock
    private CriarPagamentoUseCase criarPagamentoUseCase;

    @Mock
    private BuscarPagamentoPorIdUseCase consultarPagamentoPorIdUseCase;

    @Mock
    private AtualizarStatusPagamentoUseCase atualizarPagamentoUseCase;

    @Mock
    private BuscarPagamentoPorPedidoIdUseCase buscarPagamentoPorPedidoIdUseCase;

    @InjectMocks
    private PagamentoController pagamentoController;

    @Test
    void criar_deveRetornarPagamentoResponse_quandoPagamentoForCriado() {
        CriarPagamentoRequest request = new CriarPagamentoRequest(UUID.randomUUID(), "123456789", "TESTE", "30/12/2000", "TESTE", BigDecimal.valueOf(100.0), "TESTE");
        PagamentoResponse responseMock = new PagamentoResponse(UUID.randomUUID(),
                UUID.randomUUID(), StatusPagamento.APROVADO,"123456789", "TESTE", "30/12/2000", "TESTE", BigDecimal.valueOf(100.0), StatusPagamento.APROVADO);

        when(criarPagamentoUseCase.criar(request)).thenReturn(responseMock);

        ResponseEntity<PagamentoResponse> response = pagamentoController.criar(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void buscarPorId_deveRetornarPagamentoResponse_quandoPagamentoExistir() {
        UUID id = UUID.randomUUID();
        PagamentoResponse responseMock = new PagamentoResponse(UUID.randomUUID(),
                UUID.randomUUID(), StatusPagamento.APROVADO,"123456789", "TESTE", "30/12/2000", "TESTE", BigDecimal.valueOf(100.0), StatusPagamento.APROVADO);

        when(consultarPagamentoPorIdUseCase.buscarPorId(id)).thenReturn(responseMock);

        ResponseEntity<PagamentoResponse> response = pagamentoController.buscarPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void atualizarStatus_deveRetornarPagamentoResponse_quandoStatusForAtualizado() {
        UUID id = UUID.randomUUID();
        AtualizarStatusPagamentoRequest request = new AtualizarStatusPagamentoRequest("APROVADO");
        PagamentoResponse responseMock = new PagamentoResponse(UUID.randomUUID(),
                UUID.randomUUID(), StatusPagamento.APROVADO,"123456789", "TESTE", "30/12/2000", "TESTE", BigDecimal.valueOf(100.0), StatusPagamento.APROVADO);

        when(atualizarPagamentoUseCase.atualizarStatus(id, request)).thenReturn(responseMock);

        ResponseEntity<PagamentoResponse> response = pagamentoController.atualizarStatus(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void buscarPorPedidoId_deveRetornarPagamentoResponse_quandoPagamentoExistir() {
        UUID pedidoId = UUID.randomUUID();
        PagamentoResponse responseMock = new PagamentoResponse(UUID.randomUUID(),
                UUID.randomUUID(), StatusPagamento.APROVADO,"123456789", "TESTE", "30/12/2000", "TESTE", BigDecimal.valueOf(100.0), StatusPagamento.APROVADO);

        when(buscarPagamentoPorPedidoIdUseCase.consultar(pedidoId)).thenReturn(responseMock);

        ResponseEntity<PagamentoResponse> response = pagamentoController.buscarPorPedidoId(pedidoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}