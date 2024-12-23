package com.chaney.infra.graalpy.endpoint;

import org.graalvm.polyglot.Source;
import org.graalvm.python.embedding.utils.GraalPyResources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class DemoController {

    @GetMapping("/eval")
    public void eval(@RequestParam("path") String path) {
        try (var context = GraalPyResources.createContext()) {
            byte[] bytes = Files.readAllBytes(Path.of(path));
            String sourceString = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("source code is:\n" + sourceString);
            Source source = Source.create("python", sourceString);
            context.eval(source);
//            return evaled;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        return Value.asValue("Hello World");
    }
}
