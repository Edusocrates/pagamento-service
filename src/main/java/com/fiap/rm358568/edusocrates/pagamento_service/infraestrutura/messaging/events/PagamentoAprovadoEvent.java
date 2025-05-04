package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events;


import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoAprovadoEvent(
        UUID pagamentoId,
        UUID pedidoId,
        BigDecimal valorTotal
) {}
