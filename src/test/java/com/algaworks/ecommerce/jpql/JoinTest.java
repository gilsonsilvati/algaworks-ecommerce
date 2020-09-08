package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerConfig;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTest extends EntityManagerConfig {

    @Test
    public void fazerLeftJoin() {
        String jpql = "SELECT p FROM Pedido p LEFT JOIN p.pagamento pag ON pag.status = 'PROCESSANDO'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void fazerJoin() {
        String jpql = "SELECT p FROM Pedido p JOIN p.pagamento pag";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

}
