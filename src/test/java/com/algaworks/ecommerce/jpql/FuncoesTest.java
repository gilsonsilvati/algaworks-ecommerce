package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerConfig;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.TimeZone;

public class FuncoesTest extends EntityManagerConfig {

    @Test
    public void aplicarFuncaoNumero() {
        // Funções Numero: abs(), mod(), sqtr()

        String jpql = "select abs(-10), mod(3, 2), sqrt(9) from Pedido";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));
    }

    @Test
    public void aplicarFuncaoData() {
        // Config TimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        // Funções Data: current_date, current_time, current_timestamp, year, month, day, hour, minute, second

//        String jpql = "select current_date, current_time, current_timestamp from Pedido p";
//        String jpql = "select year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao) from Pedido p";
        String jpql = "select hour(p.dataCriacao), minute(p.dataCriacao), second(p.dataCriacao) from Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));
    }

    @Test
    public void aplicarFuncaoString() {
        // Funções String: concat, length, locate, substring, lower, upper, trim

//        String jpql = "select c.nome, concat('Categoria: ', c.nome) from Categoria c ";
//        String jpql = "select c.nome, length(c.nome) from Categoria c ";
//        String jpql = "select c.nome, locate('a', c.nome) from Categoria c ";
//        String jpql = "select c.nome, substring(c.nome, 1, 2) from Categoria c ";
//        String jpql = "select c.nome, lower(c.nome) from Categoria c ";
//        String jpql = "select c.nome, upper(c.nome) from Categoria c ";
//        String jpql = "select c.nome, trim(c.nome) from Categoria c ";

        String jpql = "select c.nome, length(c.nome) from Categoria c " +
                " where substring(c.nome, 1, 1) = 'N'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
    }

}
