package networking.restsse;

import java.util.concurrent.ConcurrentHashMap;

import data.Tank;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;



public class TankSignIns {

    @SuppressWarnings("FieldMayBeFinal")
    private static ConcurrentHashMap<String, SseEventSink> tanksSignedIn = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Sse> sseInstances = new ConcurrentHashMap<>();


    public static void addSignIn(Tank tank, SseEventSink sseEventSink) {
        // Placeholder for the actual implementation of adding a connection to the tracker
        // This method will add a connection to the tracker, allowing for easy management and retrieval of connections

        tanksSignedIn.put(tank.getName(), sseEventSink);
    }

    public static SseEventSink getSignIn(Tank tank) {
        // Placeholder for the actual implementation of retrieving a connection from the tracker
        // This method will retrieve a connection from the tracker based on the tank, allowing for easy access to the connection

        return tanksSignedIn.get(tank.getName());
    }

    @SuppressWarnings("UseSpecificCatch")
    public static void closeSignIn(Tank tank) {
        // Placeholder for the actual implementation of closing a connection in the tracker
        // This method will close a connection in the tracker based on the tank, allowing for proper cleanup and resource management

        SseEventSink sseEventSink = tanksSignedIn.get(tank.getName());
        if (sseEventSink != null) {
            try {
                sseEventSink.close();
                sseEventSink.close();
                tanksSignedIn.remove(tank.getName());
                sseInstances.remove(tank.getName());
            } catch (Exception e) {
            }
        }
        }


   
    public static void addSseInstance(Tank tank, Sse sse) {
        sseInstances.put(tank.getName(), sse);
    }

    public static Sse getSseInstance(Tank tank) {
        return sseInstances.get(tank.getName());
    }
   




}