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
    private String testamento; // Faltava esse aqui!

    // Getters e Setters (Necessários para o Spring trabalhar)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getTotalCapitulos() { return totalCapitulos; }
    public void setTotalCapitulos(int totalCapitulos) { this.totalCapitulos = totalCapitulos; }

    public String getTestamento() { return testamento; }
    public void setTestamento(String testamento) { this.testamento = testamento; }
}