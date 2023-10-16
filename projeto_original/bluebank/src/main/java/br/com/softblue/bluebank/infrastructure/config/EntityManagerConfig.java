package br.com.softblue.bluebank.infrastructure.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerConfig {
    
	@PersistenceUnit(unitName = "default")
    private EntityManagerFactory entityManagerFactory;
    
	@Produces
    @Default
    @RequestScoped
    public EntityManager create() {
        return entityManagerFactory.createEntityManager();
    }
	
    public void dispose(@Disposes @Default EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
