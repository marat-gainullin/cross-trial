import org.apache.catalina.startup.Tomcat;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Created by marat.gaynullin on 20/10/16.
 */
public class Launcher {

    public static final Optional<String> port = Optional.ofNullable(System.getenv("PORT"));

    public static void main(String[] args) throws Exception {
        String contextPath = "/cross";
        URL codeLocation = Launcher.class.getProtectionDomain().getCodeSource().getLocation();
        String appBase = Paths.get(codeLocation.toURI()).toFile().getAbsolutePath();
        System.out.println("appBase: " + appBase);
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(port.orElse("8080")));
        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp(contextPath, appBase);
        tomcat.start();
        tomcat.getServer().await();
    }
}
