# URI 2611 - Venda com Produto

## 📌 Descrição

Desafio que envolve relacionar vendas com produtos e calcular valores totais de vendas por produto.

**Dificuldade**: ⭐⭐ Intermediário

## 🎯 O Desafio

Escrever uma query SQL que:
1. Relaciona vendas com seus respectivos produtos
2. Calcula o valor total vendido de cada produto
3. Mostra informações do produto junto com totalizações

## 🔍 Solução SQL

```sql
SELECT 
    p.id,
    p.name,
    COUNT(s.id) as sales_count,
    SUM(s.quantity * s.price) as total_value
FROM products p
INNER JOIN sales s ON p.id = s.product_id
GROUP BY p.id, p.name
ORDER BY total_value DESC;
```

## 📝 Explicação

- **SUM(s.quantity * s.price)** - Calcula valor total por produto
- **COUNT(s.id)** - Conta número de vendas
- **GROUP BY** - Agrupa por produto
- **ORDER BY total_value DESC** - Ordena do maior para o menor

## 🧪 Dados de Teste

Resultado esperado exemplo:

| id | name | sales_count | total_value |
|----|------|---|---|
| 1 | Notebook | 5 | 25000.00 |
| 2 | Mouse | 15 | 750.00 |
| 3 | Teclado | 8 | 1600.00 |

## 🔗 Endpoints REST

```bash
GET /products/sales-summary
GET /products/{id}/total-sales
```

## 🚀 Como Executar

```bash
cd uri2611
mvn spring-boot:run
```

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

