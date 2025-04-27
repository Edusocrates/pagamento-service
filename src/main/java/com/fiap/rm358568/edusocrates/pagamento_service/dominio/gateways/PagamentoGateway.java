package com.fiap.rm358568.edusocrates.pagamento_service.dominio.gateways;

import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Pagamento;

import java.util.Optional;
import java.util.UUID;

public interface PagamentoGateway {

    Pagamento salvar(Pagamento pagamento);

    Optional<Pagamento> buscarPorId(UUID id);

    Optional<Pagamento> buscarPorPedidoId(UUID pedidoId);

    Pagamento atualizar(Pagamento pagamento);
}
