package Queries;

import java.util.List;

import data.Tank;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class GetUserTanks {

    @PersistenceContext
    EntityManager em;

    public List<Tank> getUsersTanks(String username) {
        // Implement your logic to fetch the list of tanks for the user from the backend service
        // For example, you can use HttpClient to make a request to the backend API
        // with the provided token and return the response as a HashMap

        return em.createNamedQuery("Tank.findAll", Tank.class)
                .setParameter("username", username)
                .getResultList();
    }

    public Tank getTankByName(String username, String name) {
        // Implement your logic to fetch a specific tank by name for the user from the backend service
        // For example, you can use HttpClient to make a request to the backend API
        // with the provided token and return the response as a Tank object

        return em.createQuery("SELECT t FROM tanks t WHERE t.name = :name AND t.username1 = :username", Tank.class)
                .setParameter("name", name)
                .setParameter("username", username)
                .getSingleResult();
    }



}
