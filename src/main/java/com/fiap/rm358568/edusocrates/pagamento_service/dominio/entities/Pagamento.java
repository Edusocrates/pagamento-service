package com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    private  UUID id;
    private  UUID pedidoId;
    private  Cartao cartao;
    private  BigDecimal valor;
    private StatusPagamento status;

    public void alterarStatus(StatusPagamento novoStatus) {
        this.status = novoStatus;
    }
}
