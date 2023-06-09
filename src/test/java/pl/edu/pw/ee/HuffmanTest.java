package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HuffmanTest {

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenPathIsNull() {
        // given
        String path = null;
        Huffman huffman = new Huffman();

        // when
        huffman.huffman(path, true);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenFileToCompressNotFound() {
        // given
        Huffman huffman = new Huffman();
        String path = "nieistniejaca/sciezka";

        // when
        huffman.huffman(path, true);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_WhenFileToDecompressNotFound() {
        // given
        Huffman huffman = new Huffman();
        String path = "nieistniejaca/sciezka";

        // when
        huffman.huffman(path, false);

        // then
        assert false;
    }

    @Test
    public void Should_Return2_WhenDefaultFileIsCompressed() {
        // given
        String path = "src/test/java/pl/edu/pw/ee";
        Huffman huffman = new Huffman();

        // when
        int ret = huffman.huffman(path, true);

        Assert.assertEquals(6, ret);
    }

    @Test
    public void Should_CorrectlyDecompressFile_WhenDecompressCalled() throws IOException {
        // given
        String path = "src/test/java/pl/edu/pw/ee";
        Huffman huffman = new Huffman();
        File file = new File(path, "default.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // when
        huffman.huffman(path, true);
        huffman.huffman(path, false);
        String content = reader.readLine();

        // then
        Assert.assertEquals("test", content);
    }
}
