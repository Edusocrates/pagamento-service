package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.listeners;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PagamentoListener {

    //@RabbitListener(queues = "${spring.rabbitmq.queue.pagamento}")
    public void processarMensagem(String mensagem) {
        // Mock para construção de listener de pagamento
        log.info("Processando pagamento: {}", mensagem);

        // Lógica para processar o pagamento
        // Por exemplo, você pode chamar um serviço de pagamento aqui
        // pagamentoService.processarPagamento(mensagem);
    }
}
