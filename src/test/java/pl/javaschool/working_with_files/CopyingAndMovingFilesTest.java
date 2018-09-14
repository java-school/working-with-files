package pl.javaschool.working_with_files;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.javaschool.working_with_files.utils.TestUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.assertj.core.api.Assertions.assertThat;

public class CopyingAndMovingFilesTest {
    private static String BASE_DIR = System.getProperty("user.dir") + "/temp";
    private static final Path BASE_PATH = Paths.get(BASE_DIR);

    @Before
    public void setUp() throws Exception {
        Files.createDirectories(BASE_PATH);
    }

    @After
    public void tearDown() {
        TestUtils.deleteRecursively(BASE_PATH);
    }

    @Test
    public void givenExistingSourceFile_whenCopy_thenCopiedFileExists() throws IOException {
        //given
        Path sourceFile = givenSourceFile();
        Path destinationFile = givenDestinationFilePath();

        //when
        Files.copy(sourceFile, destinationFile);


        //then
        assertThat(Files.exists(destinationFile)).isTrue();
    }

    @Test
    public void givenExistingSourceAndDestinationFiles_whenCopyWithReplaceFlag_thenCopiedFileIsReplaced() throws IOException {
        //given
        Path sourceFile = givenSourceFile();
        Path destinationFile = givenDestinationFilePath();

        //when
        Files.copy(sourceFile, destinationFile);

//        Files.copy(sourceFile, destinationFile);//Ponowne skopiowanie pliku gdy docelowy plik istnieje spowoduje rzucenie wyjątku FileAlreadyExistsException

//        Poniżej fragment kodu, kóry zastąpi istniejący plik podczas kopiowania:
        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);

        //then
        assertThat(Files.exists(destinationFile)).isTrue();
    }

    private Path givenSourceFile() throws IOException {
        Path sourceDirectory = Paths.get(BASE_DIR + "/sourceDir");
        Files.createDirectory(sourceDirectory);

        Path sourceFile = sourceDirectory.resolve("sourceFile.txt");
        Files.createFile(sourceFile);

        BufferedWriter writer = Files.newBufferedWriter(sourceFile);
        writer.write("Hejka!");
        writer.close();

        assertThat(Files.exists(sourceFile)).isTrue();

        return sourceFile;
    }

    private Path givenDestinationFilePath() throws IOException {
        Path destinationDirectory = Paths.get(BASE_DIR + "/destinationDir");
        Files.createDirectory(destinationDirectory);

        Path destinationFile = destinationDirectory.resolve("destinationFile.txt");

        assertThat(Files.exists(destinationFile)).isFalse();

        return destinationFile;
    }
}
