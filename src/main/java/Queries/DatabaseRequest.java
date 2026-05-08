package Queries;
import java.util.HashMap;
import java.util.List;

import data.Tank;
import frontendfacing.CredentialsBox;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class DatabaseRequest {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Inject
    private GetUserTanks getUserTanks;

    public String login(CredentialsBox credentials) {
        // Implement your logic to check the credentials against the backend service
        // For example, you can use HttpClient to make a request to the backend API
        // with the provided username and password and return true if authenticated, false otherwise

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://backend-service/api/authenticate");
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);

        String response = invocationBuilder.post(Entity.json(credentials), String.class);

        // Placeholder implementation for demonstration purposes
        return response;
    }

    
    public HashMap<String, Tank> getListofTanks(CredentialsBox credentials) {
        // Implement your logic to fetch the list of tanks from the backend service
        // For example, you can use HttpClient to make a request to the backend API
        // with the provided token and return the response as a HashMap

        HashMap<String, Tank> response = new HashMap<>();

        
        List <Tank> tanks = getUserTanks.getUsersTanks(credentials.getUsername());
        for (Tank tank : tanks) {
            response.put(tank.getName(), tank);
        }

        return response;
    }

    public Tank getTankByName(CredentialsBox credentials, String tankName) {

        Tank tank = getUserTanks.getTankByName(credentials.getUsername(), tankName);

        return tank;
    }


    public boolean checkToken(String token) {
        // Implement your logic to check the token against the backend service
        // For example, you can use HttpClient to make a request to the backend API
        // with the provided token and return true if valid, false otherwise

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://backend-service/api/checktoken");
        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);

        String response = invocationBuilder.post(Entity.json(token), String.class);

        if (response.equals("valid")) {
            return true;
        } else {
            return false;
        }
    }


}
