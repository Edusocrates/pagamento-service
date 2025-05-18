package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.listeners;

import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.AtualizarStatusPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers.AtualizarStatusPagamentoHandler;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events.PedidoCanceladoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PedidoCanceladoListener {

    private final AtualizarStatusPagamentoHandler atualizarStatusPagamentoHandler;

    public PedidoCanceladoListener(AtualizarStatusPagamentoHandler atualizarStatusPagamentoHandler) {
        this.atualizarStatusPagamentoHandler = atualizarStatusPagamentoHandler;
    }

    //@RabbitListener(queues = "${queue.cancelamento-pedido}")
    public void onPedidoCancelado(PedidoCanceladoEvent event) {
        //mock apenas para simular um listener de cancelamento de evento
        log.info("Recebendo evento de cancelamento de pedido: {}", event);

        AtualizarStatusPagamentoRequest request = new AtualizarStatusPagamentoRequest("RECUSADO");
        atualizarStatusPagamentoHandler.atualizarStatus(event.pedidoId(), request);
    }
}
