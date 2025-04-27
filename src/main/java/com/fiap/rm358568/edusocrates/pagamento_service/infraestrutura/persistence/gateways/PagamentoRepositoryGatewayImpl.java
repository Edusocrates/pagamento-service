package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.gateways;

import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Pagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.gateways.PagamentoGateway;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.mapper.PagamentoEntityMapper;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.repositories.PagamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PagamentoRepositoryGatewayImpl implements PagamentoGateway {


    private final PagamentoRepository repository;


    @Override
    @Transactional
    public Pagamento salvar(Pagamento pagamento) {
        log.info("Salvando ou atualizando pagamento na base de dados!");

        var entity = PagamentoEntityMapper.toEntity(pagamento);
        repository.findById(entity.getId()).ifPresent(existingEntity -> {
            entity.setId(existingEntity.getId());
        });
        var salvo = repository.save(entity);
        log.info("Pagamento salvo ou atualizado com sucesso!");
        return PagamentoEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Pagamento> buscarPorId(UUID id) {
        log.info("Buscando pagamento por ID: {} na base de dados!", id);
        return repository.findById(id)
                .map(PagamentoEntityMapper::toDomain);
    }

    @Override
    public Optional<Pagamento> buscarPorPedidoId(UUID pedidoId) {
        log.info("Buscando pagamento por ID do pedido: {} na base de dados!", pedidoId);
        return repository.findByPedidoId(pedidoId)
                .map(PagamentoEntityMapper::toDomain);
    }

    @Override
    @Transactional
    public Pagamento atualizar(Pagamento pagamento) {
        log.info("Atualizando pagamento na base de dados!");

        var entity = PagamentoEntityMapper.toEntity(pagamento);
        var salvo = repository.save(entity);
        log.info("Pagamento atualizado com sucesso!");
        return PagamentoEntityMapper.toDomain(salvo);
    }
}
