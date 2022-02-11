package fiftyone.geolocation.web.examples.mvc;

import org.apache.catalina.LifecycleException;

import static fiftyone.geolocation.web.examples.shared.EmbedTomcat.runWebApp;

public class ExampleLauncher {
    public static void main(String[] args) throws LifecycleException {
        runWebApp(
                "geo-location.web.examples/geo-location.web.examples.mvc/src/main/webapp",
                "geo-location.web.examples/geo-location.web.examples.mvc/target",
                8080);
    }
}
