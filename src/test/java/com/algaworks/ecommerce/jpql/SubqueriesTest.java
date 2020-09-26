package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class SubqueriesTest extends EntityManagerConfig {

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
