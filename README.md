# Desafio Java Pitang

## História de Usuário

Como desenvolvedor encarregado do backend de um Sistema de Usuários de Carros, busco criar uma API RESTful para simplificar o gerenciamento de usuários e seus respectivos carros.

### Critérios de Aceitação:

1. Como usuário, desejo criar uma conta fornecendo meu nome, sobrenome, e-mail, data de nascimento, login, senha e telefone.
2. Como usuário, necessito poder atualizar minhas informações, incluindo nome, sobrenome, e-mail, data de nascimento, login, senha e telefone.
3. Como usuário, pretendo visualizar minhas informações de perfil, incluindo nome, sobrenome, e-mail, data de nascimento, login, senha e telefone.
4. Como usuário, desejo adicionar um carro à minha lista, fornecendo detalhes como ano de fabricação, placa, modelo e cor.
5. Como usuário, espero visualizar a lista de carros que possuo, incluindo detalhes como ano de fabricação, placa, modelo e cor de cada carro.
6. Como usuário, desejo poder atualizar as informações de um carro específico, incluindo ano de fabricação, placa, modelo e cor.
7. Como usuário, desejo poder excluir um carro específico da minha lista de carros.
8. Como usuário, desejo poder excluir minha conta, removendo todas as informações associadas, incluindo meus carros.

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
