package com.sapient.dsm.config;

import com.sapient.dsm.config.config.SpringConfig;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DSMLauncher extends Application<Configuration> {

    public static void main(String... args) throws Exception {
        new DSMLauncher().run(args);
    }

    public void run(Configuration configuration, Environment environment) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBeanFactory().registerSingleton("appConfiguration", configuration);
        context.register(SpringConfig.class);
        context.registerShutdownHook();
        context.refresh();
    }
}
