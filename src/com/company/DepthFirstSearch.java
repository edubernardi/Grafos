package com.company;

public class DepthFirstSearch {
	private enum Color {
		WHITE, GRAY, BLACK
	};

	private Vertex[] vertices;
	private int time; 

	public class Vertex {
		private int v;
		private Color color;
		private int d;  // discovery time
		private int f;  // finished time
		private int pi; // predecessor

		public Vertex(int v, Color color) {
			this.v = v;
			this.color = color;
			this.pi = -1;
		}

		public int v() {
			return v;
		}

		public int d() {
			return d;
		}

		public int f() {
			return f;
		}

		public int pi() {
			return pi;
		}

		@Override
		public boolean equals(Object obj) {
			return v == ((Vertex) obj).v;
		}
	}

	public Vertex[] dfs(Graph g) {
		vertices = new Vertex[g.V()];
		for (int i = 0; i < g.V(); i++) {
			vertices[i] = new Vertex(i, Color.WHITE);
		}
		time = 0;
		for (Vertex u : vertices) {
			if (u.color == Color.WHITE) {
				visit(g, u);
			}
		}
		return vertices;
	}
	
	public void visit(Graph g, Vertex u) {
		u.d = ++time;
		u.color = Color.GRAY;
		for (int v : g.adj(u.v)) {
			if (vertices[v].color == Color.WHITE) {
				vertices[v].pi = u.v;
				visit(g, vertices[v]);
			}
		}
		u.color = Color.BLACK;
		u.f = ++time;
	}
}
