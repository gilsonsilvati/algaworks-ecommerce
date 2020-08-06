package com.algaworks.ecommerce.mapeamentoavancao;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
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
        produto.setAtributos(Collections.singletonList(new Atributo("Tela", "350x600")));

        entityManager.getTransaction().commit();

        entityManager.clear();

        var produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertFalse(produtoVerificacao.getAtributos().isEmpty());
    }

    @Test
    public void deve_aplicar_contatos() {
        entityManager.getTransaction().begin();

        var cliente = entityManager.find(Cliente.class, 1);
        cliente.setContatos(Collections.singletonMap("email", "fernando@gmail.com"));

        entityManager.getTransaction().commit();

        entityManager.clear();

        var clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        assertEquals( "fernando@gmail.com", clienteVerificacao.getContatos().get("email"));
    }

}
