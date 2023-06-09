package pl.edu.pw.ee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Mapper {

    public HashMap<String, String> mapCodesToHuffmanTreeNodes(HuffmanTree huffmanTree) {
        if (huffmanTree == null) {
            throw new IllegalArgumentException("Drzewo nie moze byc nullem!");
        }

        HashMap<String, String> hashMap = new HashMap<>();
        Queue<HuffmanNode> nodesList = new LinkedList<>();
        Queue<String> codesList = new LinkedList<>();

        nodesList.add(huffmanTree.getRoot());
        codesList.add("");

        while (!nodesList.isEmpty()) {
            HuffmanNode currentNode = nodesList.poll();
            String currentCode = codesList.poll();

            if (currentNode.getSymbol() != null) {
                hashMap.put(currentNode.getSymbol(), currentCode);
            } else {
                if (currentNode.getLeft() != null) {
                    nodesList.add(currentNode.getLeft());
                    codesList.add(currentCode + "0");
                }

                if (currentNode.getRight() != null) {
                    nodesList.add(currentNode.getRight());
                    codesList.add(currentCode + "1");
                }
            }
        }

        if (hashMap.size() == 1) {
            hashMap.replace(huffmanTree.getRoot().getSymbol(), "0");
        }

        return hashMap;
    }
}
