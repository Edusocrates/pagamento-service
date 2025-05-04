package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.producers;


import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events.PagamentoAprovadoEvent;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events.PagamentoRecusadoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.utils.RabbitMQConstants.*;

@Component
public class PagamentoProducer {

    private final RabbitTemplate rabbitTemplate;

    public PagamentoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPagamentoAprovado(PagamentoAprovadoEvent event) {
        rabbitTemplate.convertAndSend(PAGAMENTO_EXCHANGE, PAGAMENTO_APROVADO_ROUTING_KEY, event);
    }

    public void enviarPagamentoRecusado(PagamentoRecusadoEvent event) {
        rabbitTemplate.convertAndSend(PAGAMENTO_EXCHANGE, PAGAMENTO_RECUSADO_ROUTING_KEY, event);
    }
}
