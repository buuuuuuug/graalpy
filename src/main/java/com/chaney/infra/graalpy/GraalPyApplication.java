package com.chaney.infra.graalpy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
//@ImportRuntimeHints(DatabaseRuntimeHintsRegistrar.class)
public class GraalPyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraalPyApplication.class, args);
    }

}
