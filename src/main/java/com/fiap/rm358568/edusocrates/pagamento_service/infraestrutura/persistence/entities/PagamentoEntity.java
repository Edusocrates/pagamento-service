package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.entities;

import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID pedidoId;

    @Column(nullable = false)
    private String numeroCartao;

    @Column(nullable = false)
    private String nomeTitular;

    @Column(nullable = false)
    private String dataValidade;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento status;
}
