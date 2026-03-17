package br.com.igreja.biblerank.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String fotoUrl = "https://www.promoview.com.br/uploads/images/unnamed%2819%29.png";

    @Column (unique = true)
    private String email;
    private String senha;
    private int totalCapitulosLidos = 0;

}
