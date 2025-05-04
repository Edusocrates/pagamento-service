package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.utils.RabbitMQConstants.*;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange pagamentoExchange() {
        return new TopicExchange(PAGAMENTO_EXCHANGE);
    }

    @Bean
    public Queue pagamentoAprovadoQueue() {
        return QueueBuilder.durable(PAGAMENTO_APROVADO_QUEUE).build();
    }

    @Bean
    public Queue pagamentoRecusadoQueue() {
        return QueueBuilder.durable(PAGAMENTO_RECUSADO_QUEUE).build();
    }

    @Bean
    public Binding bindingAprovado() {
        return BindingBuilder
                .bind(pagamentoAprovadoQueue())
                .to(pagamentoExchange())
                .with(PAGAMENTO_APROVADO_ROUTING_KEY);
    }

    @Bean
    public Binding bindingRecusado() {
        return BindingBuilder
                .bind(pagamentoRecusadoQueue())
                .to(pagamentoExchange())
                .with(PAGAMENTO_RECUSADO_ROUTING_KEY);
    }
}
