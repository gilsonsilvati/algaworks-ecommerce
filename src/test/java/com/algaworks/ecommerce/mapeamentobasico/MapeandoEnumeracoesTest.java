package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.enums.Sexo;
import org.junit.Assert;
import org.junit.Test;

public class MapeandoEnumeracoesTest extends EntityManagerConfig {

    @Test
    public void testarEnum() {
        var cliente = new Cliente();
        cliente.setNome("José Mineiro");
        cliente.setSexo(Sexo.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertNotNull(clienteVerificacao);
    }

}
