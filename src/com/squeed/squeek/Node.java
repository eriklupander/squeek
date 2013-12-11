package com.squeed.squeek;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2013-12-10
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */
public class Node {

    private String op;
    private Node parent;
    private ArrayList<Node> children = new ArrayList<>();
    private ArrayList<String> sourceLines = new ArrayList<>();
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public ArrayList<String> getSourceLines() {
        return sourceLines;
    }

    public void setSourceLines(ArrayList<String> sourceLines) {
        this.sourceLines = sourceLines;
    }
}
