# 💳 Desafio de Transação

API REST desenvolvida em **Spring Boot** para gerenciar transações financeiras com cálculo de estatísticas em tempo real.

## 📋 Descrição

Sistema que permite registrar transações financeiras, limpar dados e obter estatísticas agregadas sobre as transações processadas. A API valida dados de entrada e garante que apenas transações válidas sejam armazenadas.

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 4.0.6**
- **Maven**
- Spring Validation
- Spring Web MVC

## 📦 Estrutura do Projeto

```
desafiodetransacao/
├── src/
│   ├── main/
│   │   └── java/desafio/spring/desafiodetransacao/
│   │       ├── controller/          # Endpoints REST
│   │       │   ├── TransacationController.java
│   │       │   └── StatisticsController.java
│   │       ├── service/             # Lógica de negócio
│   │       │   └── TransactionService.java
│   │       ├── model/               # Entidades
│   │       │   └── Transaction.java
│   │       ├── dto/                 # Data Transfer Objects
│   │       │   ├── TransactionRequest.java
│   │       │   └── StatisticsResponse.java
│   │       └── DesafiodetransacaoApplication.java
│   └── test/
├── pom.xml
└── README.md
```

## 🚀 Endpoints

### 1. Criar Transação
**POST** `/transacao`

Registra uma nova transação financeira.

**Request:**
```json
{
  "valor": 100.50,
  "dataHora": "2024-05-22T10:30:00Z"
}
```

**Validações:**
- `valor` > 0
- `dataHora` não pode ser no futuro

**Responses:**
- `201 Created` - Transação registrada com sucesso
- `422 Unprocessable Content` - Dados inválidos

---

### 2. Obter Estatísticas
**GET** `/estatistica`

Retorna estatísticas agregadas de todas as transações.

**Response:**
```json
{
  "count": 5,
  "sum": 500.50,
  "avg": 100.10,
  "min": 50.00,
  "max": 150.50
}
```

---

### 3. Limpar Transações
**DELETE** `/transacao`

Remove todas as transações registradas.

**Response:**
- `200 OK` - Transações removidas com sucesso

---

## 💾 Armazenamento

As transações são armazenadas em uma **fila thread-safe** (`ConcurrentLinkedQueue`), permitindo acesso concorrente sem sincronização manual.

## 🔐 Validações

- ✅ Valor da transação deve ser positivo
- ✅ Data/Hora não pode ser no futuro
- ✅ Validação automática de entrada via Jakarta Validation

## 🏗️ Arquitetura

```
Client HTTP
    ↓
Controller (Validação inicial)
    ↓
Service (Lógica de negócio)
    ↓
ConcurrentLinkedQueue (Armazenamento)
    ↓
DTO Response
```

## 🔄 Fluxo de Transação

1. **POST /transacao** recebe os dados
2. **TransacationController** valida data e valor
3. **TransactionService** adiciona à fila
4. **GET /estatistica** calcula estatísticas em tempo real usando Streams

## 📊 Estatísticas

O serviço utiliza `DoubleSummaryStatistics` para calcular:
- **Count**: Número total de transações
- **Sum**: Valor total somado
- **Avg**: Média aritmética
- **Min**: Valor mínimo
- **Max**: Valor máximo

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+

### Instalação e Execução

```bash
# Clonar repositório
git clone https://github.com/CarlosEdu1/desafiodetransacao.git
cd desafiodetransacao

# Compilar
mvn clean install

# Executar
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## 📝 Exemplo de Uso com cURL

```bash
# Criar transação
curl -X POST http://localhost:8080/transacao \
  -H "Content-Type: application/json" \
  -d '{
    "valor": 100.50,
    "dataHora": "2024-05-22T10:30:00Z"
  }'

# Obter estatísticas
curl -X GET http://localhost:8080/estatistica

# Limpar transações
curl -X DELETE http://localhost:8080/transacao
```

## ⚙️ Configuração

O arquivo `application.properties` (se existir) pode ser usado para customizar porta e outras propriedades.

## 🐛 Notas de Desenvolvimento

- O filtro de 60 segundos no `TransactionService.getStatistics()` está comentado (linha 27)
- Descomente para filtrar apenas transações dos últimos 60 segundos

```java
.filter(t -> t.getDataHora().isAfter(now.minusSeconds(60)))
```

## 📄 Licença

Distribuído sob a Licença MIT. Veja LICENSE para mais informações.

## 👨‍💻 Autor

**Carlos Martins** [@CarlosEdu1](https://github.com/CarlosEdu1)

---

⭐ Se este projeto foi útil, considere dar uma star!
