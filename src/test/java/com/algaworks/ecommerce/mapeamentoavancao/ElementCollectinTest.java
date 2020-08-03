package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ElementCollectinTest extends EntityManagerConfig {

    @Test
    public void deve_aplicar_tags() {
        entityManager.getTransaction().begin();

        var produto = entityManager.find(Produto.class, 1);
        produto.setTags(Arrays.asList("ebook", "livro-digital"));

        entityManager.getTransaction().commit();

        entityManager.clear();

        var produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVerificacao.getTags().isEmpty());
    }

}
