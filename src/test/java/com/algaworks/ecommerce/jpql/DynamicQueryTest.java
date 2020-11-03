package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Produto;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class DynamicQueryTest extends EntityManagerConfig {

    @Test
    public void executarConsultaDinamica() {
        Produto consultado = new Produto();
        consultado.setNome("Câmera GoPro");

        List<Produto> lista = pesquisar(consultado);

        Assert.assertFalse(lista.isEmpty());
        Assert.assertEquals("Câmera GoPro Hero 7", lista.get(0).getNome());
    }

    private List<Produto> pesquisar(Produto consultado) {
        StringBuilder jpql = new StringBuilder("select p from Produto p where 1 = 1");

        if (StringUtils.isNotBlank(consultado.getNome())) {
            jpql.append(" and p.nome like concat('%', :nome, '%')");
        }

        if (StringUtils.isNotBlank(consultado.getDescricao())) {
            jpql.append(" and p.descricao like concat('%', :descricao, '%')");
        }

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql.toString(), Produto.class);

        if (StringUtils.isNotBlank(consultado.getNome())) {
            typedQuery.setParameter("nome", consultado.getNome());
        }

        if (StringUtils.isNotBlank(consultado.getDescricao())) {
            typedQuery.setParameter("descricao", consultado.getDescricao());
        }

        return typedQuery.getResultList();
    }

}
