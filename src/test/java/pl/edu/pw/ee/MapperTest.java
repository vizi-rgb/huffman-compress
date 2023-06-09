package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class MapperTest {

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowIllegalArgumentException_WhenHuffmanTreeIsNull() {
        // given
        HuffmanTree tree = null;
        Mapper mapper = new Mapper();

        // when
        mapper.mapCodesToHuffmanTreeNodes(tree);

        // then
        assert false;
    }

    @Test
    public void Should_Map0ToNode_WhenPassedOneElemTree() {
        // given
        HuffmanTree tree = new HuffmanTree(10, "A");
        Mapper mapper = new Mapper();
        Map<String, String> map;

        // when
        map = mapper.mapCodesToHuffmanTreeNodes(tree);

        // then
        Assert.assertEquals("0", map.get(tree.getRoot().getSymbol()));
        Assert.assertEquals(1, map.size());
    }

    @Test
    public void Should_MapCodesToNodes_WhenPassed2ElemTree() {
        // given
        HuffmanTree tree1 = new HuffmanTree(10, "A");
        HuffmanTree tree2 = new HuffmanTree(8, "B");
        HuffmanTree finalTree = tree2.mergeTree(tree1);
        Mapper mapper = new Mapper();
        Map<String, String> map;

        // when
        map = mapper.mapCodesToHuffmanTreeNodes(finalTree);

        // then
        Assert.assertEquals("1", map.get("A"));
        Assert.assertEquals("0", map.get("B"));
        Assert.assertEquals(2, map.size());
    }

}
