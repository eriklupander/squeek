package com.squeed.squeek;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2013-12-10
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
public class SqkParser {

    public static void main(String[] args) throws IOException {
        Node root = new SqkParser().parse(new FileInputStream(new File("c:\\idea-workspace\\squeek\\samples\\sample1.sqk")));
        dumpNode(root, 0);
    }

    private static void dumpNode(Node node, int depth) {
        depth++;
        if(node != null) {
            System.out.println(tab(depth) + "" +(node.getOp() != null ? node.getOp() : "No operation specified") +"\n"+tab(depth+1)+"------------------------>\n"+ tab(depth+1) +(node.getSource() != null ? node.getSource() : "N/A")+"\n"+tab(depth+1)+"<------------------------");
            if(node.getChildren() != null) {
                for(Node child : node.getChildren()) {
                    dumpNode(child, depth);
                }
            }
        }

    }

    private static String tab(int depth) {
        StringBuilder buf = new StringBuilder();
        for(int a = 0; a < depth;a++) {
            buf.append("    ");
        }
        return buf.toString();
    }

    private Node parse(FileInputStream fis) throws IOException {
        byte[] data = new byte[fis.available()];
        fis.read(data);
        String source = new String(data, Charset.forName("UTF-8"));
        Node root = parseNode(source, null, 0);
        return root;
    }

    private Node parseNode(String source, Node parent, int level) {

        int mylevel = level;
        Node current = new Node();
        current.setParent(parent);
        for(int a = 0; a < source.length(); a++) {
            if(source.charAt(a) == '{') {


               // System.out.println("Adding child node starting at index "+a+" and level " + level + ". Base source: " + source.substring(a));
                Node childNode = parseNode(source.substring(a + 1), current, ++level);
                if(childNode != null) {
                    String op = parseOp(source, a);
                    childNode.setOp(op);
                    current.getChildren().add(childNode);
                }

            }
            if(source.charAt(a) == '}' && mylevel == level) {
                //System.out.println("Found end block, returning source " + source.substring(0, a) + " at level " + level);

                return current;
            }
        }
       // System.out.println("Came to the end without encountering a '}'");
        // Must be a leaf node with code!
        current.setSource(source.substring(0));
        return current;
    }

    private String parseOp(String source, int a) {

        if(source.length() <= a) return source;
        String tmp = source.substring(0, a-1);
        return tmp.substring(tmp.lastIndexOf("\r\n")+1).trim();
    }

}
