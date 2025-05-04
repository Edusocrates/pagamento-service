# 📦 pagamento-service

O `pagamento-service` é um microsserviço do sistema de gerenciamento de pedidos desenvolvido com arquitetura de microsserviços, utilizando Java 17, Spring Boot e os princípios da Clean Architecture. Esse serviço é responsável por gerenciar todo o ciclo de vida de pagamentos dos pedidos realizados pelos clientes.

---

## ✅ Funcionalidades

- Processar pagamentos com cartão de crédito.
- Simular integração com gateway de pagamento externo (ex: Mercado Pago - mockado).
- Cancelar ou reembolsar pagamentos.
- Persistência dos dados de pagamento com PostgreSQL.
- Comunicação assíncrona com outros microsserviços via RabbitMQ.

---

## 🧱 Arquitetura

O projeto segue os princípios da **Clean Architecture**, organizando o código em camadas bem definidas:


```
pagamento-service
├── dominio # Entidades e interfaces (gateways)
├── aplicacao # UseCases e Handlers
├── infraestrutura # Implementações de gateways, repositórios, mensageria
│ ├── persistence # Entidades JPA e Repositórios
│ ├── external # Integrações com sistemas externos (ex: MercadoPagoAdapter)
│ └── messaging # Configurações e listeners RabbitMQ
├── API # Controladores REST, Exceções, requests e responses

```

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- PostgreSQL
- Spring Data JPA
- Flyway
- RabbitMQ
- Docker e Docker Compose
- JUnit 5 e Mockito
- Jacoco (para cobertura de testes)
- Arquitetura Limpa (Clean Architecture)


---

## 🧾 Entidades de Domínio

- `Pagamento`: representa os dados de pagamento e seu status.
- `StatusPagamento`: enum com os possíveis estados (`CRIADO`, `PAGO`, `RECUSADO`, `ESTORNADO`, etc.).

---

## 🔁 Comunicação Assíncrona (RabbitMQ)

Este serviço escuta eventos via RabbitMQ para processar pagamentos e estornos automaticamente com base em pedidos recebidos.

- **Fila de entrada:** `pagamento.pedido.criado`
- **Fila de saída (caso necessário):** `pagamento.status.atualizado`

---

## 🔐 Integração com Mercado Pago (Mock)

Para fins didáticos, a integração com o Mercado Pago é mockada com um adapter em:
infraestrutura.external.mercadopago.MercadoPagoAdapter
- O adapter simula a comunicação com o gateway de pagamento, permitindo testar o fluxo de pagamentos sem depender de uma API externa real.
- A lógica de pagamento é mockada, retornando um status de sucesso ou falha com base em dados simulados.


Este adapter implementa a interface `ProvedorPagamentoGateway` do domínio.

---

## 🚀 Endpoints

| Método | Endpoint                     | Descrição                           |
|--------|------------------------------|--------------------------------------|
| POST   | `/pagamentos`                | Criar novo pagamento                 |
| PUT    | `/pagamentos/{id}/atualizar` | Processar pagamento                  |
| PUT    | `/pagamentos/{id}/cancelar`  | Cancelar pagamento                  |
| GET    | `/pagamentos/{id}`           | Buscar pagamento por ID             |
| GET    | `/pagamentos`                | Listar todos os pagamentos          |



## 🐘 Banco de Dados

- Banco: PostgreSQL
- Versionamento de schema com Flyway.
- Script inicial de criação em:  
  `src/main/resources/db/migration/V1__create_pagamentos.sql`

---

## ✅ Testes

- Cobertura mínima exigida: **80%**
- Ferramenta: **Jacoco**
- Testes unitários com JUnit + Mockito.
- Testes de integração planejados para controllers e handlers.

---

## 🐳 Docker

O `pagamento-service` está pronto para ser executado via Docker Compose.

### Build da imagem
```bash
docker-compose --build
```

---
## Acesso ao banco de dados (PostgreSQL)
- Host: `localhost`
- Porta: `5432`
- Usuário: `postgres`
- Senha: `postgres`
- Banco: `pagamento_db`
- Driver: `org.postgresql.Driver`
- JDBC URL: `jdbc:postgresql://localhost:5432/pagamento_db`
- Ferramenta: `DBeaver` ou `pgAdmin`
- URL: `jdbc:postgresql://localhost:5432/pagamento_db`


---
## Acesso ao RabbitMQ
- URL: `http://localhost:15672`
- Usuário: `guest`
- Senha: `guest`
- Porta: `15672`
- Ferramenta: `RabbitMQ Management Plugin`
- URL: `http://localhost:15672`
- Porta: `15672`

---


## ✍️ Autor
- Eduardo Sócrates Caria
- GitHub: https://github.com/Edusocrates
- RM: 358568
- Turma: 6ADJT
- Grupo 15
