package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(5);
        //Teste 1
//        graph.addEdge(0,1);
//        graph.addEdge(1,2);
//        graph.addEdge(2,3);
//        graph.addEdge(3,0);
//
//        graph.addEdge(1,4);
//        graph.addEdge(1,5);
//
//        graph.addEdge(2,4);
//        graph.addEdge(2,5);

        //Teste 2
//        graph.addEdge(0,1);
//        graph.addEdge(1,2);
//        graph.addEdge(2,3);
//        graph.addEdge(3,1);

        //Teste 3
        graph.addEdge(0,1);
        graph.addEdge(0,2);

        graph.addEdge(2,3);
        graph.addEdge(1,3);

        graph.addEdge(3,4);

        List<Integer> dfs = new ArrayList<Integer>();
        graph.dfs(0, dfs);
        System.out.println("DFS: " + dfs);

        System.out.println("Khan: " + graph.khan());
    }
}

