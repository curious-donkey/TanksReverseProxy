package tankfacing;


import data.Tank;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import jakarta.servlet.ServletContext;
import jakarta.websocket.server.ServerContainer;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;
import networking.restsse.TankSignIns;
import networking.sessionsockets.TankConnectionEndpointHolder;



@Path("/tankhub")
@Singleton
public class TankHub {

        

        @Context
        private Sse sse;

        @Context
        private ServletContext servletContext;

     

        private static TankConnectionEndpointHolder tankConnectionEndpoint = new TankConnectionEndpointHolder();

        @PostConstruct
        public void init() {
            ServerContainer serverContainer = (ServerContainer) servletContext.getAttribute("jakarta.websocket.server.ServerContainer");
            deployEndpoints(serverContainer);
        }


            @GET
            @Path("/connect")
            @Produces(MediaType.SERVER_SENT_EVENTS)
        public void connectToServer(@Context Sse sse, @Context SseEventSink sseEventSink, Tank tank) {
            // Placeholder for the actual implementation of connecting to a server
            // This method will utilize server-sent events to establish a connection with the server and handle communication with it
            
            //authentication logic still needs to be written for the tank side

            TankSignIns.addSignIn(tank, sseEventSink);

        }


        @DELETE
        @Path("/disconnect")
        public void disconnectFromServer(Tank tank) {
            // Placeholder for the actual implementation of disconnecting from a server
            // This method will utilize server-sent events to close the connection with the server and handle any necessary cleanup

            TankSignIns.closeSignIn(tank);
        }


        public static void deployEndpoints(ServerContainer serverContainer) {
    
                        
                        tankConnectionEndpoint.deployEndpointsforTank(serverContainer);
                        tankConnectionEndpoint.deployEndpointsforFrontend(serverContainer);
      
        }



        public static String sendConnectionMessageToTank(String message, Tank tank) {
            //this is for sending a server-sent event message to the tank
            //it is prompted by input from the frontend-facing side


            SseEventSink sseEventSink = TankSignIns.getSignIn(tank);
            Sse sse = TankSignIns.getSseInstance(tank);
           

            if (sseEventSink != null) {
                try {
                    try {
                        TankConnectionEndpointHolder tankConnectionEndpoints = TankHub.getTankConnectionEndpoint();

                        sseEventSink.send(sse.newEvent(tankConnectionEndpoints.getURI()));
                        sseEventSink.close();
                       return tankConnectionEndpoints.getURIforFrontend();

                    } catch (Exception e) {
                        e.printStackTrace();
                        sseEventSink.close();
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            else {
                return null;
            }

        }

        
        

        public static TankConnectionEndpointHolder getTankConnectionEndpoint() {
            return tankConnectionEndpoint;
        }

}
