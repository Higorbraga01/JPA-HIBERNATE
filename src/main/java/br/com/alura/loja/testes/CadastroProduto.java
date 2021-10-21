package br.com.alura.loja.testes;

import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroProduto {

    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Xiaomi Redmi");
        celular.setDescricao("RedMi 9");
        celular.setPreco(new BigDecimal("800"));
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        em.getTransaction().begin();
        produtoDAO.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }

}
