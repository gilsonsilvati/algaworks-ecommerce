package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class AutoRelacionamentoTest extends EntityManagerConfig {

    @Test
    public void verificarRelacionamento() {
        var categoriaPai = new Categoria();
        categoriaPai.setNome("Eletrônicos");

        var categoria = new Categoria();
        categoria.setNome("Celulares");
        categoria.setCategoriaPai(categoriaPai);

        entityManager.getTransaction().begin();
        entityManager.persist(categoriaPai);
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        assertNotNull(categoriaVerificacao.getCategoriaPai());

        var categoriaPaiVerificacao = entityManager.find(Categoria.class, categoriaPai.getId());
        assertFalse(categoriaPaiVerificacao.getCategorias().isEmpty());
    }

}
