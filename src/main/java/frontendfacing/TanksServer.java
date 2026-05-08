package frontendfacing;

import java.util.HashMap;

import Queries.DatabaseRequest;
import data.Tank;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import tankfacing.TankHub;

@Path("/frontend")
public class TanksServer {

@Inject
private DatabaseRequest databaseRequest;


@GET
@Path("/getmenu")
@Consumes("application/json")
@Produces("application/json")
public String getInitialReply() {
    // Implement your logic to fetch the menu data from the backend service
    // For example, you can use HttpClient to make a request to the backend API
    // and return the response as a string

    String reply = "Please Send me your username and password to login to the server";
    

    // Placeholder response for demonstration purposes
    return reply;
    }

@PUT
@Path("/login")
@Consumes("application/json")
@Produces("application/json")
public String checkToken(CredentialsBox credentials) {
    // Implement your logic to authenticate the user with the backend service
    // For example, you can use HttpClient to make a request to the backend API
    // with the provided token and return the response as a string


    String response = databaseRequest.login(credentials);

    if (response.equals("Login failed! Invalid username or password.")) {
        return response;
    }
    else {

    String reply = "Login successful! Welcome, " + credentials.getUsername() + "!";

    // Placeholder response for demonstration purposes
    return reply;
    }

}


@GET
@Path("/getdata")
@Consumes("application/json")
@Produces("application/json")
public HashMap<String, Tank> getData(CredentialsBox credentials) {


    boolean tokenValidity = databaseRequest.checkToken(credentials.getToken());

    if (tokenValidity == false) {
        // Handle invalid token scenario
        return null; // or throw an exception, or return an error response
    }
    else {

    HashMap<String, Tank> reply = databaseRequest.getListofTanks(credentials);
    
    return reply;
    }
}


@GET
@Path("/gettank")
@Consumes("application/json")
@Produces("application/json")
public String establishTankConnection(CredentialsBox credentials, String tankName) {

    boolean tokenValidity = databaseRequest.checkToken(credentials.getToken());

    if (tokenValidity == false) {
        // Handle invalid token scenario
        return null; // or throw an exception, or return an error response
        }
    else if (tankName == null || tankName.isEmpty()) {
        // Handle missing or empty tank name scenario
        return null; // or throw an exception, or return an error response
        }
    else {
    
        Tank tank = databaseRequest.getTankByName(credentials, tankName);

        if (tank.getTankRestConnectionState().equals("offline")) {
            // Handle tank connection failure scenario
            return null; // or throw an exception, or return an error response
        }
        else{
            
             String tankConnectionsURI = TankHub.sendConnectionMessageToTank("Ping", tank);

                if (tankConnectionsURI == null) {
                    // Handle tank connection failure scenario
                    return null; // or throw an exception, or return an error response
                    }
                    else{

                    return tankConnectionsURI;
                    }
            }
        }


    
    }

    


    
}






