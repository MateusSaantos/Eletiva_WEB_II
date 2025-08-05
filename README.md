
# 🎟️ Ticket System - Projeto de Sistemas Web II (CSI607)

Este projeto é um sistema de vendas de ingressos desenvolvido com arquitetura de microsserviços usando **Spring Boot** e **Docker** como parte da disciplina **CSI607 - Sistemas Web II**, ministrada pelo Prof. Fernando Bernardes de Oliveira (UFOP - DECSI).

---

## 📁 Estrutura do Projeto

O projeto está dividido em múltiplos microsserviços Java com Spring Boot:

```
Codes/
├── helloworld/           # Exemplo básico Spring Boot
└── ticket/
    ├── gateway/          # API Gateway (Spring Cloud Gateway)
    ├── nameserver/       # Service discovery (Eureka Server)
    ├── sales/            # Microsserviço de vendas
    └── user/             # Microsserviço de usuários
```

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Web
- Spring Cloud Gateway
- Spring Eureka (Service Discovery)
- PostgreSQL
- Docker e Docker Compose
- Lombok
- Postman (coleção incluída)

---

## 🚀 Como Rodar o Projeto (via Docker)

### ✅ Pré-requisitos

- Docker + Docker Compose
- Java 17 (caso queira rodar localmente sem containers)

### ▶️ Passo a passo

1. Acesse a pasta raiz do projeto onde está o `docker-compose.yml`
2. Execute o comando:

```bash
docker-compose up --build
```

3. Os seguintes serviços estarão disponíveis:

| Serviço         | Porta             | Descrição                      |
|----------------|-------------------|--------------------------------|
| Gateway        | `localhost:4000`  | Entrada única da aplicação     |
| Eureka         | `localhost:8761`  | Dashboard do service discovery |
| User Service   | Interno (via gateway) | Gerencia usuários           |
| Sales Service  | Interno (via gateway) | Gerencia vendas             |
| PostgreSQL     | `localhost:5432`  | Banco de dados                 |

---

## 🧪 Testes via Postman

Você pode testar os endpoints usando o arquivo Postman incluso:

- `WEB II.postman_collection.json`

Importe a coleção no Postman e use os exemplos de:

- Criação de eventos
- Criação de vendas
- Atualização de status
- Busca por ID
- Listagem geral

---

## 📌 Endpoints de Exemplo (via Gateway)

> Todos os endpoints passam pelo `http://localhost:4000`

### 🎫 Event (sales service)
```http
POST /sales/events
GET /sales/events
GET /sales/events/{id}
PUT /sales           # Atualizar evento
```

### 🛒 Sale
```http
POST /sales
PUT /sales/saleStatus
```

### 👤 User (caso esteja implementado)
```http
POST /user
GET /user/{id}
```

---

## 🧩 Organização do código

Cada microsserviço possui:

- `pom.xml` próprio
- Camadas bem definidas: `model`, `repository`, `service`, `controller`, `dto`
- DTOs para entrada e saída de dados
- Enum para estados de venda e tipo de evento
- Mapeamento com JPA e persistência em PostgreSQL

---

## 📄 Scripts úteis (manual)

Dentro do projeto há um arquivo `COMANDOS.txt` com exemplos para:

- Rodar serviços manualmente com `mvn spring-boot:run`
- Testar com `curl`
- Acessar banco PostgreSQL

---

## 👨‍🏫 Créditos

Professor: [Fernando Bernardes de Oliveira, Ph.D.](mailto:fboliveira@ufop.edu.br)  
Departamento de Computação e Sistemas - [DECSI/UFOP](https://decsi.ufop.br)

---

## 📝 Licença

Distribuído sob [Creative Commons BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/)

---

![May the force be with you](https://media.giphy.com/media/SW52VX6Xtzk1q/giphy.gif)