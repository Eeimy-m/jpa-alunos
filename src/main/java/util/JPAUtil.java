package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("alunos");

    public final EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
