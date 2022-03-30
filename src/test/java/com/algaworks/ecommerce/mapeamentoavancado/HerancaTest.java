package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.*;
import com.algaworks.ecommerce.model.enums.Sexo;
import com.algaworks.ecommerce.model.enums.StatusPagamento;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Ignore
public class HerancaTest extends EntityManagerConfig {

    @Test
    public void deve_salvar_cliente() {
        var cliente = new Cliente();
        cliente.setNome("Fernanda Morais");
        cliente.setCpf("123456");
        cliente.setSexo(Sexo.FEMININO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        assertNotNull(clienteVerificacao.getId());
    }

    @Test
    public void deve_buscar_pagamentos() {
        List<Pagamento> pagamentos = entityManager.createQuery("SELECT p FROM Pagamento p").getResultList();
        assertFalse(pagamentos.isEmpty());
    }

    @Test
    public void deve_incluir_pagamento_pedido() {
        var pedido = entityManager.find(Pedido.class, 1);

        var boleto = new PagamentoBoleto();
        boleto.setPedido(pedido);
        boleto.setStatus(StatusPagamento.PROCESSANDO);
        boleto.setCodigoBarras("1234567890");

        entityManager.getTransaction().begin();
        entityManager.persist(boleto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificacao.getPagamento());
    }

}
