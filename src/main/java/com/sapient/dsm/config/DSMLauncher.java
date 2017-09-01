package com.sapient.dsm.config;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class DSMLauncher extends Application<Configuration> {

    public static void main(String... args) throws Exception {
        new DSMLauncher().run(args);
    }

    public void run(Configuration configuration, Environment environment) throws Exception {
        System.out.println("Hello world, by Dropwizard!");
    }
}
