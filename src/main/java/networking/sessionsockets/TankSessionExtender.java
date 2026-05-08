package networking.sessionsockets;

import data.Tank;
import jakarta.websocket.Session;

public class TankSessionExtender extends SessionSocket {

    public TankSessionExtender(Session session, Class endpoint, Tank tank) {
        super(session, endpoint, tank);
        
    }
}
