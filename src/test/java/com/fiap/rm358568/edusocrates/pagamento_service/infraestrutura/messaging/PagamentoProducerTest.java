package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging;


import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events.PagamentoAprovadoEvent;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events.PagamentoRecusadoEvent;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.producers.PagamentoProducer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.util.UUID;

import static com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.utils.RabbitMQConstants.*;
import static org.mockito.Mockito.verify;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
class PagamentoProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PagamentoProducer pagamentoProducer;

    @Test
    void enviarPagamentoAprovado_deveEnviarMensagemParaFilaCorreta() {
        PagamentoAprovadoEvent event = new PagamentoAprovadoEvent(
                UUID.randomUUID(),
                UUID.randomUUID(),
                BigDecimal.valueOf(150.0)
        );

        pagamentoProducer.enviarPagamentoAprovado(event);

        verify(rabbitTemplate).convertAndSend(PAGAMENTO_EXCHANGE, PAGAMENTO_APROVADO_ROUTING_KEY, event);
    }

    @Test
    void enviarPagamentoRecusado_deveEnviarMensagemParaFilaCorreta() {
        PagamentoRecusadoEvent event = new PagamentoRecusadoEvent(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Cartão inválido"
        );

        pagamentoProducer.enviarPagamentoRecusado(event);

        verify(rabbitTemplate).convertAndSend(PAGAMENTO_EXCHANGE, PAGAMENTO_RECUSADO_ROUTING_KEY, event);
    }
}