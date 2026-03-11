# URI 2609 - Cidades com Clientes

## 📌 Descrição

Desafio que envolve encontrar todas as cidades que possuem clientes cadastrados na base de dados.

**Dificuldade**: ⭐ Básico

## 🎯 O Desafio

Escrever uma query SQL que:
1. Seleciona apenas cidades que têm clientes
2. Remove duplicatas (DISTINCT)
3. Ordena alfabeticamente

## 🔍 Solução SQL

```sql
SELECT DISTINCT c.city
FROM customers c
ORDER BY c.city ASC;
```

## 📝 Explicação

- **SELECT DISTINCT** - Remove linhas duplicadas
- **FROM customers** - Tabela de origem
- **ORDER BY** - Ordena resultado alfabeticamente

## 🧪 Dados de Teste

Resultado esperado exemplo:

| city |
|------|
| Belo Horizonte |
| Brasília |
| Curitiba |
| São Paulo |

## 🔗 Endpoints REST

```bash
GET /cities/with-customers
GET /cities/count
```

## 🚀 Como Executar

```bash
cd uri2609
mvn spring-boot:run
```

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

