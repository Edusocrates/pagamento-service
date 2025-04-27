package com.fiap.rm358568.edusocrates.pagamento_service.API.responses;

import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Pagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import lombok.Builder;


import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record PagamentoResponse(

        UUID id,
        UUID pedidoId,
        StatusPagamento statusPagamento,
        String numeroCartao,
        String nomeTitular,
        String dataValidade,
        String cvv,
        BigDecimal valor,
        StatusPagamento status

) {
    public static PagamentoResponse fromDomain(Pagamento pagamento) {
        return new PagamentoResponse(
                pagamento.getId(),
                pagamento.getPedidoId(),
                pagamento.getStatus(),
                pagamento.getCartao().getNumero(),
                pagamento.getCartao().getNomeTitular(),
                pagamento.getCartao().getDataValidade(),
                pagamento.getCartao().getCvv(),
                pagamento.getValor(),
                pagamento.getStatus()
        );
    }
}
