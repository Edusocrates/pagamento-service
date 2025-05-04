package com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging;


import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.CriarPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.handlers.CriarPagamentoHandler;
import com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities.StatusPagamento;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.events.PedidoCriadoEvent;
import com.fiap.rm358568.edusocrates.pagamento_service.infraestrutura.messaging.listeners.PedidoCriadoListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoCriadoListenerTest {

    @Mock
    private CriarPagamentoHandler criarPagamentoHandler;

    @InjectMocks
    private PedidoCriadoListener pedidoCriadoListener;

    @Test
    void receber_deveCriarPagamentoComDadosCorretos() {
        UUID pedidoId = UUID.randomUUID();
        BigDecimal valorTotal = BigDecimal.valueOf(200.0);
        PedidoCriadoEvent.DadosPagamentoRabbit dadosPagamento = new PedidoCriadoEvent.DadosPagamentoRabbit(
                "1234567890123456",
                "João Silva",
                "12/30",
                "123"
        );
        List<PedidoCriadoEvent.ItemPedidoRabbit> itens = List.of(
                new PedidoCriadoEvent.ItemPedidoRabbit(UUID.randomUUID(), 2),
                new PedidoCriadoEvent.ItemPedidoRabbit(UUID.randomUUID(), 1)
        );

        PedidoCriadoEvent event = new PedidoCriadoEvent(pedidoId, valorTotal, dadosPagamento, itens);

        CriarPagamentoRequest esperado = new CriarPagamentoRequest(
                UUID.randomUUID(),
                dadosPagamento.numeroCartao(),
                dadosPagamento.nomeTitular(),
                dadosPagamento.dataValidade(),
                dadosPagamento.cvv(),
                valorTotal,
                StatusPagamento.EM_PROCESSAMENTO.name()
        );

        pedidoCriadoListener.receber(event);

        verify(criarPagamentoHandler).criar(argThat(request ->
                request.numeroCartao().equals(esperado.numeroCartao()) &&
                        request.nomeTitular().equals(esperado.nomeTitular()) &&
                        request.dataValidade().equals(esperado.dataValidade()) &&
                        request.cvv().equals(esperado.cvv()) &&
                        request.valor().compareTo(esperado.valor()) == 0 &&
                        request.status().equals(esperado.status())
        ));
    }
}