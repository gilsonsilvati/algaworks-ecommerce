package com.algaworks.ecommerce.cache;

import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.Cache;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EhCacheTest {

    protected static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @Test
    @DisplayName("Deve validar o cache de segundo nivel")
    void deveValidarEhCache() {
        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        log(">>> Buscando e incluindo no cache... <<<");
        entityManager1
                .createQuery("SELECT p FROM Pedido p", Pedido.class)
                .getResultList();
        log("---");

        esperar(1);
        assertTrue(cache.contains(Pedido.class, 2));

        entityManager2.find(Pedido.class, 2);

        esperar(3);
        assertFalse(cache.contains(Pedido.class, 2));

        entityManager1.close();
        entityManager2.close();
    }

    @AfterAll
    public static void tearDownAfterClass() {
        entityManagerFactory.close();
    }

    private static void log(Object obj) {
        System.out.println("[LOG " + System.currentTimeMillis() + "] " + obj);
    }

    private static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
