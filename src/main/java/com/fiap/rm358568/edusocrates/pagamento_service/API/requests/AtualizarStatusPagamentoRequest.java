package com.fiap.rm358568.edusocrates.pagamento_service.API.requests;

import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusPagamentoRequest(

        @NotNull(message = "O status do pagamento é obrigatório.")
        StatusPagamento statusPagamento

) {}
