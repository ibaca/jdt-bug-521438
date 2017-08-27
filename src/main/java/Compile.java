import static java.lang.System.err;
import static java.lang.System.out;

import java.io.PrintWriter;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.jdt.core.compiler.CompilationProgress;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;

public class Compile {
    public static void main(String[] args) {
//        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
//        System.out.println(Instant.now() + " Compiling with java compiler…");
//        javaCompiler.run(null, null, null, "-soruce 1.8 -proce:none src/main/generics");
//        System.out.println(Instant.now() + " java compiler done!");

        System.out.println(Instant.now() + " Compiling with eclipse compiler…");
        Map<String, String> opts = new HashMap<>();
        opts.put(CompilerOptions.OPTION_Store_Annotations, "disabled");
        CompilationProgress progress = new CompilationProgress() {
            @Override public void begin(int remainingWork) { }
            @Override public void done() { }
            @Override public boolean isCanceled() { return false; }
            @Override public void setTaskName(String name) { }
            @Override public void worked(int workIncrement, int remainingWork) { }
        };
        new Main(new PrintWriter(out), new PrintWriter(err), false, opts, progress).compile(
                new String[] { "-source", "1.8", "-proc:none", "-warn:-nullAnnot", "../RxJava/src/main/java" });
        System.out.println(Instant.now() + " eclipse compiler done!");
    }
}
