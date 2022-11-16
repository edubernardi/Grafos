package com.company;

public class UndirectedGraph extends Graph {

	public UndirectedGraph(int V) {
		super(V);
	}	
	
	@Override
	public void addEdge(int v, int w) {
		super.addEdge(v, w);
		adj[v].add(w);
		adj[w].add(v);
	}	
	
}

