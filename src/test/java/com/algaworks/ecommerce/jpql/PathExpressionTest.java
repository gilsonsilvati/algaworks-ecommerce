package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class PathExpressionTest extends EntityManagerConfig {

    @Test
    public void buscarPedidosComProdutoEspecifico() {
        String jpql = "SELECT p FROM Pedido p join p.itens i WHERE i.id.produtoId = 1";
//        String jpql = "SELECT p FROM Pedido p join p.itens i WHERE i.produto.id = 1";
//        String jpql = "SELECT p FROM Pedido p join p.itens i join i.produto pro WHERE pro.id = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarPathExpressions() {
        String jpql = "SELECT p.cliente.nome FROM Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
