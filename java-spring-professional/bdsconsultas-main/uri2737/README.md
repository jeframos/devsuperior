# URI 2737 - Pedidos com Dados do Cliente

## 📌 Descrição

Desafio que envolve combinar dados de pedidos com informações dos clientes para gerar relatórios consolidados.

**Dificuldade**: ⭐⭐ Intermediário

## 🎯 O Desafio

Escrever uma query SQL que:
1. Combina dados de pedidos e clientes
2. Aplica filtros específicos
3. Calcula agregações quando necessário

## 🔍 Solução SQL

```sql
SELECT 
    o.id as order_id,
    o.order_date,
    c.name as customer_name,
    c.email,
    SUM(oi.quantity * oi.price) as order_total,
    COUNT(oi.id) as items_count
FROM orders o
INNER JOIN customers c ON o.customer_id = c.id
LEFT JOIN order_items oi ON o.id = oi.order_id
GROUP BY o.id, o.order_date, c.name, c.email
ORDER BY o.order_date DESC;
```

## 📝 Explicação

- **INNER JOIN com customers** - Garante que temos dados do cliente
- **LEFT JOIN com order_items** - Inclui pedidos mesmo sem itens
- **GROUP BY** - Agrupa por pedido e cliente
- **SUM() e COUNT()** - Agregações do pedido

## 🧪 Dados de Teste

Resultado esperado exemplo:

| order_id | order_date | customer_name | email | order_total | items_count |
|---|---|---|---|---|---|
| 5 | 2024-01-15 | João Silva | joao@example.com | 1500.00 | 3 |
| 4 | 2024-01-10 | Maria Santos | maria@example.com | 899.99 | 2 |

## 🔗 Endpoints REST

```bash
GET /orders/with-customer-details
GET /customers/{id}/orders
GET /orders/{id}/details
```

## 🚀 Como Executar

```bash
cd uri2737
mvn spring-boot:run
```

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

