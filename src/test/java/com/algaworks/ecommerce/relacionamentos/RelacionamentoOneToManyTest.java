package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.*;
import com.algaworks.ecommerce.model.enums.StatusPedido;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;

public class RelacionamentoOneToManyTest extends EntityManagerConfig {

    @Test
    public void verificarRelacionamento() {
        var cliente = entityManager.find(Cliente.class, 1);

        var pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(BigDecimal.TEN);
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());

        assertFalse(clienteVerificacao.getPedidos().isEmpty());
    }

    @Test
    public void verificarRelacionamentoPedido() {
        var cliente = entityManager.find(Cliente.class, 1);
        var produto = entityManager.find(Produto.class, 1);

        var pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(BigDecimal.TEN);
        pedido.setCliente(cliente);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertFalse(pedidoVerificacao.getItens().isEmpty());
    }

}
