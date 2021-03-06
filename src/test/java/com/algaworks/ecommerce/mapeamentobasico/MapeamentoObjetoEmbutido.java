package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.EnderecoEntrega;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.enums.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutido extends EntityManagerConfig {

    @Test
    public void analizarMapeamentoObjetoEmbutido() {
        var enderecoEntrega = criaEnderecoEntrega();
        var pedido = criaPedido(enderecoEntrega);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificacao);
    }

    private EnderecoEntrega criaEnderecoEntrega() {
        var enderecoEntrega = new EnderecoEntrega();
        enderecoEntrega.setCep("00000000");
        enderecoEntrega.setLogradouro("Rua das Laranjeiras");
        enderecoEntrega.setNumero("123");
        enderecoEntrega.setEstado("MG");
        enderecoEntrega.setCidade("Uberlândia");
        enderecoEntrega.setBairro("Centro");

        return enderecoEntrega;
    }

    private Pedido criaPedido(EnderecoEntrega enderecoEntrega) {
        var cliente = entityManager.find(Cliente.class, 1);

        var pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setCliente(cliente);
        pedido.setEnderecoEntrega(enderecoEntrega);

        return pedido;
    }

}
