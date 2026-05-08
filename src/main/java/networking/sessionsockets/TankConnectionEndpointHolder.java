package networking.sessionsockets;

import jakarta.websocket.server.ServerContainer;
import jakarta.websocket.server.ServerEndpointConfig;
import networking.frontendstuff.FrontEndAIControls;
import networking.frontendstuff.FrontEndCamera;
import networking.frontendstuff.FrontEndFrontBottomServo;
import networking.frontendstuff.FrontEndFrontTopServo;
import networking.frontendstuff.FrontEndLeftServo;
import networking.frontendstuff.FrontEndRightServo;
import networking.tankcontrolstuff.TankAIControls;
import networking.tankcontrolstuff.TankCamera;
import networking.tankcontrolstuff.TankFrontBottomServo;
import networking.tankcontrolstuff.TankFrontTopServo;
import networking.tankcontrolstuff.TankLeftServo;
import networking.tankcontrolstuff.TankRightServo;

public class TankConnectionEndpointHolder {


        private static String URIforFrontend = "/frontend";
        private static String URIforTank = "/tank";

    public boolean deployEndpointsforFrontend(ServerContainer serverContainer){
         try {

            

            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(FrontEndAIControls.class, URIforFrontend + "/aicontrols").build());
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(FrontEndCamera.class, URIforFrontend + "/camera").build());
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(FrontEndFrontBottomServo.class, URIforFrontend + "/frontbottomservo").build());
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(FrontEndFrontTopServo.class, URIforFrontend + "/fronttopservo").build());
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(FrontEndLeftServo.class, URIforFrontend + "/leftservo").build());
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(FrontEndRightServo.class, URIforFrontend + "/rightservo").build());



            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    


    public boolean deployEndpointsforTank(ServerContainer serverContainer){

        try {

            

            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(TankAIControls.class, URIforTank + "/aicontrols").build());
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(TankCamera.class, URIforTank + "/camera").build());
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(TankFrontBottomServo.class, URIforTank + "/frontbottomservo").build());
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(TankFrontTopServo.class, URIforTank + "/fronttopservo").build());
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(TankLeftServo.class, URIforTank + "/leftservo").build());
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(TankRightServo.class, URIforTank + "/rightservo").build());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
     

}




    public String getURI() {
        return URIforTank;
    }




    public String getURIforFrontend() {
        return URIforFrontend;
    }

    


            

    
    
    

    

}
