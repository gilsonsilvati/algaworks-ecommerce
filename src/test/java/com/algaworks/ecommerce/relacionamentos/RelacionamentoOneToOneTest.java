package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.enums.StatusPagamento;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class RelacionamentoOneToOneTest extends EntityManagerConfig {

    @Test
    public void verificarRelacionamento() {
        var pedido = entityManager.find(Pedido.class, 1);

        var pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setNumero("1234");
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
        pagamentoCartao.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificacao.getPagamentoCartao());
    }

    @Test
    public void verificarRelacionamentoPedidoNotaFiscal() {
        var pedido = entityManager.find(Pedido.class, 1);

        var notaFiscal = new NotaFiscal();
        notaFiscal.setXml("TESTE");
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        assertNotNull(pedidoVerificacao.getNotaFiscal());
    }

}
