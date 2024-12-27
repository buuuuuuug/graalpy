package com.chaney.infra.graalpy.endpoint;

import com.oracle.graal.python.shell.GraalPythonMain;
import java.io.File;
import java.nio.file.Paths;
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

    private static Context context;
    private static final VirtualFileSystem virtualFileSystem;

    private static void initContext() {
        context = GraalPyResources
                .contextBuilder(Path.of("/home/chaney/IdeaProjects/graalpy/fs"))
                .allowAllAccess(true)
                //            .allowCreateProcess(true)
                //            .allowCreateThread(true)
                //            .allowExperimentalOptions(true)
                //            .allowIO(IOAccess.ALL)
                //            .currentWorkingDirectory(Path.of("/home/chaney/IdeaProjects/graalpy/src/main/python"))
                .build();
    }

    static {
        virtualFileSystem = VirtualFileSystem.newBuilder()
                .allowHostIO(VirtualFileSystem.HostIO.READ_WRITE)
//                    .unixMountPoint("/home/chaney/IdeaProjects/graalpy/mnt")
                .build();
        initContext();
    }

    @GetMapping("/eval")
    public void eval(@RequestParam("path") String path) throws IOException {
//            System.loadLibrary()
        File file = new File(path);
        String language = Source.findLanguage(file);
        Source source = Source.newBuilder(language, file).build();

//            return evaled;
//        return Value.asValue("Hello World");
//        virtualFileSystem.delete(Path.of("/graalpy_vfs/src/lib.py"));
        context.eval(source);

        // 修改文件内容
        Path libPath = Paths.get("/home/chaney/IdeaProjects/graalpy/fs/src/lib.py");
        String oriContent = new String(Files.readAllBytes(libPath));
        String replaced = oriContent.replace("++", "hh");
        // flush modification
        Path libPath1 = Files.write(libPath, replaced.getBytes());
        initContext();

        // 重新执行
        context.eval(source);
    }
}
