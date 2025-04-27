package com.fiap.rm358568.edusocrates.pagamento_service.API.controllers;

import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.AtualizarStatusPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.requests.CriarPagamentoRequest;
import com.fiap.rm358568.edusocrates.pagamento_service.API.responses.PagamentoResponse;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.AtualizarStatusPagamentoUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.BuscarPagamentoPorIdUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.BuscarPagamentoPorPedidoIdUseCase;
import com.fiap.rm358568.edusocrates.pagamento_service.aplicacao.usecases.CriarPagamentoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Configurações Pagamento!", description = "Operações relacionadas ao pagamento!")
public class PagamentoController {

     private final CriarPagamentoUseCase criarPagamentoUseCase;
     private final BuscarPagamentoPorIdUseCase consultarPagamentoPorIdUseCase;
     private final AtualizarStatusPagamentoUseCase atualizarPagamentoUseCase;
     private final BuscarPagamentoPorPedidoIdUseCase buscarPagamentoPorPedidoIdUseCase;

     @PostMapping
     @Tag(name = "Criar Pagamento", description = "Criar um novo pagamento")
     @Operation(summary = "Criar Pagamento", description = "Criar um novo pagamento")
     public ResponseEntity<PagamentoResponse> criar(@RequestBody CriarPagamentoRequest pagamentoRequest) {
         log.info("Criando pagamento: {}", pagamentoRequest);
         PagamentoResponse pagamentoResponse = criarPagamentoUseCase.criar(pagamentoRequest);
         return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoResponse);
     }

    @GetMapping("/{id}")
    @Tag(name = "Consultar Pagamento", description = "Consultar pagamento por ID")
    @Operation(summary = "Consultar Pagamento", description = "Consultar pagamento por ID")
    public ResponseEntity<PagamentoResponse> buscarPorId(@PathVariable UUID id) {
        log.info("Buscando pagamento por ID: {}", id);
        PagamentoResponse pagamentoResponse = consultarPagamentoPorIdUseCase.buscarPorId(id);
        return ResponseEntity.ok(pagamentoResponse);
    }

    @PatchMapping("/{id}/atualizar-status")
    @Tag(name = "Atualizar Status Pagamento", description = "Atualizar o status de um pagamento")
    @Operation(summary = "Atualizar Status Pagamento", description = "Atualizar o status de um pagamento")
    public ResponseEntity<PagamentoResponse> atualizarStatus(@PathVariable UUID id, @RequestParam AtualizarStatusPagamentoRequest status) {
        log.info("Atualizando status do pagamento com ID: {} para o status: {}", id, status);
        PagamentoResponse pagamentoResponse = atualizarPagamentoUseCase.atualizarStatus(id, status);
        return ResponseEntity.ok(pagamentoResponse);
    }

    @GetMapping("/pedido/{pedidoId}")
    @Tag(name = "Consultar Pagamento por Pedido", description = "Consultar pagamento por ID do pedido")
    @Operation(summary = "Consultar Pagamento por Pedido", description = "Consultar pagamento por ID do pedido")
    public ResponseEntity<PagamentoResponse> buscarPorPedidoId(@PathVariable UUID pedidoId) {
        log.info("Buscando pagamento por ID do pedido: {}", pedidoId);
        PagamentoResponse pagamentoResponse = buscarPagamentoPorPedidoIdUseCase.consultar(pedidoId);
        return ResponseEntity.ok(pagamentoResponse);
    }
}
