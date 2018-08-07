package info.blockchain.helper;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class RawJsonStringLoaderHelper {

    @SneakyThrows
    public static String getRawJsonStringResponse(String fileName) {
        @Cleanup InputStreamReader inputStream = new InputStreamReader(ClassLoader.getSystemResourceAsStream(fileName));
        @Cleanup BufferedReader bufferedReader = new BufferedReader(inputStream);

        return bufferedReader.lines().collect(Collectors.joining());
    }
}
