package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProduto {

    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        Produto p = produtoDAO.buscarPorId(1L);
        System.out.println(p.getPreco());

        List<Produto> todos = produtoDAO.buscarTodos();
        todos.forEach(p2 -> System.out.println(p2.getNome()));

    }

    private static void cadastrarProduto() {
        Categoria categoria = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi","RedMi 9",new BigDecimal("800"), categoria);
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        em.getTransaction().begin();
        categoriaDAO.cadastrar(categoria);
        produtoDAO.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }

}
