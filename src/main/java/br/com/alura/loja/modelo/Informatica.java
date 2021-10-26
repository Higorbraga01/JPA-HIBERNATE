package br.com.alura.loja.modelo;

import javax.persistence.Entity;

@Entity
public class Informatica extends  Produto{

    private String marca;
    private Integer modelo;

    public Informatica(String autor, Integer paginas) {
        this.marca = autor;
        this.modelo = paginas;
    }

    public Informatica() {
    }

    public String getAutor() {
        return marca;
    }

    public void setAutor(String autor) {
        this.marca = autor;
    }

    public Integer getPaginas() {
        return modelo;
    }

    public void setPaginas(Integer paginas) {
        this.modelo = paginas;
    }
}
