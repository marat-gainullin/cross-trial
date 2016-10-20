import org.apache.catalina.startup.Tomcat;

import java.net.URL;
import java.nio.file.Path;
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
        Path selfPath = Paths.get(codeLocation.toURI());
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(port.orElse("8080")));
        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp(contextPath, selfPath.toFile().getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
