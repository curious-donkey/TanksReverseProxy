package networking.sessionsockets;

import data.Tank;
import jakarta.websocket.Session;

public class FrontendSessionExtender extends SessionSocket {


    
    public FrontendSessionExtender(Session session, Class endpoint, Tank tank) {
        super(session, endpoint, tank);
        
    }




}
