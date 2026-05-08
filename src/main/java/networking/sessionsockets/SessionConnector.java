package networking.sessionsockets;

import java.util.concurrent.ConcurrentHashMap;

import data.Tank;


public class SessionConnector {


    private static ConcurrentHashMap<String, SessionSocket> sessionStates = new ConcurrentHashMap<>();




    private static ConcurrentHashMap<String, SessionSocket> frontSessionExtenders = new ConcurrentHashMap<>();


    private static ConcurrentHashMap<String, SessionSocket> tankSessionExtenders = new ConcurrentHashMap<>();


    public static void addSessionExtender(String tankName, SessionSocket sessionExtender, Class sessionExtenderClass) {
        
        if (sessionExtenderClass.equals(FrontendSessionExtender.class)) {
            frontSessionExtenders.put(tankName + "_" + sessionExtenderClass.getSimpleName(), sessionExtender);
        } else if (sessionExtenderClass.equals(TankSessionExtender.class)) {
            tankSessionExtenders.put(tankName + "_" + sessionExtenderClass.getSimpleName(), sessionExtender);
        }
    }


    public static SessionSocket getCounterPartSession(Class sessionExtenderClass, Tank tank){

        if (sessionExtenderClass.equals(FrontendSessionExtender.class)) {
            return tankSessionExtenders.get(tank.getName() + "_" + TankSessionExtender.class.getSimpleName());
        } else if (sessionExtenderClass.equals(TankSessionExtender.class)) {
            return frontSessionExtenders.get(tank.getName() + "_" + FrontendSessionExtender.class.getSimpleName());
        } else {
            return null;

            
        }


    }   
    


    public static void addSessionState(String tankName, Class classType, SessionSocket sessionSocket) {
       
        String key = tankName + "_" + classType.getSimpleName();
        sessionStates.put(key, sessionSocket);

        
    }

    
   



   
}
