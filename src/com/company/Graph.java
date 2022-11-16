package com.company;

import java.util.*;

public abstract class Graph {
	protected final int V;
	protected int E;
	protected List<Integer>[] adj;

	public Graph(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be non-negative");
		this.V = V;
		clear();
	}

	public boolean isEmpty() {
		return V == 0;
	}

	public void clear() {
		E = 0;
		adj = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v]; 
	}

	protected void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < V; i++) {
			sb.append("[" + i + "] => " + adj(i));
			sb.append("\n");
		}
		return sb.toString();
	}

	public boolean isEulerian(){
		for (List<Integer> vertex : adj) {
			if (vertex.size() % 2 != 0) {
				return false;
			}
		}
		return true;
	}

	public int getConnectedComponents(){
		List<Integer> visited = new ArrayList<Integer>();
		int connectedComponents = 0;
		for (int i = 0; i < adj.length; i++) {
			if (!visited.contains(i)) {
				List<Integer> connected = new ArrayList<Integer>();
				dfs(i, connected);
				visited.addAll(connected);
				connectedComponents++;
			}
		}
		return connectedComponents;
	}

	public void dfs(int i, List<Integer> sorted){
		List<Integer> temporary = new ArrayList<Integer>(); //Lista que contém os vértices marcados temporariamente
		List<Integer> permanent = new ArrayList<Integer>(); //Lista que contém os vértices marcados permanentemente
		visit(i, temporary, permanent, sorted);  //Inicia a pesquisa em profundidade a partir do vértice informado
		Collections.reverse(sorted);  //Inverte a lista resultante, visto que cada novo vértice foi adicionado no final
	}

	public void visit(int i, List<Integer> temporary, List<Integer> permanent, List<Integer> connected){
		if (permanent.contains(i)){
			return;
		}
		if (temporary.contains(i)){
			throw new IllegalArgumentException("O grafo possui pelo menos um ciclo");
		}

		temporary.add(i);
		List<Integer> adjacent = new ArrayList<>(adj[i]);
		Collections.sort(adjacent);
		for (Integer integer : adjacent) {
			visit(integer, temporary, permanent, connected);
		}

		temporary.remove((Integer) i);
		permanent.add(i);
		connected.add(i);
	}

	public List<Integer> khan(){
		List<Integer> sorted = new ArrayList<Integer>();
		List<Integer> noEdge = noIncomingEdges(); //Gera uma lista dos vértices sem arestas de entrada

		PriorityQueue<Integer> noEdgeQueue = new PriorityQueue<>();
		noEdgeQueue.addAll(noEdge); //Populando uma fila de prioridades com a lista de nodes sem arestas de entrada

		while (!noEdgeQueue.isEmpty()) { //Enquanto a fila de vertices sem arcos de entrada não estiver vazia
			int n = noEdgeQueue.poll(); //Remove o primeiro elemento da fila
			sorted.add(n); //Adiciona esse elemento à lista ordenada
			for (int j = 0; j < adj[n].size(); j++){ //Para cada nó m com um arco que vai de n para m
				int m = adj[n].get(j);
				adj[n].remove(j); //Remove o arco do grafo
				j--;
				if (noIncomingEdges(m)){ //Verifica se o vértice m não possui arcos de entrada
					noEdgeQueue.add(m); //Se não possuir, adiciona m para a fila de vértices sem entrada
				}
			}
		}
		if (hasEdges()) { //Avalia se o grafo ainda possuiu ciclos
			throw new IllegalArgumentException("O grafo possui pelo menos um ciclo");
		} else {
			return sorted; //Caso contrário, retorna a lista ordenada dos vértices
		}
	}

	public List<Integer> noIncomingEdges(){
		List<Integer> nodes = new ArrayList<Integer>(); //Nodes sem aresta de entrada
		for (int i = 0; i < V; i++){
			nodes.add(i);
		}

		for (List<Integer> integers : adj) {
			for (Integer integer : integers) {
				nodes.remove(integer);
			}
		}

		return nodes;
	}

	public boolean noIncomingEdges(int m){
		for (int i = 0; i < adj.length; i++){
			for (int j = 0; j < adj[i].size(); j++){
				if (adj[i].get(j) == m){
					return false;
				}
			}
		}

		return true;
	}

	public boolean hasEdges(){
		for (List<Integer> integers : adj) {
			if (integers.size() > 0) {
				return true;
			}
		}
		return false;
	}
}
