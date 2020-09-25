package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerConfig;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.TimeZone;

public class GroupByTest extends EntityManagerConfig {

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
        sb.append("select c.nome, sum(ip.precoProduto) from ItemPedido ip ");
        sb.append("join ip.produto prod join prod.categorias c ");
        sb.append("group by c.id");

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(sb.toString(), Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(r -> System.out.println(r[0] + ", " + r[1]));
    }

}
