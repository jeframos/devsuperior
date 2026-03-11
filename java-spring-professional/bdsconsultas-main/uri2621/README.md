# URI 2621 - Produtos Mais Vendidos

## 📌 Descrição

Desafio que envolve identificar os produtos mais vendidos ordenados por quantidade de vendas.

**Dificuldade**: ⭐⭐ Intermediário

## 🎯 O Desafio

Escrever uma query SQL que:
1. Identifica os produtos mais vendidos
2. Ordena por quantidade vendida (decrescente)
3. Retorna informações do produto e quantidade

## 🔍 Solução SQL

```sql
SELECT 
    p.id,
    p.name,
    SUM(s.quantity) as total_quantity
FROM products p
INNER JOIN sales s ON p.id = s.product_id
GROUP BY p.id, p.name
ORDER BY total_quantity DESC
LIMIT 10;
```

## 📝 Explicação

- **SUM(s.quantity)** - Soma total de quantidade vendida
- **GROUP BY** - Agrupa por produto
- **ORDER BY DESC** - Do maior para o menor
- **LIMIT 10** - Retorna apenas os 10 primeiros

## 🧪 Dados de Teste

Resultado esperado exemplo:

| id | name | total_quantity |
|----|------|---|
| 2 | Mouse | 150 |
| 3 | Teclado | 120 |
| 1 | Notebook | 80 |

## 🔗 Endpoints REST

```bash
GET /products/most-sold
GET /products/most-sold?limit=10
```

## 🚀 Como Executar

```bash
cd uri2621
mvn spring-boot:run
```

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

