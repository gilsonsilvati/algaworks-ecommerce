package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerConfig {

    @Test
    public void impedirOperacaoComBancoDeDados() {
        var produto = entityManager.find(Produto.class, 1);
        entityManager.detach(produto); // Desanexa uma instancia do entityManager

        entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite 2ª Geração");
        entityManager.getTransaction().commit();

        entityManager.clear(); // Necessário só para persist ou merge

        var produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertEquals("Kindle", produtoVerificacao.getNome());
    }

    @Test
    public void inserirObjetoComMerge() {
        var produto = new Produto();

        produto.setNome("Microfone Rode Vide");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setPreco(new BigDecimal(1000));

        entityManager.getTransaction().begin();
        var produtoSalvo = entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear(); // Necessário só para persist ou merge

        var produtoVerificacao = entityManager.find(Produto.class, produtoSalvo.getId());

        Assert.assertNotNull(produtoVerificacao);
    }

    @Test
    public void atualizarObjetoGerenciado() {
        var produto = entityManager.find(Produto.class, 1);
        produto.setNome("Kindle Paperwhite 2ª Geração");

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        entityManager.clear(); // Necessário só para persist ou merge

        var produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertEquals("Kindle Paperwhite 2ª Geração", produtoVerificacao.getNome());
    }

    @Test
    public void atualizarObjeto() {
        var produto = new Produto();
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo Kindle.");
        produto.setPreco(new BigDecimal(599));

        entityManager.getTransaction().begin();
        var produtoSalvo = entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear(); // Necessário só para persist ou merge

        var produtoVerificacao = entityManager.find(Produto.class, produtoSalvo.getId());

        Assert.assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());
    }

    @Test
    public void removerObjeto() {
        var produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        var produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertNull(produtoVerificacao);
    }

    @Test
    public void inserirOPrimeiroObjeto() {
        var produto = new Produto();

        produto.setNome("Câmera Canon");
        produto.setDescricao("A melhor definição para suas fotos.");
        produto.setPreco(new BigDecimal(5000));

        // Opção mais indicada
//        entityManager.getTransaction().begin();
//        entityManager.persist(produto);
//        entityManager.getTransaction().commit();

        // Opção menos indicada
        entityManager.persist(produto);

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        entityManager.clear(); // Necessário só para persist ou merge

        var produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertNotNull(produtoVerificacao);
    }

}
