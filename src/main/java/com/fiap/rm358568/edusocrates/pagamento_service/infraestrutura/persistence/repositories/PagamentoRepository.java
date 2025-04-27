package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.repositories;

import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.entities.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {

    Optional<PagamentoEntity> findByPedidoId(UUID pedidoId);
}
