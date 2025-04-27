package com.fiap.rm358568.edusocrates.pagamento_service.dominio.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {

    private  String numero;
    private  String nomeTitular;
    private  String dataValidade;
    private  String cvv;
}
