package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;

public class ElementCollectinTest extends EntityManagerConfig {

    @Test
    public void deve_aplicar_tags() {
        entityManager.getTransaction().begin();

        var produto = entityManager.find(Produto.class, 1);
        produto.setTags(Arrays.asList("ebook", "livro-digital"));

        entityManager.getTransaction().commit();

        entityManager.clear();

        var produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertFalse(produtoVerificacao.getTags().isEmpty());
    }

    @Test
    public void deve_aplicar_atributos() {
        entityManager.getTransaction().begin();

        var produto = entityManager.find(Produto.class, 1);
        produto.setAtributos(Arrays.asList(new Atributo("Tela", "350x600")));

        entityManager.getTransaction().commit();

        entityManager.clear();

        var produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertFalse(produtoVerificacao.getAtributos().isEmpty());
    }

}
