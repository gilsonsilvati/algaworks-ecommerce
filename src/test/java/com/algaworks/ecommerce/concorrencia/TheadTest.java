package com.algaworks.ecommerce.concorrencia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class TheadTest {

    @Test
    @DisplayName("Entendendo o conceito de concorrência")
    void entenderThreads() {
        Runnable runnable1 = () -> {
            log("Runnable 01 vai esperar 3 segundos.");
            esperar(3);
            log("Runnable 01 concluído.");
        };

        Runnable runnable2 = () -> {
            log("Runnable 02 vai esperar 1 segundos.");
            esperar(1);
            log("Runnable 02 concluído.");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread foi interrompida: " + e.getMessage());
        }

        log("Encerrando método de teste.");
    }

    private static void log(Object obj) {
        System.out.printf("""
                [LOG %s] %s
                %n""", Instant.now(), obj);
    }

    private static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Boa prática para restaurar o estado de interrupção
            System.err.println("Thread foi interrompida: " + e.getMessage());
        }
    }
}
