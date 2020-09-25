package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerConfig;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.TimeZone;

public class GroupByTest extends EntityManagerConfig {

    @Test
    public void agruparEFiltrarResultado() {
        StringBuilder sb = new StringBuilder();

        // Total de vendas por mês e do ano corrente (atual)
//        sb.append("select concat(year(p.dataCriacao), '-', function('monthname', p.dataCriacao)), sum(p.total) ");
//        sb.append("from Pedido p ");
//        sb.append("where year(p.dataCriacao) = year(current_date) ");
//        sb.append("group by year(p.dataCriacao), month(p.dataCriacao)");

        // Total de vendas por categoria e do ano e mês corrente (atual)
//        sb.append("select c.nome, sum(ip.precoProduto) from ItemPedido ip ");
//        sb.append("join ip.produto pro join pro.categorias c join ip.pedido p ");
//        sb.append("where year(p.dataCriacao) = year(current_date) and month(p.dataCriacao) = month(current_date) ");
//        sb.append("group by c.id");

        // Total de vendas por cliente e do ano corrente (atual) dos últimos 3 meses
        sb.append("select c.nome, sum(ip.precoProduto) from ItemPedido ip ");
        sb.append("join ip.pedido p join p.cliente c join ip.pedido p ");
        sb.append("where year(p.dataCriacao) = year(current_date) ");
        sb.append("and month(p.dataCriacao) >= (month(current_date) - 3) ");
        sb.append("group by c.id");

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(sb.toString(), Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }

    @Test
    public void agruparResultado() {
        StringBuilder sb = new StringBuilder();

        // Quantidade de produtos por categoria
//        sb.append("select c.nome, count(p.id) from Categoria c join c.produtos p ");
//        sb.append("group by c.id");

        // Total de vendas por mês
//        sb.append("select concat(year(p.dataCriacao), ' - ',  function('monthname', p.dataCriacao)), ");
//        sb.append("sum(p.total) from Pedido p ");
//        sb.append("group by year(p.dataCriacao), month(p.dataCriacao)");

        // Total de vendas por categoria
//        sb.append("select c.nome, sum(ip.precoProduto) from ItemPedido ip ");
//        sb.append("join ip.produto prod join prod.categorias c ");
//        sb.append("group by c.id");

        // Total de vendas por cliente
//        sb.append("select c.nome, sum(ip.precoProduto) from ItemPedido ip ");
//        sb.append("join ip.pedido p ");
//        sb.append("join p.cliente c ");
//        sb.append("group by c.id");

        // Total de vendas por dia e por categoria
        sb.append("select concat(year(p.dataCriacao), '/', month(p.dataCriacao), '/', day(p.dataCriacao)), ");
        sb.append("concat(c.nome, ': ', sum(ip.precoProduto)) ");
        sb.append("from ItemPedido ip join ip.pedido p join ip.produto pro join pro.categorias c ");
        sb.append("group by year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao), c.id ");
        sb.append("order by p.dataCriacao, c.nome");

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(sb.toString(), Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());

        lista.forEach(r -> System.out.println(r[0] + " - " + r[1]));
    }

}
