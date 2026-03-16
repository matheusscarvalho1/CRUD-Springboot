# 🧊 Fridge API - Notas de Estudo (Geladeira)

Este projeto é uma API REST desenvolvida em **Java** com **Spring Boot** para gerenciar alimentos. O objetivo principal foi aplicar os padrões de mercado e entender como o dado flui dentro de uma aplicação profissional.

---

### 🏗️ Arquitetura em Camadas (O Caminho do Dado)
Segui a separação de responsabilidades para o código ficar limpo e fácil de mexer:

*   **Controller:** O "porteiro". Ele recebe o JSON, valida se os dados fazem sentido e decide o que responder (200, 201, 400, 404). Ele não mexe com o banco, só coordena.
*   **Service:** O "cérebro". É aqui que a mágica acontece. Ele pega os dados do porteiro, faz as contas, verifica se o item existe e manda salvar. Ele não sabe o que é um JSON, ele lida com lógica pura.
*   **Repository:** O "arquivista". Ele só sabe salvar, buscar e deletar no banco de dados. É a ponte direta com o banco (JPA).
*   **Model:** A "gaveta". É o desenho exato da minha tabela no banco de dados. Representa o estado real da informação.

---

### 📦 DTOs (Data Transfer Objects) e Records
Aprendi que não devo mexer direto com o banco de dados via API:

*   **Isolamento:** Parei de usar a **Model** (banco) para receber dados da internet. Isso protege o banco de dados.
*   **Estratégia:** Criei o `FoodRequestDTO` (exigente) para criar/substituir alimentos e o `FoodUpdateDTO` (flexível) para atualizar só um pedacinho (**PATCH**).
*   **Records (Java Moderno):** Em vez de classes pesadas, usei `record`. O código ficou muito mais curto, imutável e seguro.

---

### 🏷️ Bean Validation e Groups
Deixei o Spring conferir os dados para mim usando "etiquetas":

*   **Validações:** Usei `@NotBlank`, `@Size`, `@Min` e `@FutureOrPresent` para validar nomes, quantidades e datas automaticamente.
*   **Groups (Etiquetas):** Usei o `OnCreate` para ser rigoroso no cadastro (**POST/PUT**), exigindo tudo, mas permitir que os campos sejam opcionais na atualização (**PATCH**).

---

### ⚠️ Tratamento de Exceções
Fiz o sistema responder de forma amigável:

*   **Personalização:** Criei minhas próprias mensagens de erro (`ResourceNotFoundException`, `BadRequestException`).
*   **Status HTTP:** Isso evita que o usuário veja erros técnicos "feios" (500) e receba apenas a mensagem clara que eu escrevi, com o status correto (400 ou 404).

---

### ⚓ Injeção de Dependência
Conectei as peças do jeito certo:

*   **Construtor:** Usei o construtor para conectar as peças (Controller -> Service -> Repository). É o jeito mais seguro de "colar" as partes do sistema sem depender de `@Autowired` em campos.

---

🚩 **O QUE FALTA:** Criar documentação automática (Swagger/OpenAPI) para aprender como a API se explica sozinha!
