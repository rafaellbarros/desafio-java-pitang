# Desafio Java Pitang

## História de Usuário

Como desenvolvedor encarregado do backend de um Sistema de Usuários de Carros, busco criar uma API RESTful para simplificar o gerenciamento de usuários e seus respectivos carros.

### Critérios de Aceitação Usuários:

1. Como usuário, desejo acessar a minha conta fornecendo meu login e senha.
2. Como usuário, desejo listar todos os usuários.
3. Como usuário, desejo cadastrar um novo usuário com os seguintes campos: nome, sobrenome, e-mail, data de nascimento, login, senha, telefone e carros.
4. Como usuário, desejo buscar um usuário pelo id.
5. Como usuário, desejo remover um usuário pelo id.
6. Como usuário, desejo atualizar um usuário pelo id.

### Especificações:

**Rotas que NÃO exigem autenticação:**

- **Rota:** /api/signin

  - **Descrição:** Autenticação do usuário. Deve fornecer um token JWT.
  - **Erros Possíveis:**
    1. Login inexistente ou senha inválida: "Invalid login or password".
    2. E-mail já existente: "Email already exists".
    3. Login já existente: "Login already exists".
    4. Campos inválidos: "Invalid fields".
    5. Campos não preenchidos: "Missing fields".

- **Rota:** /api/users

  - **Descrição:** Listar todos os usuários.
  - **Erro Possível:** N/A

- **Rota:** /api/users

  - **Descrição:** Cadastrar um novo usuário.
  - **Erros Possíveis:** 2, 3, 4, 5

- **Rota:** /api/users/{id}

  - **Descrição:** Buscar um usuário pelo ID.
  - **Erro Possível:** N/A

- **Rota:** /api/users/{id}

  - **Descrição:** Remover um usuário pelo ID.
  - **Erro Possível:** N/A

- **Rota:** /api/users/{id}
  - **Descrição:** Atualizar um usuário pelo ID.
  - **Erros Possíveis:** 2, 3, 4, 5
