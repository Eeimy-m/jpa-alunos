package Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String ra;
    private String email;
    private BigDecimal nota1;
    private BigDecimal nota2;
    private BigDecimal nota3;

    public Aluno() {}

    public Aluno(String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRa() {
        return ra;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getNota1() {
        return nota1;
    }

    public BigDecimal getNota2() {
        return nota2;
    }

    public BigDecimal getNota3() {
        return nota3;
    }
}
