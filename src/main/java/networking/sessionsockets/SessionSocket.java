package networking.sessionsockets;

import data.Tank;
import jakarta.websocket.Session;

public abstract class SessionSocket {

    protected Session tankSession;
    protected Session frontendSession;
    protected Tank tank;
    protected Class classType;

    public void addTankSession(Session tankSession) {
        this.tankSession = tankSession;
    }
    public void addFrontendSession(Session frontendSession) {
        this.frontendSession = frontendSession;
    }
    
    public SessionSocket(Session session, Class endpoint, Tank tank) {
        this.frontendSession = session;
        this.classType = endpoint;
        this.tank = tank;
    }


    public Session getSession(Class endpoint) {
        if(FrontendConnectionEndpoint.class.isAssignableFrom(endpoint)) {
            return frontendSession;
        } else if (TankConnectionEndpoint.class.isAssignableFrom(endpoint)) {
            return tankSession;
        } else {
            return null;
        }
    }   

    public Tank getTank() {
        return tank;
    }


    public void addToSessionMap() {
       
        SessionConnector.addSessionExtender(tank.getName(), this, classType);

        SessionConnector.addSessionState(tank.getName(), classType, this);
        
    }

    public void sendMessageToCounterPart(String msg, Class endpoint) {
        if(FrontendConnectionEndpoint.class.isAssignableFrom(endpoint)) {
            if (tankSession != null) {
                tankSession.getAsyncRemote().sendText(msg);
            }
        } else if (TankConnectionEndpoint.class.isAssignableFrom(endpoint)) {
            if (frontendSession != null) {
                frontendSession.getAsyncRemote().sendText(msg);
            }
        }
        
    }


}
