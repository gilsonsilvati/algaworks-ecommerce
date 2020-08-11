package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

public class CachePrimeiroNivelTest extends EntityManagerConfig {

    @Test
    public void verificarCache() {
        var produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());

        System.out.println("-----------------------------------------");
        // Sai do cache de primeiro nivel
//        entityManager.clear();

        // Continua no cache de primeiro nivel
//        entityManager.getTransaction().begin();
//        produto = entityManager.merge(produto);
//        entityManager.getTransaction().commit();

        var produtoCache = entityManager.find(Produto.class, produto.getId());
        System.out.println(produtoCache.getNome());
    }

}
