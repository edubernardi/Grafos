package com.company;

public class DirectedGraph extends Graph {

    public DirectedGraph(int V) {
        super(V);
    }

    @Override
    public void addEdge(int v, int w) {
        super.addEdge(v, w);
        adj[v].add(w);
    }

}
