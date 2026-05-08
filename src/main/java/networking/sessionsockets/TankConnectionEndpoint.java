package networking.sessionsockets;

import data.Tank;
import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.Session;

public abstract class TankConnectionEndpoint extends Endpoint {

    FrontendConnectionEndpoint frontendConnectionEndpoint; 
    Tank tank;
    Session session;
    Session frontendSession;
   
   @Override
   public void onOpen(final Session session, EndpointConfig config) {

    this.tank = (Tank) config.getUserProperties().get("tank");
    this.session = session;
    
    SessionSocket sessionExtender = new TankSessionExtender(session, this.getClass(), tank);

      sessionExtender.getSession(this.getClass()).addMessageHandler(new MessageHandler.Whole<String>() {
         @Override
         public void onMessage(String msg) {
            if(sessionExtender.getSession(this.getClass()) != null) {
                sessionExtender.sendMessageToCounterPart(msg, FrontendConnectionEndpoint.class);
            }else {
                sessionExtender.getSession(this.getClass()).getAsyncRemote().sendText("No frontend session found yet for this tank.");
            }
           
         }
      });

      
      sessionExtender.getSession(this.getClass()).getAsyncRemote().sendText(tank.getName() + " has connected to the server!");
      sessionExtender.addToSessionMap();
      

        
        //where endpoint adds itself to a hashmap so it can be referenced externally
      
   }


    @Override
    public void onClose(Session session, jakarta.websocket.CloseReason closeReason) {
        session.getAsyncRemote().sendText(tank.getName() + " has disconnected from the server!");
    }

    @Override
    public void onError(Session session, Throwable thr) {
        session.getAsyncRemote().sendText(tank.getName() + " has encountered an error: " + thr.getMessage());
    }

    public void sendMessageToTank(String msg) {
        session.getAsyncRemote().sendText(msg);
    }

    
   
    public void addFrontendSession(Session frontendSession) {
        this.frontendSession = frontendSession;
    }

    public SessionSocket pingFrontend() {
        return SessionConnector.getCounterPartSession(this.getClass(), tank);
    }

    

}
