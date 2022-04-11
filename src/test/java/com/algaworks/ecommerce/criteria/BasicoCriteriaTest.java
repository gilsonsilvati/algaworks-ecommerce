package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static org.junit.Assert.*;

public class BasicoCriteriaTest extends EntityManagerConfig {

    @Test
    public void projetarOResultado() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.multiselect(
                root.get("id"),
                root.get("nome")
        );

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> produtos = typedQuery.getResultList();

        assertFalse(produtos.isEmpty());

        produtos.forEach(produto -> System.out.println("ID: " + produto[0] + ", Nome: " + produto[1]));
    }

    @Test
    public void selecionarTodosOsProdutosExercicio() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> produtos = typedQuery.getResultList();

        assertNotNull(produtos);
        assertFalse(produtos.isEmpty());
    }

    @Test
    public void selecionarUmAtributoParaRetorno() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root.get("cliente"));
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
        Cliente cliente = typedQuery.getSingleResult();

        assertEquals("123", cliente.getCpf());
    }

    @Test
    public void buscarPorIdentificador() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        //String jpql = "select p from Pedido p where p.id = 1";
        criteriaQuery.select(root); // Nao precisa quando o tipo do Root é o mesmo do CriteriaQuery
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<Pedido> typedQuery = entityManager
                //.createQuery(jpql, Pedido.class);
                .createQuery(criteriaQuery);

        Pedido pedido = typedQuery.getSingleResult();
        assertNotNull(pedido);
    }

}
