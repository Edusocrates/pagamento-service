package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging;


import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.AtualizarStatusPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers.AtualizarStatusPagamentoHandler;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events.PedidoCanceladoEvent;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.listeners.PedidoCanceladoListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoCanceladoListenerTest {

    @Mock
    private AtualizarStatusPagamentoHandler atualizarStatusPagamentoHandler;

    @InjectMocks
    private PedidoCanceladoListener pedidoCanceladoListener;

    @Test
    void onPedidoCancelado_deveAtualizarStatusParaRecusado() {
        UUID pedidoId = UUID.randomUUID();
        PedidoCanceladoEvent event = new PedidoCanceladoEvent(pedidoId);

        AtualizarStatusPagamentoRequest request = new AtualizarStatusPagamentoRequest(StatusPagamento.RECUSADO);

        pedidoCanceladoListener.onPedidoCancelado(event);

        verify(atualizarStatusPagamentoHandler).atualizarStatus(pedidoId, request);
    }
}