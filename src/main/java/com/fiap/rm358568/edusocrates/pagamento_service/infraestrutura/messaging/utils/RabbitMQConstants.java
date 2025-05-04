package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.utils;

public class RabbitMQConstants {
    public static final String PAGAMENTO_EXCHANGE = "pagamento.exchange";
    public static final String PAGAMENTO_APROVADO_ROUTING_KEY = "pagamento.aprovado";
    public static final String PAGAMENTO_RECUSADO_ROUTING_KEY = "pagamento.recusado";
    public static final String PAGAMENTO_APROVADO_QUEUE = "pagamento.aprovado.queue";
    public static final String PAGAMENTO_RECUSADO_QUEUE = "pagamento.recusado.queue";
}
