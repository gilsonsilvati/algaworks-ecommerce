package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerConfig;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Ignore
public class SalvandoArquivosTest extends EntityManagerConfig {

    @Test
    public void deve_salvar_xml_nota() {
        var pedido = entityManager.find(Pedido.class, 1);

        var notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml(carregarNotaFiscal());

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        assertNotNull(notaFiscalVerificacao.getXml());
        assertTrue(notaFiscalVerificacao.getXml().length > 0);

        /* try {
            OutputStream out = new FileOutputStream(
                    Files.createFile(Paths.get(System.getProperty("user.home") + "/xml.xml")).toFile());

            out.write(notaFiscalVerificacao.getXml());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } */
    }

    @Test
    public void deve_salvar_foto_produto() {
        var produto = entityManager.find(Produto.class, 1);
        produto.setFoto(carregarFoto());

        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        entityManager.clear();

        var produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertNotNull(produtoVerificacao.getFoto());
        assertTrue(produtoVerificacao.getFoto().length > 0);
    }

    private static byte[] carregarFoto() {
        return carregarArquivo("/kindle.jpg");
    }

    private static byte[] carregarNotaFiscal() {
        return carregarArquivo("/nota-fiscal.xml");
    }

    private static byte[] carregarArquivo(String nome) {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream(nome).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
