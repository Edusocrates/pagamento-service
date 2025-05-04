package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.external;

import com.fiap.rm358568.edusocrates.pagamento_service.dominio.gateways.ProvedorPagamentoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class MercadoPagoAdapter implements ProvedorPagamentoGateway {
    @Override
    public boolean processarPagamento(UUID pagamentoId, String numeroCartao, String nomeTitular, String validade, String cvv, double valor) {
        log.info("MOCK: Processando pagamento com Mercado Pago");
        return true;
    }

    @Override
    public boolean cancelarPagamento(UUID pagamentoId) {
        log.info("MOCK: Cancelando pagamento com Mercado Pago");
        return true;
    }
}
