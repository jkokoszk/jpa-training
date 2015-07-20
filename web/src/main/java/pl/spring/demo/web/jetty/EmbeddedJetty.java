package pl.spring.demo.web.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.LifeCycle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.EnumSet;

public class EmbeddedJetty {

    private static final int DEFAULT_PORT = 9721;
    private static final String CONTEXT_PATH = "/workshop/";
    private static final String CONFIG_LOCATION = "classpath*:spring/*.xml";
    private static final String MAPPING_URL = "/*";
    private static final String DEFAULT_PROFILE = "dev";

    public static void main(String[] args) throws Exception {
        new EmbeddedJetty().startJetty(getPortFromArgs(args));
    }

    private static int getPortFromArgs(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.valueOf(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }
        return DEFAULT_PORT;
    }

    protected LifeCycle startJetty(int port) throws Exception {
        Server server = new Server(port);
        server.setHandler(getServletContextHandler(getContext()));
        addListeners(server);

        server.start();
        server.join();
        return server;
    }

    protected List<LifeCycle.Listener> createListeners() {
        return Collections.emptyList();
    }

    private void addListeners(Server server) {
        createListeners().forEach(server::addLifeCycleListener);
    }

    private ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler();
        contextHandler.addFilter(getEncodingFilterHolder(), "/*", EnumSet.allOf(DispatcherType.class));
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(context));
        contextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
        return contextHandler;
    }

    private WebApplicationContext getContext() {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
        return context;
    }



    private FilterHolder getEncodingFilterHolder() {
        FilterHolder filterHolder = new FilterHolder();

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        filterHolder.setFilter(characterEncodingFilter);
        return filterHolder;
    }

}
