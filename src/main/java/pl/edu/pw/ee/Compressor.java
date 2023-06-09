package pl.edu.pw.ee;

import java.io.*;
import java.util.*;

public class Compressor {


    public void readUncompressedFile(File uncompressedFile, int[] quantity) throws IOException {
        if (uncompressedFile == null || quantity == null) {
            throw new IllegalArgumentException("Argumenty nie moga byc nullami");
        }

        if (quantity.length != 256) {
            throw new IllegalArgumentException("Tablica wejsciowa powinna miec rozmiar 256 elementow!");
        }

        BufferedReader reader = new BufferedReader(FileIO.getNewFileReader(uncompressedFile));

        int currentSymbol;
        while ((currentSymbol = reader.read()) != -1) {
            if (currentSymbol < 256) {
                quantity[currentSymbol]++;
            } else if (polishChars(currentSymbol) != -1) {
                quantity[polishChars(currentSymbol)]++;
            } else {
                throw new IllegalArgumentException("Niedozwolone znaki w pliku! (" + (char) currentSymbol + " / " + (int) currentSymbol + ")");
            }
        }

        reader.close();
    }

    public int saveCompressedFile(File uncompressedFile, File compressedFile, Map<String, String> nodeSymbolToCodeMap) throws IOException {
        if (uncompressedFile == null || compressedFile == null || nodeSymbolToCodeMap == null) {
            throw new IllegalArgumentException("Argumenty nie moga byc nullami");
        }

        int current;
        StringBuilder encodedTextBuilder = new StringBuilder();
        BufferedReader uncompressedFileReader = new BufferedReader(FileIO.getNewFileReader(uncompressedFile));

        while ((current = uncompressedFileReader.read()) != -1) {
            if (current < 256) {
                encodedTextBuilder.append(nodeSymbolToCodeMap.get(Character.toString((char) current)));
            } else if (polishChars(current) != -1) {
                encodedTextBuilder.append(nodeSymbolToCodeMap.get(Character.toString((char) polishChars(current))));
            } else {
                throw new IOException("Plik do kompresji zawiera nieobslugiwane symbole! " + (char) current);
            }
        }

        String encodedText = encodedTextBuilder.toString();
        int redundantBits = encodedText.length() % 8 == 0 ? 0 : Byte.SIZE - encodedText.length() % 8;
        int size = (encodedText.length() + redundantBits) / 8 + 1;
        byte[] bytes = new byte[size];
        bytes[0] = (byte) redundantBits;

        int currentCharacterIndex = 0;
        for (int i = 1; i < size && currentCharacterIndex < encodedText.length(); i++) {
            for (int j = 0; j < Byte.SIZE && currentCharacterIndex < encodedText.length(); j++) {
                bytes[i] <<= 1;
                bytes[i] |= (encodedText.charAt(currentCharacterIndex) - '0');
                currentCharacterIndex++;
            }
        }

        bytes[size - 1] <<= redundantBits;

        FileOutputStream outputStream = new FileOutputStream(compressedFile);
        outputStream.write(bytes);
        outputStream.close();
        return encodedText.length();
    }

    public void saveHuffmanTree(File treeFile, Map<String, String> nodeSymbolToCodeMap) throws IOException {
        if (treeFile == null || nodeSymbolToCodeMap == null) {
            throw new IllegalArgumentException("Argumenty nie moga byc nullami");
        }

        FileWriter writer = FileIO.getNewFileWriter(treeFile);
        for (String key : nodeSymbolToCodeMap.keySet()) {
            String toWrite = key + nodeSymbolToCodeMap.get(key) + "\n";
            writer.write(toWrite);
        }

        writer.close();
    }

    public HuffmanTree constructHuffmanTree(int[] quantity) {
        if (quantity == null) {
            throw new IllegalArgumentException("Tablica czestosci wystepowania znakow nie moze byc nullem!");
        }

        PriorityQueue<HuffmanTree> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < quantity.length; i++) {
            if (quantity[i] > 0) {
                priorityQueue.add(new HuffmanTree(quantity[i], Character.toString((char) i)));
            }
        }

        while (priorityQueue.size() > 1) {
            HuffmanTree firstTree = priorityQueue.poll();
            HuffmanTree secondTree = priorityQueue.poll();
            HuffmanTree mergedTree = firstTree.mergeTree(secondTree);
            priorityQueue.add(mergedTree);
        }

        if (priorityQueue.size() == 0) {
            return null;
        }

        return priorityQueue.poll();
    }

    private int polishChars(int character) {
        switch (character) {
            case 'ą':
                return 'a';
            case 'ę':
                return 'e';
            case 'ń':
                return 'n';
            case 'ć':
                return 'c';
            case 'ł':
                return 'l';
            case 'ó':
                return 'o';
            case 'ś':
                return 's';
            case 'ż':
            case 'ź':
                return 'z';
            case 'Ą':
                return 'A';
            case 'Ę':
                return 'E';
            case 'Ń':
                return 'N';
            case 'Ć':
                return 'C';
            case 'Ł':
                return 'L';
            case 'Ó':
                return 'O';
            case 'Ś':
                return 'S';
            case 'Ż':
            case 'Ź':
                return 'Z';
            default:
                return -1;
        }
    }
}
