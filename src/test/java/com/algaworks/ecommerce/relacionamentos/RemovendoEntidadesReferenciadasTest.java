package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerConfig {

    @Test
    public void deve_remover_entidade_relacionada() {
        var pedido = entityManager.find(Pedido.class, 1);

//        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().forEach(item -> entityManager.remove(item));
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

//        entityManager.clear();

        var pedidoVerificacao = entityManager.find(Pedido.class, 1);
//        assertNull(pedidoVerificacao);
    }

}
