package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PAGAMENTO_CRIADO_QUEUE = "pagamento-criado-queue";
    public static final String PAGAMENTO_CRIADO_EXCHANGE = "pagamento-criado-exchange";
    public static final String PAGAMENTO_CRIADO_ROUTING_KEY = "pagamento-criado-routing-key";
    public static final String PAGAMENTO_RECUSADO_QUEUE = "pagamento-recusado-queue";
    public static final String PAGAMENTO_RECUSADO_EXCHANGE = "pagamento-recusado-exchange";
    public static final String PAGAMENTO_RECUSADO_ROUTING_KEY = "pagamento-recusado-routing-key";
    public static final String PAGAMENTO_APROVADO_QUEUE = "pagamento-aprovado-queue";
    public static final String PAGAMENTO_APROVADO_EXCHANGE = "pagamento-aprovado-exchange";
    public static final String PAGAMENTO_APROVADO_ROUTING_KEY = "pagamento-aprovado-routing-key";
    public static final String PAGAMENTO_EM_PROCESSAMENTO_QUEUE = "pagamento-em-processamento-queue";
    public static final String PAGAMENTO_EM_PROCESSAMENTO_EXCHANGE = "pagamento-em-processamento-exchange";
    public static final String PAGAMENTO_EM_PROCESSAMENTO_ROUTING_KEY = "pagamento-em-processamento-routing-key";

    @Bean
    public Queue pagamentoCriadoQueue() {
        return new Queue(PAGAMENTO_CRIADO_QUEUE, true);
    }

    @Bean
    public Queue pagamentoRecusadoQueue() {
        return new Queue(PAGAMENTO_RECUSADO_QUEUE, true);
    }
    @Bean
    public Queue pagamentoAprovadoQueue() {
        return new Queue(PAGAMENTO_APROVADO_QUEUE, true);
    }
    @Bean
    public Queue pagamentoEmProcessamentoQueue() {
        return new Queue(PAGAMENTO_EM_PROCESSAMENTO_QUEUE, true);
    }
    @Bean
    public Queue pagamentoCriadoExchange() {
        return new Queue(PAGAMENTO_CRIADO_EXCHANGE, true);
    }
    @Bean
    public Queue pagamentoRecusadoExchange() {
        return new Queue(PAGAMENTO_RECUSADO_EXCHANGE, true);
    }
    @Bean
    public Queue pagamentoAprovadoExchange() {
        return new Queue(PAGAMENTO_APROVADO_EXCHANGE, true);
    }
    @Bean
    public Queue pagamentoEmProcessamentoExchange() {
        return new Queue(PAGAMENTO_EM_PROCESSAMENTO_EXCHANGE, true);
    }
    @Bean
    public Queue pagamentoCriadoRoutingKey() {
        return new Queue(PAGAMENTO_CRIADO_ROUTING_KEY, true);
    }
    @Bean
    public Queue pagamentoRecusadoRoutingKey() {
        return new Queue(PAGAMENTO_RECUSADO_ROUTING_KEY, true);
    }
    @Bean
    public Queue pagamentoAprovadoRoutingKey() {
        return new Queue(PAGAMENTO_APROVADO_ROUTING_KEY, true);
    }
    @Bean
    public Queue pagamentoEmProcessamentoRoutingKey() {
        return new Queue(PAGAMENTO_EM_PROCESSAMENTO_ROUTING_KEY, true);
    }
}
