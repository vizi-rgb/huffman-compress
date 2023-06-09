package pl.edu.pw.ee;

import java.io.*;
import java.util.*;

public class Huffman {

    private final String defaultFile = "default.txt";
    private final String compressedFile = "compressed.txt";
    private final String treeFile = "tree.txt";

    public int huffman(String pathToRootDir, boolean compress) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Sciezka nie moze byc nullem");
        }

        File uncompressed = new File(pathToRootDir, defaultFile);
        File compressed = new File(pathToRootDir, compressedFile);
        File tree = new File(pathToRootDir, treeFile);

        if (compress) {
            if (!uncompressed.exists()) {
                String msg = "Plik o nazwie " + defaultFile + " nie istnieje w lokalizacji " + pathToRootDir;
                throw new IllegalArgumentException(msg);
            }

            try {
                return compress(uncompressed, compressed, tree);
            } catch (IOException io) {
                throw new IllegalArgumentException("Blad I/O podczas kompresji");
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            }

        } else {
            if (!compressed.exists() || !tree.exists()) {
                String msg = "Nie znaleziono plików: " + compressedFile + " " + treeFile + " w lokalizacji "
                        + pathToRootDir;
                throw new IllegalArgumentException(msg);
            }

            try {
                return decompress(uncompressed, compressed, tree);
            } catch (IOException io) {
                throw new IllegalArgumentException("Blad I/O podczas dekompresji");
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

    }

    private int compress(File uncompressedFile, File compressedFile, File treeFile) throws IOException {
        if (uncompressedFile == null) {
            throw new IllegalArgumentException("Plik do kompresji nie moze byc nullem");
        }

        int[] quantity = new int[256];

        Compressor compressor = new Compressor();
        compressor.readUncompressedFile(uncompressedFile, quantity);
        HuffmanTree huffmanTree = compressor.constructHuffmanTree(quantity);
        if (huffmanTree == null) {
            throw new IllegalArgumentException("Plik do kompresji jest pusty");
        }

        Mapper huffmanMapper = new Mapper();
        Map<String, String> nodeSymbolToCodeMap = huffmanMapper.mapCodesToHuffmanTreeNodes(huffmanTree);

        compressor.saveHuffmanTree(treeFile, nodeSymbolToCodeMap);
        return compressor.saveCompressedFile(uncompressedFile, compressedFile, nodeSymbolToCodeMap);
    }

    private int decompress(File uncompressedFile, File compressedFile, File treeFile) throws IOException {
        if (compressedFile == null || treeFile == null) {
            throw new IllegalArgumentException("Pliki potrzebne do dekompresji nie moga byc nullami");
        }
        Decompressor decompressor = new Decompressor();
        Map<String, String> codeToNodeSymbolMap = decompressor.readTree(treeFile);

        return decompressor.saveUncompressedFile(uncompressedFile, compressedFile, codeToNodeSymbolMap);
    }

}
