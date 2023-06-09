# huffman-compress
University project. Text file compression based on Huffman coding. Java 11.

## Compress
In order to compress a text file, one must include the desired text in a text file named *default.txt* and do:
```java
String pathWhereTheFileIs = "";
Huffman huffman = new Huffman();
huffman.huffman(pathWhereTheFileIs, true);
```
Compressed content will be output to a text file named *compressed.txt*

## Decompress
In order to decompress a text file, one must rename the compressed file to *compressed.txt* and do:
```java
String pathWhereTheFileIs = "";
Huffman huffman = new Huffman();
huffman.huffman(pathWhereTheFileIs, false);
```
Decompressed content will be output to a text file named *default.txt*
