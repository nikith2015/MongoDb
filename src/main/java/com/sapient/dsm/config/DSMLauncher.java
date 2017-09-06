package com.sapient.dsm.config;

import com.codahale.metrics.health.HealthCheck;
import com.sapient.dsm.DSMSpringConfiguration;
import com.sapient.dsm.SpringContextLoaderListener;
import com.sapient.dsm.exception.DSMExceptionMapper;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

import javax.ws.rs.Path;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DSMLauncher extends Application<Configuration> {

    public static void main(String... args) throws Exception {
        new DSMLauncher().run(args);
    }

    public void run(Configuration configuration, Environment environment) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBeanFactory().registerSingleton("appConfiguration", configuration);
        context.register(DSMSpringConfiguration.class);
        context.registerShutdownHook();
        context.refresh();

        Map<String, HealthCheck> healthChecks = context.getBeansOfType(HealthCheck.class);
        for (Map.Entry<String, HealthCheck> entry : healthChecks.entrySet()) {
            environment.healthChecks().register("template", entry.getValue());
        }

        Map<String, Object> resources = context.getBeansWithAnnotation(Path.class);
        for (Map.Entry<String, Object> entry : resources.entrySet()) {
            environment.jersey().register(entry.getValue());
        }

        //environment.servlets().addServletListeners(new SpringContextLoaderListener(ctx));
        environment.jersey().register(new DSMExceptionMapper());
    }
}
