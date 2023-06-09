package pl.edu.pw.ee;

public class HuffmanNode {

    private int quantity;
    private String symbol;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(int quantity, String symbol) {
        this.quantity = quantity;
        this.symbol = symbol;
        this.left = null;
        this.right = null;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setLeft(HuffmanNode node) {
        left = node;
    }

    public void setRight(HuffmanNode node) {
        right = node;
    }
}
