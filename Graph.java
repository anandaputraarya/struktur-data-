import java.util.*;

class Graph {
    private int V; 
    private List<List<Node>> adjList; 

 
    class Node {
        int vertex, weight;

        Node(int v, int w) {
            vertex = v;
            weight = w;
        }
    }

    public Graph(int v) {
        V = v;
        adjList = new ArrayList<>(v);
        for (int i = 0; i < v; ++i)
            adjList.add(new ArrayList<>());
    }


    public void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new Node(v, weight));
        adjList.get(v).add(new Node(u, weight)); 
    }

 
    public void dijkstra(int source) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        minHeap.add(new Node(source, 0));

        while (!minHeap.isEmpty()) {
            int u = minHeap.poll().vertex;

            for (Node neighbor : adjList.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    minHeap.add(new Node(v, distance[v]));
                }
            }
        }

   
        System.out.println("Shortest distances from source " + source + ":");
        for (int i = 0; i < V; ++i) {
            if (distance[i] != Integer.MAX_VALUE) {
                System.out.println("To " + i + ": " + distance[i]);
            } else {
                System.out.println("To " + i + ": Not reachable");
            }
        }
    }
}

public class ShortestPathAlgorithm {
    public static void main(String[] args) {
        int V = 5;
        Graph graph = new Graph(V);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 1);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 4);

        int source = 0;
        graph.dijkstra(source);
    }
}
