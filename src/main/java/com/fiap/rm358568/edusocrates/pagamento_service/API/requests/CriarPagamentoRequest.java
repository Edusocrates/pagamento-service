package com.fiap.rm358568.edusocrates.pagamento_service.API.requests;




import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record CriarPagamentoRequest(

        @NotNull(message = "O ID do pedido é obrigatório.")
        UUID pedidoId,

        @NotBlank(message = "O número do cartão é obrigatório.")
        @Size(min = 13, max = 19, message = "O número do cartão deve ter entre 13 e 19 dígitos.")
        @Pattern(regexp = "\\d+", message = "O número do cartão deve conter apenas números.")
        String numeroCartao,

        @NotBlank(message = "O nome do titular é obrigatório.")
        String nomeTitular,

        @NotBlank(message = "A data de validade é obrigatória.")
        @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "A data de validade deve estar no formato MM/AA.")
        String dataValidade,

        @NotBlank(message = "O CVV é obrigatório.")
        @Size(min = 3, max = 4, message = "O CVV deve ter 3 ou 4 dígitos.")
        @Pattern(regexp = "\\d+", message = "O CVV deve conter apenas números.")
        String cvv,

        @NotNull(message = "O valor é obrigatório.")
        BigDecimal valor,

        @NotNull(message = "O status é obrigatório.")
        @Pattern(regexp = "APROVADO|RECUSADO", message = "O status deve ser APROVADO ou RECUSADO.")
        @Size(max = 10, message = "O status deve ter no máximo 10 caracteres.")
        String status

) {}