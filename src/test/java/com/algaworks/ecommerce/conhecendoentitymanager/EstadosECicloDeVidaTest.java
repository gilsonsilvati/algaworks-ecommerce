package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Test;

public class EstadosECicloDeVidaTest extends EntityManagerConfig {

    @Test
    public void analisarEstados() {
        var categoriaNovo = new Categoria();
        categoriaNovo.setNome("Eletro");

        var categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);

        var categoriaGerenciada = entityManager.find(Categoria.class, 1);

        entityManager.remove(categoriaGerenciada);
        entityManager.persist(categoriaGerenciada);

        entityManager.detach(categoriaGerenciada);
    }

}
