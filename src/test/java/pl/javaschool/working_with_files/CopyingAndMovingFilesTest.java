package pl.javaschool.working_with_files;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class CopyingAndMovingFilesTest {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";

    @Test
    public void givenExistingSourceFile_whenCopy_thenCopiedFileExists() throws IOException {
        //given
        Path sourceDirectory = Paths.get(BASE_DIR + "/sourceDir");
        Path destinationDirectory = Paths.get(BASE_DIR + "/destinationDir");
        Files.createDirectory(sourceDirectory);
        Files.createDirectory(destinationDirectory);

        Path sourceFile = sourceDirectory.resolve("sourceFile.txt");
        Path destinationFile = destinationDirectory.resolve("destinationFile.txt");

        Files.createFile(sourceFile);
        BufferedWriter writer = Files.newBufferedWriter(sourceFile);
        writer.write("Hejka!");
        writer.close();

        assertThat(Files.exists(sourceFile)).isTrue();
        assertThat(Files.exists(destinationFile)).isFalse();

        //when
        Files.copy(sourceFile, destinationFile);

        //then
        assertThat(Files.exists(destinationFile)).isTrue();
    }
}
