package br.com.igreja.biblerank.model;

import br.com.igreja.biblerank.model.Livro;
import br.com.igreja.biblerank.model.Usuario;
import jakarta.persistence.*;

@Entity
public class Leitura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Livro livro;

    private int capitulosLidos; // Quantos ele já leu desse livro específico
    private boolean concluido; // Se ele já terminou o livro todo
}