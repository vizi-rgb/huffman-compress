package pl.edu.pw.ee;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DecompressorTest {

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenReadTreeTreeFileIsNull() throws IOException {
        // given
        File treeFile = null;
        Decompressor decompressor = new Decompressor();

        // when
        decompressor.readTree(treeFile);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenSaveUncompressedFileIsPassedNullUncompressedFile() throws IOException {
        // given
        File file = null;
        File file1 = new File("x");
        Map<String, String> map = new HashMap<>();
        Decompressor decompressor = new Decompressor();

        // when
        decompressor.saveUncompressedFile(file, file1, map);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenSaveUncompressedFileIsPassedNullCompressedFile() throws IOException {
        // given
        File file = new File("x");
        File file1 = null;
        Map<String, String> map = new HashMap<>();
        Decompressor decompressor = new Decompressor();

        // when
        decompressor.saveUncompressedFile(file, file1, map);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenSaveUncompressedFileIsPassedNullMap() throws IOException {
        // given
        File file = new File("x");
        File file1 = new File("y");
        Map<String, String> map = null;
        Decompressor decompressor = new Decompressor();

        // when
        decompressor.saveUncompressedFile(file, file1, map);

        // then
        assert false;
    }
}
