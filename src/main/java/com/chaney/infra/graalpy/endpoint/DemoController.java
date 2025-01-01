package com.chaney.infra.graalpy.endpoint;

import java.io.File;
import java.nio.file.Paths;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.python.embedding.utils.GraalPyResources;
import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class DemoController {

    private static Context context;

    private static void initContext() {
        context = GraalPyResources
//                .contextBuilder(Path.of("/Users/chaneychan/IdeaProjects/graalPy/fs"))
                .contextBuilder()
                .allowExperimentalOptions(true)
                .option("python.PythonPath", "/Users/chaneychan/IdeaProjects/graalPy/fs/src")
                .option("python.InputFilePath", "/Users/chaneychan/IdeaProjects/graalPy/fs/src")
                .allowAllAccess(true)
                //            .allowCreateProcess(true)
                //            .allowCreateThread(true)
                //            .allowExperimentalOptions(true)
                //            .allowIO(IOAccess.ALL)
                //            .currentWorkingDirectory(Path.of("/home/chaney/IdeaProjects/graalpy/src/main/python"))
                .build();
    }

    static {
        initContext();
    }
///home/chaney/.sdkman/candidates/java/23-graal/bin/java -agentlib:native-image-agent=config-output-dir=/home/chaney/IdeaProjects/graalpy/configs   -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /home/chaney/IdeaProjects/graalpy/build/classes/java/main:/home/chaney/IdeaProjects/graalpy/build/resources/main:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-web/3.4.1/ff7227fc62338e0f6eba3f9f94c12eb952d4da95/spring-boot-starter-web-3.4.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter/3.4.1/2c97b6fdc451ea69cd04dcfa54980439b7c7cb34/spring-boot-starter-3.4.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.python/python-launcher/24.1.1/48935e5d255e087d3dd4d15bc17cb3f6738bb70b/python-launcher-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.python/python-embedding/24.1.1/8f9945d0b97779aed38fb68540ef93b16818b137/python-embedding-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.polyglot/polyglot/24.1.1/90838233a34d0768f33cbdba810096d7feb824d2/polyglot-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.luaj/luaj-jse/3.0.1/99245b2df284805e1cb835e9be47c243f9717511/luaj-jse-3.0.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-json/3.4.1/c1d084f65d8d9f2de9daccab47c4f452fb0464de/spring-boot-starter-json-3.4.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-tomcat/3.4.1/ac4bb51582c57cfb0d2beb102a76fe1a4d8b8b21/spring-boot-starter-tomcat-3.4.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework/spring-webmvc/6.2.1/44bdf7e5641d44044ac52d7bb5c1fc46004e7754/spring-webmvc-6.2.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework/spring-web/6.2.1/877acb94c5b3a0c92e652b6bebdfdc7c60922ac8/spring-web-6.2.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-autoconfigure/3.4.1/f17b54cc5816ec8f06d0aca9df11c330ead97f2a/spring-boot-autoconfigure-3.4.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot/3.4.1/5fb9890a5eb7c4e86c8f5c0f6960b79240daf3d5/spring-boot-3.4.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-logging/3.4.1/5cd01e208b15113c7f88b3ea40e843ea9989f38a/spring-boot-starter-logging-3.4.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/jakarta.annotation/jakarta.annotation-api/2.1.1/48b9bda22b091b1f48b13af03fe36db3be6e1ae3/jakarta.annotation-api-2.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework/spring-core/6.2.1/f42e6b51d9c0c2fcf95df9e5848470d173adc9af/spring-core-6.2.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.yaml/snakeyaml/2.3/936b36210e27320f920536f695cf1af210c44586/snakeyaml-2.3.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.sdk/launcher-common/24.1.1/9776756460575bf2b0725664dc3eee6315e951f3/launcher-common-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.sdk/maven-downloader/24.1.1/256ac666a57260af5c0f28657b5b0db06c67f0b1/maven-downloader-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.sdk/nativeimage/24.1.1/a3a3e22b928e1c3bf527548d3127c6680e122b32/nativeimage-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.shadowed/jline/24.1.1/12d99876452b98492c17dd2d84c2f1fdea4384d7/jline-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.sdk/collections/24.1.1/16b6de3fe00198630e375e6a8dc26a39a6df288d/collections-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.datatype/jackson-datatype-jsr310/2.18.2/7b6ff96adf421f4c6edbd694e797dd8fe434510a/jackson-datatype-jsr310-2.18.2.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.module/jackson-module-parameter-names/2.18.2/72960cb3277347a748911d100c3302d60e8a616a/jackson-module-parameter-names-2.18.2.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.datatype/jackson-datatype-jdk8/2.18.2/9ed6d538ebcc66864e114a7040953dce6ab6ea53/jackson-datatype-jdk8-2.18.2.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-databind/2.18.2/deef8697b92141fb6caf7aa86966cff4eec9b04f/jackson-databind-2.18.2.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.apache.tomcat.embed/tomcat-embed-websocket/10.1.34/eef6d430f34b6e393b8d9e40f10db9043732b4e5/tomcat-embed-websocket-10.1.34.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.apache.tomcat.embed/tomcat-embed-core/10.1.34/f610f84be607fbc82e393cc220f0ad45f92afc91/tomcat-embed-core-10.1.34.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.apache.tomcat.embed/tomcat-embed-el/10.1.34/d2b2daca3bc999c62e58ae36b45ba0582530fb25/tomcat-embed-el-10.1.34.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework/spring-context/6.2.1/f56c7431b03860bfdb016e68f484c5c35531ef2e/spring-context-6.2.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework/spring-aop/6.2.1/a9384de38fc00751084446ba014a0c4962240244/spring-aop-6.2.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework/spring-beans/6.2.1/ab57ec03ba6900075bf28e3cd70ccce173205b8d/spring-beans-6.2.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework/spring-expression/6.2.1/91fcf6b9501705c31c8337e2713fe823bb512b24/spring-expression-6.2.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/io.micrometer/micrometer-observation/1.14.2/a9cad29cc04c0f7e30e3e58b454d4cd47ccc54bd/micrometer-observation-1.14.2.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.5.12/3790d1a62e868f7915776dfb392bd9a29ce8d954/logback-classic-1.5.12.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-to-slf4j/2.24.3/da1143e2a2531ee1c2d90baa98eb50a28a39d5a7/log4j-to-slf4j-2.24.3.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.slf4j/jul-to-slf4j/2.0.16/6d57da3e961daac65bcca0dd3def6cd11e48a24a/jul-to-slf4j-2.0.16.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.springframework/spring-jcl/6.2.1/a5d662d64470aff0ae51d210147bb6ede31a8ea3/spring-jcl-6.2.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.sdk/word/24.1.1/3c30d46d03d86d5d1069bfd34a57390f8cf8a30c/word-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-annotations/2.18.2/985d77751ebc7fce5db115a986bc9aa82f973f4a/jackson-annotations-2.18.2.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-core/2.18.2/fb64ccac5c27dca8819418eb4e443a9f496d9ee7/jackson-core-2.18.2.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/io.micrometer/micrometer-commons/1.14.2/69c454dbec59c7842cf59a534b7ec03618d75b91/micrometer-commons-1.14.2.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-core/1.5.12/65b1fa25fe8d8e4bdc140e79eb67ac6741f775e2/logback-core-1.5.12.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/2.0.16/172931663a09a1fa515567af5fbef00897d3c04/slf4j-api-2.0.16.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-api/2.24.3/b02c125db8b6d295adf72ae6e71af5d83bce2370/log4j-api-2.24.3.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.python/python/24.1.1/9cf65ec4c1f8e1021da761176f26c59eba558de6/python-24.1.1.pom:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm/24.1.1/a5abfaba56520cb163e0f13dbb38431769941c56/llvm-24.1.1.pom:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.python/python-community/24.1.1/1ba2e86d989b6f0765b5c93aa3464dbb999a322/python-community-24.1.1.pom:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.truffle/truffle-enterprise/24.1.1/aebb774ec3a25f53d9dd9f24b063fb5c3b44bff4/truffle-enterprise-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-native/24.1.1/2c0fbcc6e3101c9ebcf766f8bde77f282444adf8/llvm-native-24.1.1.pom:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-managed/24.1.1/12dfbc1ab9907340ae4a79d8946cd60949ed6790/llvm-managed-24.1.1.pom:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.python/python-language/24.1.1/9a8af5d8387ba5156aa5d9b47949478d0a7fa949/python-language-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.python/python-resources/24.1.1/a4ca9c0b3361553d39aaeecbd638dd8896ed5b8/python-resources-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.truffle/truffle-runtime/24.1.1/405a3db994bb09149103226ba88eaa69865ccdb7/truffle-runtime-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.sdk/nativebridge/24.1.1/8ee4441cb19749542927301826578cf92d58610f/nativebridge-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.sdk/jniutils/24.1.1/4b938ddc284a5da3fa39bc408e8df98abf2d7e36/jniutils-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.truffle/truffle-compiler/24.1.1/cf1980f8d824b7370a125efa51b1c5dde4b15aab/truffle-compiler-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-native-community/24.1.1/cb4a29c64f87a56ef343652d1b8832fb4285e13e/llvm-native-community-24.1.1.pom:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-language-native-enterprise/24.1.1/d1ce32c20853d3a55beebf76faf3fe3041e503c1/llvm-language-native-enterprise-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-language-enterprise/24.1.1/bf2dcedc60966919d73e7d2dad69beac659dbe28/llvm-language-enterprise-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-language-nfi/24.1.1/4d676f8a41ae3a56835961fb872d4cf4c5c720cc/llvm-language-nfi-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-language-managed/24.1.1/e59bca0bfdcba50bfb52c2a9bdf68f81745f7af/llvm-language-managed-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-language-managed-resources/24.1.1/6b8e5cae261d49dd37cfa73360c8d78cc8097098/llvm-language-managed-resources-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.tools/profiler-tool/24.1.1/5b1bd7fe9ed1610cd2bece8c9c6a33900365d1f1/profiler-tool-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.regex/regex/24.1.1/54481ac1f39247252ab1910650425ba7ae44754a/regex-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.truffle/truffle-nfi-libffi/24.1.1/cf27ea3a964faa49043b2201ffcbe8afbfd74d8f/truffle-nfi-libffi-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.truffle/truffle-nfi/24.1.1/3a0933174665ce62be94652831af998a88f3ca5/truffle-nfi-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-api/24.1.1/780c182c59ad36d57932a4e3f2b2f2cfefbcd510/llvm-api-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.truffle/truffle-api/24.1.1/ab928bda7b6e0b32c07d1881109c80c8b0906e84/truffle-api-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.shadowed/icu4j/24.1.1/9114a2956fed6e02209f7764b31a31bfa07dcbd/icu4j-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.shadowed/xz/24.1.1/31b49394143dd019c497508e509c3718106496e7/xz-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcpkix-jdk18on/1.78.1/17b3541f736df97465f87d9f5b5dfa4991b37bb3/bcpkix-jdk18on-1.78.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcutil-jdk18on/1.78.1/5353ca39fe2f148dab9ca1d637a43d0750456254/bcutil-jdk18on-1.78.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.bouncycastle/bcprov-jdk18on/1.78.1/39e9e45359e20998eb79c1828751f94a818d25f8/bcprov-jdk18on-1.78.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-language-native/24.1.1/69795c2626736f7c05277daebd9121d620e3f96b/llvm-language-native-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-language/24.1.1/92e9e65aff646259b0ad2ecb1cc4628d6622dc6f/llvm-language-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.llvm/llvm-language-native-resources/24.1.1/6d67373a5cd874b8e1fbe050e10d4e1d9decb13c/llvm-language-native-resources-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.shadowed/json/24.1.1/eaff4977365232f63a50ebc27c0ca7b4d14d0c80/json-24.1.1.jar:/home/chaney/.gradle/caches/modules-2/files-2.1/org.graalvm.shadowed/antlr4/24.1.1/656acd71d8956d20640f02a2cc63a0b58c93d918/antlr4-24.1.1.jar:/home/chaney/IdeaProjects/graalpy/build/classes/java/aot:/home/chaney/IdeaProjects/graalpy/build/resources/aot:/home/chaney/.local/share/JetBrains/Toolbox/apps/intellij-idea-ultimate/lib/idea_rt.jar -jar build/libs/graalpy-0.0.1-SNAPSHOT.jar

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
        Path libPath = Paths.get("/Users/chaneychan/IdeaProjects/graalPy/fs/src/lib.py");
        String oriContent = new String(Files.readAllBytes(libPath));
        String replaced = oriContent.replace("++", "hh");
        // flush modification
        Files.write(libPath, replaced.getBytes());
        initContext();

        // 重新执行
        context.eval(source);

        context.close();

        String luaScript = """
                require('luajava')
                local javaClass = luajava.bindClass("com.chaney.infra.graalpy.endpoint.DemoController")
                javaClass:five()
                """;
        Globals globals = JsePlatform.standardGlobals();
//        globals.load("require 'luajava'").call();
        globals.load(luaScript).call();
    }

    public static void five() {
        System.out.println("555");
    }
}
