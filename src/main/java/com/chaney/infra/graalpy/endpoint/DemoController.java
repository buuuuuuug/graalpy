package com.chaney.infra.graalpy.endpoint;

import com.chaney.infra.graalpy.MonitClient;
import jakarta.annotation.Resource;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.python.embedding.utils.GraalPyResources;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
public class DemoController {

    private static Context context;

    private static final String PATH_PREFIX = "/home/chaneychan/dist/";
    private static final String SRC_PATH;

    private static void initContext() {
        context = GraalPyResources
//                .contextBuilder(Path.of("/Users/chaneychan/IdeaProjects/graalPy/fs"))
                .contextBuilder()
                .allowExperimentalOptions(true)
                .option("python.PythonPath", SRC_PATH)
                .option("python.InputFilePath", SRC_PATH)
                .allowAllAccess(true)
                .build();
    }

    static {
        SRC_PATH = PATH_PREFIX + System.getProperty("python.InputFilePath");
        initContext();
    }

    @Resource
    private MonitClient monitClient;

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
        Path libPath = Paths.get(SRC_PATH + "/lib.py");
        String oriContent = new String(Files.readAllBytes(libPath));
        String replaced = oriContent.replace("++", "hh");
        // flush modification
        Files.write(libPath, replaced.getBytes());
        initContext();

        // 重新执行
        context.eval(source);

//        context.close();

        String luaScript = """
                require('luajava')
                local javaClass = luajava.bindClass("com.chaney.infra.graalpy.endpoint.DemoController")
                javaClass:five()
                """;
        Globals globals = JsePlatform.standardGlobals();
//        globals.load("require 'luajava'").call();
        globals.load(luaScript).call();
    }

    @GetMapping("/queryStatus")
    public Map<String, String> queryStatus() throws IOException {
        return monitClient.queryStatus("graalpy");
    }

    @GetMapping("/restart")
    public Map<String, String> restart() throws IOException {
        return monitClient.restart("graalpy");
    }

    public static void five() {
        System.out.println("555");
    }
}
