package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events;

import java.util.UUID;

public record PedidoCanceladoEvent(UUID pedidoId) {}
