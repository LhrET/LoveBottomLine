package net.web.lblpack.push;

import net.web.lblpack.push.provider.GsonProvider;
import net.web.lblpack.push.service.AccountService;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

public class Application extends ResourceConfig {
    public Application(){
        //注册包名
        packages(AccountService.class.getPackage().getName());
        register(JacksonJsonProvider.class);
        register(GsonProvider.class);
        register(Logger.class);

    }
}
