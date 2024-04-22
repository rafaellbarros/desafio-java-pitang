# Desafio Java Pitang

## História de Usuário

Como desenvolvedor encarregado do backend de um Sistema de Usuários de Carros, busco criar uma API RESTful para simplificar o gerenciamento de usuários e seus respectivos carros.

### História de Usuário: Gerenciar Autenticação e Usuários

**Descrição:**
Como um administrador do sistema,
Gostaria de gerenciar autenticação e informações de usuários,
Para garantir o acesso seguro e a integridade dos dados no sistema.

### Critérios de Aceitação:

1. Como usuário, desejo realizar autenticação fornecendo meu login e senha e retornando o token de acesso.
2. Como usuário, desejo listar todos os usuários.
3. Como usuário, desejo cadastrar um novo usuário com os seguintes campos: nome, sobrenome, e-mail, data de nascimento, login, senha, telefone e carros.
4. Como usuário, desejo buscar um usuário pelo id.
5. Como usuário, desejo remover um usuário pelo id.
6. Como usuário, desejo atualizar um usuário pelo id.

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

1. Como usuário autenticado, consultar as minhas informações.

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

1. Como usuário autenticado, desejo listar todos os carros do usuário autenticado.
2. Como usuário autenticado, desejo cadastrar um novo carro para o usuário autenticado.
3. Como usuário autenticado, desejo buscar um carro do usuário autenticado pelo id.
4. Como usuário autenticado, desejo remover um carro do usuário autenticado pelo id.
5. Como usuário autenticado, desejo atualizar um carro do usuário autenticado pelo id.

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
