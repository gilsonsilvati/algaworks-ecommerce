package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.enums.Sexo;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroCrudTest extends EntityManagerConfig {

    @Test
    public void buscar() {
        var cliente = entityManager.find(Cliente.class, 2);

        Assert.assertNotNull(cliente);
    }

    @Test
    public void inserirRegistro() {
        var cliente = new Cliente();
        cliente.setNome("Leka Show Teste");
        cliente.setCpf("000");
        cliente.setSexo(Sexo.FEMININO);

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear(); // Necessário só para persist ou merge

        var clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertNotNull(clienteVerificacao);
    }

    @Test
    public void atualizarRegistro() {
        var cliente = entityManager.find(Cliente.class, 1);
        cliente.setNome("Gabriel Show Teste");
        cliente.setCpf("123");
        cliente.setSexo(Sexo.MASCULINO);

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        entityManager.clear(); // Necessário só para persist ou merge

        var clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertEquals("Gabriel Show Teste", clienteVerificacao.getNome());
    }

    @Test
    public void removerRegistro() {
        var cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        var clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertNull(clienteVerificacao);
    }

}
