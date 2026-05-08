package networking.sessionsockets;



import data.Tank;
import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.OnClose;
import jakarta.websocket.Session;

public abstract class FrontendConnectionEndpoint extends Endpoint{

   
    private Tank tank;
    private Session session;
    private Session tankSession;
    

   @Override
   public void onOpen(final Session session, EndpointConfig config) {
    this.session = session;
    this.tank = (Tank) config.getUserProperties().get("tank");
    
    SessionSocket sessionExtender = new FrontendSessionExtender(session, this.getClass(), tank);
    

      sessionExtender.getSession(this.getClass()).addMessageHandler(new MessageHandler.Whole<String>() {
         @Override
         public void onMessage(String msg) {
            if(sessionExtender.getSession(this.getClass()) != null) {
                sessionExtender.sendMessageToCounterPart(msg, TankConnectionEndpoint.class);
            }
            else {
                sessionExtender.getSession(this.getClass()).getAsyncRemote().sendText("No tank session found yet for this tank.");  
            }
            }
         });
        
    
    sessionExtender.getSession(this.getClass()).getAsyncRemote().sendText(tank.getName() + " has connected to the server!");
    sessionExtender.addToSessionMap();

        

         
      }


    public void sendMessageToFrontend(String msg) {
        session.getAsyncRemote().sendText(msg);
    }

    
    public Session getSession() {
        return session;
    }

    @OnClose
    public void onClose(Session session) {
        session.getAsyncRemote().sendText(tank.getName() + " has disconnected from the frontend!");
    }
    

    public SessionSocket pingTank() {
        return SessionConnector.getCounterPartSession(this.getClass(), tank);
    }
    
   

}
