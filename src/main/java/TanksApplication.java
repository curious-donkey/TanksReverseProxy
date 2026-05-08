import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import tankfacing.TankHub;

@ApplicationPath("/api")
public class TanksApplication extends Application implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize any resources or configurations needed for the application
        TankHub.deployEndpoints();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Clean up any resources or configurations when the application is destroyed
    }

    
    
}
