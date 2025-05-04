package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events;

import java.util.UUID;

public record PagamentoRecusadoEvent(
        UUID pagamentoId,
        UUID pedidoId,
        String motivo
) {}
