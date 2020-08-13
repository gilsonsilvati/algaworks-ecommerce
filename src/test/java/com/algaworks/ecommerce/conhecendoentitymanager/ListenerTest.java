package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ListenerTest extends EntityManagerConfig {

    @Test
    public void carregarEntidades() {
        var produto = entityManager.find(Produto.class, 1);
        var pedido = entityManager.find(Pedido.class, 1);
    }

    @Test
    public void verificarListener() {
        var cliente = entityManager.find(Cliente.class, 1);

        var pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(BigDecimal.TEN);

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
        Assert.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
    }

}
