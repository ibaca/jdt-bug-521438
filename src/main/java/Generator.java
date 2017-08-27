import static java.util.stream.IntStream.range;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;
import java.nio.file.Paths;
import java.util.List;

public class Generator {
    public static void main(String[] args) throws Exception {

        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder("Foo").addModifiers(PUBLIC);
        for (int i = 0; i < 3000; i++) {
            TypeVariableName T = TypeVariableName.get("T");
            ParameterizedTypeName listOfT = ParameterizedTypeName
                    .get(ClassName.get(List.class), WildcardTypeName.subtypeOf(T));
            typeBuilder.addMethod(MethodSpec.methodBuilder("generic" + i)
                    .addModifiers(PUBLIC, STATIC).addTypeVariable(T).returns(void.class)
                    .addParameters(() -> range(0, 10)
                            .mapToObj(p -> ParameterSpec.builder(listOfT, "arg" + p).build())
                            .iterator())
                    .build());
        }

        JavaFile.builder("", typeBuilder.build()).build().writeTo(Paths.get("."));
    }
}
