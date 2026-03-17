package br.com.igreja.biblerank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int totalCapitulos;

    public String getNome() {
        return nome;
    }

    public int getTotalCapitulos() {
        return totalCapitulos;
    }

}