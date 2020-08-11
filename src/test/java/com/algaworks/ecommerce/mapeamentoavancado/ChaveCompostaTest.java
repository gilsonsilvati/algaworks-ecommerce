package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.*;
import com.algaworks.ecommerce.model.enums.StatusPedido;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ChaveCompostaTest extends EntityManagerConfig {

    @Test
    public void deve_salvar_item() {
        var cliente = entityManager.find(Cliente.class, 1);
        var produto = entityManager.find(Produto.class, 1);

        var pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(produto.getPreco());


        var itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificacao);
        assertFalse(pedidoVerificacao.getItens().isEmpty());
    }

    @Test
    public void bucarItem() {
        var itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1, 1));
        assertNotNull(itemPedido);
    }
}
