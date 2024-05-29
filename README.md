# Desafio Java Pitang

## História de Usuário

Como desenvolvedor encarregado do backend de um Sistema de Usuários de Carros, busco criar uma API RESTful para simplificar o gerenciamento de usuários e seus respectivos carros.

### História de Usuário: Gerenciar Autenticação e Usuários

**Descrição:**
Como um administrador do sistema,
Gostaria de gerenciar autenticação e informações de usuários,
Para garantir o acesso seguro e a integridade dos dados no sistema.

### Critérios de Aceitação:

- [x] 1. Como usuário, desejo realizar autenticação fornecendo meu login e senha e retornando o token de acesso.
- [x] 2. Como usuário, desejo listar todos os usuários.
- [x] 3. Como usuário, desejo cadastrar um novo usuário com os seguintes campos: nome, sobrenome, e-mail, data de nascimento, login, senha, telefone e carros.
- [x] 4. Como usuário, desejo buscar um usuário pelo id.
- [x] 5. Como usuário, desejo remover um usuário pelo id.
- [x] 6. Como usuário, desejo atualizar um usuário pelo id.

### Especificações:

**Rotas que NÃO exigem autenticação:**

- **POST /api/signin**

  - **Descrição:** Realiza autenticação do usuário e retorna o token de acesso.
  - **Erros Possíveis:**
    - Login inexistente ou senha inválida: "Invalid login or password"

- **GET /api/users**

  - **Descrição:** Lista todos os usuários cadastrados.
  - **Erros Possíveis:** Nenhum

- **POST /api/users**

  - **Descrição:** Cadastra um novo usuário.
  - **Erros Possíveis:**
    - E-mail já existente: "Email already exists"
    - Login já existente: "Login already exists"
    - Campos inválidos: "Invalid fields"
    - Campos não preenchidos: "Missing fields"

- **GET /api/users/{id}**

  - **Descrição:** Busca um usuário pelo id.
  - **Erros Possíveis:** Nenhum

- **DELETE /api/users/{id}**

  - **Descrição:** Remove um usuário pelo id.
  - **Erros Possíveis:** Nenhum

- **PUT /api/users/{id}**
  - **Descrição:** Atualiza um usuário pelo id.
  - **Erros Possíveis:**
    - Campos inválidos: "Invalid fields"
    - Campos não preenchidos: "Missing fields"

### História de Usuário: Gerenciar Perfil do Usuário

**Descrição:**
Como um usuário autenticado,
Gostaria de visualizar minhas informações pessoais.

### Critérios de Aceitação:

- [ ] 1. Como usuário autenticado, consultar as minhas informações.

### Especificações:

**Rotas:**

- **GET /api/me**
  - **Descrição:** Retorna as informações do usuário logado, incluindo detalhes do perfil e metadados.
  - **Erros Possíveis:**
    1. Token não enviado: "Unauthorized"
    2. Token expirado: "Unauthorized - invalid session"

### História de Usuário: Gerenciar Carros do Usuário

**Descrição:**
Como um usuário autenticado,
Gostaria de gerenciar meus carros cadastrados,
Para manter meus dados atualizados no sistema.

### Critérios de Aceitação:

- [x] 1. Como usuário autenticado, desejo listar todos os carros do usuário autenticado.
- [ ] 2. Como usuário autenticado, desejo cadastrar um novo carro para o usuário autenticado.
- [ ] 3. Como usuário autenticado, desejo buscar um carro do usuário autenticado pelo id.
- [ ] 4. Como usuário autenticado, desejo remover um carro do usuário autenticado pelo id.
- [ ] 5. Como usuário autenticado, desejo atualizar um carro do usuário autenticado pelo id.

### Especificações:

**Rotas:**

- **GET /api/cars**

  - **Descrição:** Lista todos os carros cadastrados pelo usuário logado.
  - **Erros Possíveis:**
    1. Token não enviado: "Unauthorized"
    2. Token expirado: "Unauthorized - invalid session"

- **POST /api/cars**

  - **Descrição:** Cadastra um novo carro para o usuário logado.
  - **Erros Possíveis:**
    1. Token não enviado: "Unauthorized"
    2. Token expirado: "Unauthorized - invalid session"
    3. Placa já existente: "License plate already exists"
    4. Campos inválidos: "Invalid fields"
    5. Campos não preenchidos: "Missing fields"

- **GET /api/cars/{id}**

  - **Descrição:** Busca um carro do usuário logado pelo id.
  - **Erros Possíveis:**
    1. Token não enviado: "Unauthorized"
    2. Token expirado: "Unauthorized - invalid session"

- **DELETE /api/cars/{id}**

  - **Descrição:** Remove um carro do usuário logado pelo id.
  - **Erros Possíveis:**
    1. Token não enviado: "Unauthorized"
    2. Token expirado: "Unauthorized - invalid session"

- **PUT /api/cars/{id}**
  - **Descrição:** Atualiza um carro do usuário logado pelo id.
  - **Erros Possíveis:**
    1. Token não enviado: "Unauthorized"
    2. Token expirado: "Unauthorized - invalid session"
    3. Campos inválidos: "Invalid fields"
    4. Campos não preenchidos: "Missing fields"

# Solução

Nesta seção, detalhamos as justificativas e defesa técnica para a escolha das tecnologias utilizadas no projeto.

## Back-end

### Java 8

Java 8 foi escolhido por sua robustez e maturidade. A versão inclui recursos importantes como a API de Streams e expressões lambda, que facilitam a escrita de um código mais conciso e eficiente.

### Maven

Maven é utilizado para gerenciamento de dependências e build. Ele simplifica o processo de construção do projeto, garantindo que todas as dependências sejam resolvidas de forma consistente e proporcionando um modelo de configuração padronizado.

### Mapstruct

MapStruct é utilizado para mapeamento de objetos, eliminando a necessidade de escrever código boilerplate para conversão entre diferentes tipos de objetos. Isso aumenta a produtividade e reduz erros.

### JWT (JSON Web Token)

JWT é empregado para autenticação e autorização. Ele fornece uma forma segura e compacta de transmitir informações entre as partes como um objeto JSON, que pode ser verificado e confiável porque é assinado digitalmente.

### Banco de Dados MySQL

MySQL foi escolhido por ser um sistema de gerenciamento de banco de dados relacional amplamente utilizado e confiável. Ele oferece alto desempenho, segurança e facilidade de uso, tornando-o uma excelente escolha para aplicações de produção.

### Framework Spring Boot 2

Spring Boot 2 facilita a criação de aplicações Java stand-alone e produção-grade. Ele oferece configuração automática, métricas, segurança e outros recursos prontos para uso, reduzindo o tempo de desenvolvimento e aumentando a produtividade.

### Balanceador de Cargas e Service Registry (Eureka e Zuul)

- **Eureka**: Usado como serviço de registro para permitir a descoberta de serviços em uma arquitetura de microserviços. Ele facilita a comunicação entre serviços, permitindo que se registrem e descubram uns aos outros.
- **Zuul**: Atua como um gateway de API, fornecendo roteamento dinâmico, monitoramento, resiliência, segurança e muito mais. Ele gerencia todas as solicitações e as roteia para os serviços apropriados.

### Testes Unitários

Os testes unitários garantem que cada parte do código funcione corretamente. Utilizamos frameworks como JUnit e Mockito para criar testes robustos que ajudam a prevenir regressões e manter a qualidade do código.

### Swagger

Swagger é utilizado para documentação automática das APIs REST. Ele gera uma interface web interativa onde desenvolvedores e clientes podem visualizar e testar as operações da API, facilitando o desenvolvimento e a integração.

### Docker

Docker é utilizado para containerizar a aplicação, garantindo que ela possa ser executada de forma consistente em qualquer ambiente. Isso facilita o desenvolvimento, teste e implantação contínuos.

## Front-end

### Angular 17

Angular 17 foi escolhido por ser um framework moderno e robusto para desenvolvimento de aplicações web. Ele oferece uma arquitetura bem definida, duas vias de data binding e um ecossistema rico, permitindo a criação de aplicações escaláveis e de alta performance.

### Bootstrap

Bootstrap é utilizado para estilização e design responsivo. Ele fornece uma coleção de componentes CSS e JavaScript que facilitam a criação de interfaces de usuário atraentes e funcionais com um esforço mínimo.
