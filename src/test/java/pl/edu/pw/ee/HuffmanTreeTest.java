package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

public class HuffmanTreeTest {

    private void compareNodes(HuffmanNode nodeExpected, HuffmanNode nodeActual) {
        Assert.assertEquals(nodeExpected.getSymbol(), nodeActual.getSymbol());
        Assert.assertEquals(nodeExpected.getQuantity(), nodeActual.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowIllegalArgumentException_WhenTreeIsNullWhenMerging() {
        // given
        HuffmanTree tree = new HuffmanTree(3, "A");

        // when
        tree.mergeTree(null);

        // then
        assert false;
    }

    @Test
    public void Should_ReturnRoot_WhenGetRootCalled() {
        // given
        HuffmanNode rootExpected = new HuffmanNode(3, "A");
        HuffmanTree tree = new HuffmanTree(3, "A");

        // when
        HuffmanNode rootActual = tree.getRoot();

        // then
        compareNodes(rootExpected, rootActual);
    }

    @Test
    public void Should_ReturnCorrectNode_WhenGetRightAndGetLeftCalled() {
        // given
        HuffmanTree tree = new HuffmanTree(3, "A");
        HuffmanNode left = new HuffmanNode(1, "B");
        HuffmanNode right = new HuffmanNode(2, "C");

        tree.getRoot().setLeft(left);
        tree.getRoot().setRight(right);

        // when
        HuffmanNode leftActual = tree.getRoot().getLeft();
        HuffmanNode rightActual = tree.getRoot().getRight();

        // then
        compareNodes(left, leftActual);
        compareNodes(right, rightActual);
    }

    @Test
    public void Should_MergeRootsCorrectly_WhenMergeTreeCalled() {
        // given
        HuffmanTree treeLeft = new HuffmanTree(3, "A");
        HuffmanTree treeRight = new HuffmanTree(4, "B");

        // when
        HuffmanTree mergedTree = treeLeft.mergeTree(treeRight);

        // then
        compareNodes(new HuffmanNode(7, null), mergedTree.getRoot());
        compareNodes(treeLeft.getRoot(), mergedTree.getRoot().getLeft());
        compareNodes(treeRight.getRoot(), mergedTree.getRoot().getRight());
    }

    @Test
    public void Should_MergeRootsCorrectly_WhenMergeTreeCalledOn3ElemTreeAnd1ElemTree() {
        // given
        HuffmanTree treeLeft = new HuffmanTree(3, "A");
        HuffmanTree treeRight = new HuffmanTree(4, "B");
        HuffmanTree extraTree = new HuffmanTree(10, "C");

        // when
        HuffmanTree mergedTree = treeLeft.mergeTree(treeRight);
        HuffmanTree extraMergedTree = mergedTree.mergeTree(extraTree);

        // then
        compareNodes(new HuffmanNode(17, null), extraMergedTree.getRoot());
        compareNodes(treeLeft.getRoot(), extraMergedTree.getRoot().getLeft().getLeft());
        compareNodes(treeRight.getRoot(), extraMergedTree.getRoot().getLeft().getRight());
        compareNodes(extraTree.getRoot(), extraMergedTree.getRoot().getRight());
    }

    @Test
    public void Should_CorrectlyCompareTrees_WhenCompareToInvoked() {
        // given
        HuffmanTree treeLeft = new HuffmanTree(3, "A");
        HuffmanTree treeRight = new HuffmanTree(4, "B");
        HuffmanTree extraTree = new HuffmanTree(10, "C");
        HuffmanTree equalToTreeLeft = new HuffmanTree(3, "X");

        // then
        Assert.assertEquals(true, treeLeft.compareTo(treeRight) < 0);
        Assert.assertEquals(true, treeRight.compareTo(extraTree) < 0);
        Assert.assertEquals(false, extraTree.compareTo(treeLeft) < 0);
        Assert.assertEquals(true, equalToTreeLeft.compareTo(treeLeft) == 0);
    }
}
