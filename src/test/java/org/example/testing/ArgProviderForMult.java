package org.example.testing;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class ArgProviderForMult implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Scanner sc;
        sc=new Scanner(Path.of("testData.txt"));
        List<Arguments> argumentsList=new ArrayList<>();
        while (sc.hasNextInt()){
           argumentsList.add(Arguments.of(
                   sc.nextInt(),
                   sc.nextInt(),
                   sc.nextInt()));
        }
        return argumentsList.stream();
    }
}
