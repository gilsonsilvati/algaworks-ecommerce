package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class EstrategiaChavePrimariaTest extends EntityManagerConfig {

    @Test
    public void testarEstrategiaChave() {
        var categoria = new Categoria();
        categoria.setNome("Natação");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());

        Assert.assertNotNull(categoriaVerificacao);
    }

}
