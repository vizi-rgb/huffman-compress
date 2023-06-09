package pl.edu.pw.ee;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CompressorTest {

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenReadUncompressedFileFunctionIsPassedNullFile() throws IOException {
        // given
        File file = null;
        Compressor compressor = new Compressor();
        int[] quantity = new int[10];

        // when
        compressor.readUncompressedFile(file, quantity);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenReadUncompressedFileFunctionIsPassedNullQuantityArray() throws IOException {
        // given
        File file = new File("file");
        Compressor compressor = new Compressor();
        int[] quantity = null;

        // when
        compressor.readUncompressedFile(file, quantity);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenReadUncompressedFileIsPassedIncorrectLengthArray() throws IOException {
        // given
        int[] quantity = new int[12];
        File file = new File("X");
        Compressor compressor = new Compressor();

        // when
        compressor.readUncompressedFile(file, quantity);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenFileContainsCharsFromOutsideAsciiTable() throws IOException {
        // given
        File file = new File("src/test/java/pl/edu/pw/ee/fileWithUnicodeChars.txt");
        Compressor compressor = new Compressor();
        int[] quantity = new int[256];

        // when
        compressor.readUncompressedFile(file, quantity);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenSaveCompressedFileIsPassedNullUncompressedFile() throws IOException {
        // given
        File file = null;
        File file2 = new File("x");
        Map<String, String> map = new HashMap<String, String>();
        Compressor compressor = new Compressor();

        // when
        compressor.saveCompressedFile(file, file2, map);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenSaveCompressedFileIsPassedNullCompressedFile() throws IOException {
        // given
        File file = null;
        File file2 = new File("x");
        Map<String, String> map = new HashMap<>();
        Compressor compressor = new Compressor();

        // when
        compressor.saveCompressedFile(file2, file, map);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenSaveCompressedFileIsPassedNullMap() throws IOException {
        // given
        File file = new File("y");
        File file2 = new File("x");
        Map<String, String> map = null;
        Compressor compressor = new Compressor();

        // when
        compressor.saveCompressedFile(file, file2, map);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenSaveHuffmanTreeIsPassedNullTreeFile() throws IOException {
        // given
        File treeFile = null;
        Map<String, String> map = new HashMap<>();
        Compressor compressor = new Compressor();

        // when
        compressor.saveHuffmanTree(treeFile, map);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenSaveHuffmanTreeIsPassedNullMap() throws IOException {
        // given
        File treeFile = new File("x");
        Map<String, String> map = null;
        Compressor compressor = new Compressor();

        // when
        compressor.saveHuffmanTree(treeFile, map);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenConstructHuffmanTreeIsPassedNullQuantityArray() {
        // given
        int[] quantity = null;
        Compressor compressor = new Compressor();

        // when
        compressor.constructHuffmanTree(quantity);

        // then
        assert false;
    }
}
