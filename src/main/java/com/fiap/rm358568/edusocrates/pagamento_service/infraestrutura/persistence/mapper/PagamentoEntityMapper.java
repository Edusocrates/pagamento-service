package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.mapper;

import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.CriarPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Cartao;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.Pagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.persistence.entities.PagamentoEntity;
import org.springframework.stereotype.Component;

@Component
public class PagamentoEntityMapper {

    private PagamentoEntityMapper() {
        // Utilitário, não instanciar
    }

    public static Pagamento requestToDomain(CriarPagamentoRequest request) {
        if (request == null) {
            return null;
        }
        return new Pagamento(
                request.pedidoId(),
                request.pedidoId(),
                new Cartao(
                        request.numeroCartao(),
                        request.nomeTitular(),
                        request.dataValidade(),
                        request.cvv()
                ),
                request.valor(),
                StatusPagamento.valueOf(request.status())
        );
    }

    public static Pagamento toDomain(PagamentoEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Pagamento(
                entity.getId(),
                entity.getPedidoId(),
                new Cartao(
                        entity.getNumeroCartao(),
                        entity.getNomeTitular(),
                        entity.getDataValidade(),
                        entity.getCvv()
                ),
                entity.getValor(),
                entity.getStatus()
        );
    }

    public static PagamentoEntity toEntity(Pagamento domain) {
        if (domain == null) {
            return null;
        }
        return PagamentoEntity.builder()
                .id(domain.getId())
                .pedidoId(domain.getPedidoId())
                .numeroCartao(domain.getCartao().getNumero())
                .nomeTitular(domain.getCartao().getNomeTitular())
                .dataValidade(domain.getCartao().getDataValidade())
                .cvv(domain.getCartao().getCvv())
                .valor(domain.getValor())
                .status(domain.getStatus())
                .build();
    }

    public static PagamentoResponse toResponse(Pagamento pagamentoSalvo) {
        if (pagamentoSalvo == null) {
            return null;
        }
        return PagamentoResponse.builder()
                .id(pagamentoSalvo.getId())
                .pedidoId(pagamentoSalvo.getPedidoId())
                .numeroCartao(pagamentoSalvo.getCartao().getNumero())
                .nomeTitular(pagamentoSalvo.getCartao().getNomeTitular())
                .dataValidade(pagamentoSalvo.getCartao().getDataValidade())
                .cvv(pagamentoSalvo.getCartao().getCvv())
                .valor(pagamentoSalvo.getValor())
                .status(pagamentoSalvo.getStatus())
                .build();
    }
}
