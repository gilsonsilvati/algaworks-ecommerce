package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.enums.Sexo;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import static org.junit.Assert.assertNotNull;

public class SecondaryTableTest extends EntityManagerConfig {

    @Test
    public void deve_salvar_cliente() {
        var cliente = new Cliente();
        cliente.setNome("Carlos Finotti");
        cliente.setSexo(Sexo.MASCULINO);
        cliente.setDataNascimento(LocalDate.of(Year.now().getValue(), Month.AUGUST, 8));

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        assertNotNull(clienteVerificacao.getSexo());
    }

}
