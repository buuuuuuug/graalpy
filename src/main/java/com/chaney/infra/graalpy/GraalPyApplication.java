package com.chaney.infra.graalpy;

import org.graalvm.polyglot.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraalPyApplication {

    public static void main(String[] args) {
        try (var context = Context.create()) {
            System.out.println(context.eval("python", "'Hello Python!'").asString());
        }
        SpringApplication.run(GraalPyApplication.class, args);
    }

}
