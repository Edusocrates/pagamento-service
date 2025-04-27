CREATE TABLE pagamentos (
    id UUID PRIMARY KEY,
    pedido_id UUID NOT NULL,
    numero_cartao VARCHAR(255) NOT NULL,
    nome_titular VARCHAR(255) NOT NULL,
    data_validade VARCHAR(7) NOT NULL,
    cvv VARCHAR(10) NOT NULL,
    valor DECIMAL(19,2) NOT NULL,
    status VARCHAR(20) NOT NULL
);
