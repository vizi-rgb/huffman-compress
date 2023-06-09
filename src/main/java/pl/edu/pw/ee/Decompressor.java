package pl.edu.pw.ee;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Decompressor {

    public Map<String, String> readTree(File treeFile) throws IOException {
        if (treeFile == null) {
            throw new IllegalArgumentException("Plik z drzewem nie moze byc nullem");
        }

        Map<String, String> codeToNodeSymbolMap = new HashMap<>();
        FileReader reader = FileIO.getNewFileReader(treeFile);

        int character;
        boolean isNode = true;
        String nodeSymbol = "";
        StringBuilder codeStringBuilder = new StringBuilder();
        while ((character = reader.read()) != -1) {
            if (isNode) {
                nodeSymbol = Character.toString((char) character);
                isNode = false;
                continue;
            }

            if (character == '\n') {
                codeToNodeSymbolMap.put(codeStringBuilder.toString(), nodeSymbol);
                codeStringBuilder.setLength(0);
                isNode = true;
                continue;
            }

            codeStringBuilder.append(character - '0');
        }
        return codeToNodeSymbolMap;
    }

    public int saveUncompressedFile(File uncompressedFile, File compressedFile, Map<String, String> codeToNodeSymbolMap) throws IOException {
        if (uncompressedFile == null || compressedFile == null || codeToNodeSymbolMap == null) {
            throw new IllegalArgumentException("Argumenty nie moga byc nullami");
        }

        byte code;
        long counter = 1;
        int currentCharacter;
        int uncompressedFileBytes = 0;
        long fileSize = compressedFile.length();

        FileInputStream compressedBinary = new FileInputStream(compressedFile);
        FileWriter writer = FileIO.getNewFileWriter(uncompressedFile);
        StringBuilder stringBuilder = new StringBuilder();

        int reduntantBits = compressedBinary.read();

        while ((currentCharacter = compressedBinary.read()) != -1 && counter++ < fileSize - 1) {
            code = (byte) currentCharacter;
            String binaryRepresentation = byteToString(code);

            for (int i = 0; i < Byte.SIZE; i++) {
                stringBuilder.append(binaryRepresentation.charAt(i));

                if (codeToNodeSymbolMap.get(stringBuilder.toString()) != null) {
                    uncompressedFileBytes += stringBuilder.toString().length();
                    writer.write(codeToNodeSymbolMap.get(stringBuilder.toString()));
                    stringBuilder.setLength(0);
                }
            }
        }

        code = (byte) currentCharacter;
        String binaryRepresentation = byteToString(code);

        for (int i = 0; i < Byte.SIZE - reduntantBits; i++) {
            stringBuilder.append(binaryRepresentation.charAt(i));

            if (codeToNodeSymbolMap.get(stringBuilder.toString()) != null) {
                uncompressedFileBytes += stringBuilder.toString().length();
                writer.write(codeToNodeSymbolMap.get(stringBuilder.toString()));
                stringBuilder.setLength(0);
            }
        }

        writer.close();
        compressedBinary.close();
        return uncompressedFileBytes;
    }

    private String byteToString(byte data) {
        StringBuilder stringBuilder = new StringBuilder();
        int x = (1 << 7);

        while (x != 0) {
            if ((x & data) == x) {
                stringBuilder.append("1");
            } else {
                stringBuilder.append("0");
            }

            x >>>= 1;
        }

        return stringBuilder.toString();
    }
}
