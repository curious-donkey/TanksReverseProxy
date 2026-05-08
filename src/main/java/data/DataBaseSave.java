package data;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;

public abstract class DataBaseSave {

    @PersistenceContext
    protected EntityManager entityManager;

    @Resource
    protected UserTransaction userTransaction;

    public String save() {
        // Implement database logic to save the login attempt
        try {
            this.userTransaction.begin();
            this.entityManager.persist(this);
            this.userTransaction.commit();
            return "Saved successfully";
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.userTransaction.rollback();
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }
            return "Failed to save";
        }
    }
}










