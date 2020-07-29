package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;

public class RelacionamentoManyToManyTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        var produto = entityManager.find(Produto.class, 1);
        var categoria = entityManager.find(Categoria.class, 1);

        entityManager.getTransaction().begin();
//        categoria.setProdutos(Arrays.asList(produto)); -> Error: Non Owner (Não Proprietário) -> mappedBy
        produto.setCategorias(Arrays.asList(categoria)); // -> OK: Owner (Proprietário)
        entityManager.getTransaction().commit();

        entityManager.clear();

        var categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        assertFalse(categoriaVerificacao.getProdutos().isEmpty());
    }

}
