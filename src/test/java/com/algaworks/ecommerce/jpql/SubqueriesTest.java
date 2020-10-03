package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class SubqueriesTest extends EntityManagerConfig {

    @Test
    public void pesquisarSubqueriesComExists() {
        StringBuilder builder = new StringBuilder();

        builder.append("select p from Produto p where exists ");
        builder.append("(select 1 from ItemPedido ip join ip.produto p2 where p2 = p)");

        TypedQuery<Produto> typedQuery = entityManager.createQuery(builder.toString(), Produto.class);
        List<Produto> lista = typedQuery.getResultList();

        assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarSubqueriesComIn() {
        StringBuilder builder = new StringBuilder();

        builder.append("select p from Pedido p where p.id in ");
        builder.append("(select p2.id from ItemPedido i2 join i2.pedido p2 join i2.produto prod2 ");
        builder.append("where prod2.preco > 100)");

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(builder.toString(), Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();

        assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarSubqueries() {
        StringBuilder builder = new StringBuilder();

        // Bons clientes - v1
        builder.append("select c from Cliente c ");
        builder.append("where (select sum(p.total) from c.pedidos p) > 500");

        // Bons clientes - v2
//        builder.append("select c from Cliente c ");
//        builder.append("where (select sum(total) from Pedido p where p.cliente = c) > 500");

        // Todos os pedidos acima da média de vendas
//        builder.append("select p from Pedido p ");
//        builder.append("where p.total > (select avg(total) from Pedido)");

        // Produto ou produtos mais caros da base
//        builder.append("select p from Produto p ");
//        builder.append("where p.preco = (select max(preco) from Produto)");

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(builder.toString(), Cliente.class);
        List<Cliente> lista = typedQuery.getResultList();

        assertFalse(lista.isEmpty());

        lista.forEach(r -> System.out.println("\nID: " + r.getId() + " - Nome: " + r.getNome()));
    }

}
