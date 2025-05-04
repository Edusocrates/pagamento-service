package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.listeners;

import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.CriarPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers.CriarPagamentoHandler;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events.PedidoCriadoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class PedidoCriadoListener {

    private final CriarPagamentoHandler criarPagamentoHandler;

    public PedidoCriadoListener(CriarPagamentoHandler criarPagamentoHandler) {
        this.criarPagamentoHandler = criarPagamentoHandler;
    }

    //@RabbitListener(queues = "pedido.criado.queue")
    public void receber(PedidoCriadoEvent event) {
        //criado apenas um mock para simular o recebimento do evento
        log.info("Recebendo evento de pedido criado: {}", event);
        var dadosPagamento = new CriarPagamentoRequest(
                UUID.randomUUID(),
                event.dadosPagamento().numeroCartao(),
                event.dadosPagamento().nomeTitular(),
                event.dadosPagamento().dataValidade(),
                event.dadosPagamento().cvv(),
                event.valorTotal(),
                StatusPagamento.EM_PROCESSAMENTO.name()
        );

        log.info("Criando pagamento com os dados: {}", dadosPagamento);
        criarPagamentoHandler.criar(dadosPagamento);
    }
}
