package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

public class HuffmanNodeTest {

    @Test
    public void Should_ReturnQuantity_WhenGetQuantityCalled() {
        // given
        HuffmanNode node = new HuffmanNode(3, null);

        // then
        Assert.assertEquals(3, node.getQuantity());
    }

    @Test
    public void Should_ReturnSymbol_WhenGetSymbolCalled() {
        // given
        HuffmanNode node = new HuffmanNode(3, "x");

        // then
        Assert.assertEquals("x", node.getSymbol());
    }

    @Test
    public void Should_ReturnLeft_WhenGetLeftCalled() {
        // given
        HuffmanNode node = new HuffmanNode(3, "x");
        node.setLeft(new HuffmanNode(1, "y"));

        // then
        Assert.assertEquals("y", node.getLeft().getSymbol());
        Assert.assertEquals("x", node.getSymbol());
    }

    @Test
    public void Should_ReturnRight_WhenGetRightCalled() {
        // given
        HuffmanNode node = new HuffmanNode(3, "x");
        node.setRight(new HuffmanNode(1, "z"));

        // then
        Assert.assertEquals("z", node.getRight().getSymbol());
        Assert.assertEquals("x", node.getSymbol());
    }

    @Test
    public void Should_SetRight_WhenSetRightCalled() {
        // given
        HuffmanNode node = new HuffmanNode(3, "x");
        HuffmanNode rightNode = new HuffmanNode(1, "z");
        node.setRight(rightNode);

        // then
        Assert.assertEquals(rightNode, node.getRight());
        Assert.assertEquals("x", node.getSymbol());
    }

    @Test
    public void Should_SetLeft_WhenSetLeftCalled() {
        // given
        HuffmanNode node = new HuffmanNode(3, "x");
        HuffmanNode leftNode = new HuffmanNode(1, "y");
        node.setRight(leftNode);

        // then
        Assert.assertEquals(leftNode, node.getRight());
        Assert.assertEquals("x", node.getSymbol());
    }
}
