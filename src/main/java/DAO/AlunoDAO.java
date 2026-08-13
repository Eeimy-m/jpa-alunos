package DAO;

import Model.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class AlunoDAO {
    private EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Aluno aluno) {
        this.em.persist(aluno);
    }

    public void excluir(Aluno aluno) {
        this.em.remove(aluno);
    }

    public void alterar(Aluno aluno) {
        this.em.merge(aluno);
    }

    public Aluno procurarPorNome(String nome) throws NoResultException {
        String jpql = "select a from Aluno a where a.nome = :nome";

        return em.createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    public List<Aluno> listarAlunos() {
        String jpql = "Select a from Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }
}
