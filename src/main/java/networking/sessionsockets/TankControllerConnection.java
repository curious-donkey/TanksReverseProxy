package networking.sessionsockets;

import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.Session;

public class TankControllerConnection extends Endpoint {

   

    //for establishing and holding a connection to the python client on the tank from the frontend
    


    




    @Override
    public void onOpen(final Session arg0, EndpointConfig arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onOpen'");
    }

    
    public void onMessage(String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onMessage'");
    }
    






}
