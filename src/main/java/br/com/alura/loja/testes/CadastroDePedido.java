package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;
import br.com.alura.loja.vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CadastroDePedido {
    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
        Produto produto = produtoDAO.buscarPorId(1L);
        Cliente cliente =  clienteDAO.buscarPorId(1L);
        em.getTransaction().begin();
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        pedidoDAO.cadastrar(pedido);
        em.getTransaction().commit();
        BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println("Valor Total: "+totalVendido);

        List<RelatorioVendasVo> relatorio = pedidoDAO.relatorioDeVendas();

        for (RelatorioVendasVo item : relatorio) {
            System.out.println(item.getNomeProduto());
            System.out.println(item.getDataUltimaVenda());
            System.out.println(item.getQuantidadeVendida());
        }

    }

    private static void popularBancoDeDados() {
        Categoria categoria = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi","RedMi 9",new BigDecimal("800"), categoria);
        Cliente cliente = new Cliente("Rodrigo","123456789950");
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
        em.getTransaction().begin();
        clienteDAO.cadastrar(cliente);
        categoriaDAO.cadastrar(categoria);
        produtoDAO.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
