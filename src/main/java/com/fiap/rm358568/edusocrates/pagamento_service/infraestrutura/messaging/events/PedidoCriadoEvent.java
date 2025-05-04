package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PedidoCriadoEvent(
        UUID pedidoId,
        BigDecimal valorTotal,
        DadosPagamentoRabbit dadosPagamento,
        List<ItemPedidoRabbit> itens
) {
    public record DadosPagamentoRabbit(String numeroCartao, String nomeTitular, String dataValidade, String cvv) {}
    public record ItemPedidoRabbit(UUID produtoId, int quantidade) {}
}
