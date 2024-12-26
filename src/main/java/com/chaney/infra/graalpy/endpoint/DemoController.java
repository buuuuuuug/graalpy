package com.chaney.infra.graalpy.endpoint;

import java.io.File;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.io.IOAccess;
import org.graalvm.python.embedding.utils.GraalPyResources;
import org.graalvm.python.embedding.utils.VirtualFileSystem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class DemoController {

    private static final Context context = GraalPyResources
            .contextBuilder(VirtualFileSystem.newBuilder()
                    .allowHostIO(VirtualFileSystem.HostIO.READ_WRITE)
//                    .unixMountPoint("/home/chaney/IdeaProjects/graalpy/mnt")
                    .build())
            .allowAllAccess(true)
//            .allowCreateProcess(true)
//            .allowCreateThread(true)
//            .allowExperimentalOptions(true)
//            .allowIO(IOAccess.ALL)
//            .currentWorkingDirectory(Path.of("/home/chaney/IdeaProjects/graalpy/src/main/python"))
            .build();

    @GetMapping("/eval")
    public void eval(@RequestParam("path") String path) throws IOException {
//            System.loadLibrary()
        File file = new File(path);
        String language = Source.findLanguage(file);
        Source source = Source.newBuilder(language, file).build();

        context.eval(source);
//            return evaled;
//        return Value.asValue("Hello World");
    }
}
