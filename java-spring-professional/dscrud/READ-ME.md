# SISTEMA DSCRUD de clientes

## CLASSE
![image](https://github.com/user-attachments/assets/4f45a213-16f7-4ca5-ad04-6d360ec4e0bd)

## ENDPOINTs IMPLEMENTADOS
Busca de cliente por id
 - GET /clients/1

Busca paginada de clientes
 - GET /clients?page=0&size=6&sort=name

Inserção de novo cliente
 - POST /clients
{
"name": "Maria Silva",
"cpf": "12345678901",
"income": 6500.0,
"birthDate": "1994-07-20",
"children": 2
}

Atualização de cliente
 - PUT /clients/1
{
"name": "Maria Silvaaa",
"cpf": "12345678901",
"income": 6500.0,
"birthDate": "1994-07-20",
"children": 2
}

Deleção de cliente
 - DELETE /clients/1
