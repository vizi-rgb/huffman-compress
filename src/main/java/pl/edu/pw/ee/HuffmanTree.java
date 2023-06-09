package pl.edu.pw.ee;

public class HuffmanTree implements Comparable<HuffmanTree> {

    private HuffmanNode root;

    public HuffmanTree(int quantity, String symbol) {
        root = new HuffmanNode(quantity, symbol);
    }

    public HuffmanTree(int quantity, String symbol, HuffmanNode left, HuffmanNode right) {
        root = new HuffmanNode(quantity, symbol);
        root.setLeft(left);
        root.setRight(right);
    }

    public HuffmanTree mergeTree(HuffmanTree tree) {
        if (tree == null) {
            throw new IllegalArgumentException("Tree cannot be null!");
        }

        int quantity = this.root.getQuantity() + tree.root.getQuantity();
        HuffmanTree newTree = new HuffmanTree(quantity, null, this.root, tree.root);

        return newTree;
    }

    public HuffmanNode getRoot() {
        return root;
    }

    @Override
    public int compareTo(HuffmanTree o) {
        if (o == null) {
            throw new IllegalArgumentException("Drzewo nie moze byc nullem!");
        }
        return this.root.getQuantity() - o.root.getQuantity();
    }
}
