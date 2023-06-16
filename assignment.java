import java.util.*;

public class Graph {
    private int V; // Number of vertices
    private LinkedList<Edge>[] adjList; // Adjacency list

    // Class to represent an edge
    class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Graph constructor
    Graph(int v) {
        V = v;
        adjList = new LinkedList[V];
        for (int i = 0; i < V; i++)
            adjList[i] = new LinkedList<>();
    }

    // Add an edge to the graph
    void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(dest, weight);
        adjList[src].add(edge);
    }

    // Greedy Best-First Search algorithm
    void greedyBestFirstSearch(int source, int destination) {
        // Create a visited array to keep track of visited vertices
        boolean[] visited = new boolean[V];

        // Create a priority queue (Min Heap)
        PriorityQueue<Integer> queue = new PriorityQueue<>((v1, v2) -> heuristic(v1, destination) - heuristic(v2, destination));
        // The comparator uses the heuristic function to determine the priority of vertices in the queue

        visited[source] = true;
        queue.add(source);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            if (currentVertex == destination) {
                System.out.println("\nDestination reached!");
                return;
            }

            // Iterate through all the adjacent vertices
            for (Edge edge : adjList[currentVertex]) {
                int adjacentVertex = edge.destination;

                if (!visited[adjacentVertex]) {
                    visited[adjacentVertex] = true;
                    queue.add(adjacentVertex);
                }
            }
        }

        System.out.println("\nDestination not reachable!");
    }

    // Heuristic function to estimate the cost from a vertex to the destination
    int heuristic(int currentVertex, int destination) {
        // You need to define your own heuristic function here
        // This function should estimate the cost from the currentVertex to the destination
        // The lower the heuristic value, the higher the priority in the queue
        // It should return an integer value representing the estimated cost
        return 0;
    }

    public static void main(String[] args) {
        // Create a graph
        Graph graph = new Graph(7);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 4);
        graph.addEdge(2, 5, 3);
        graph.addEdge(3, 6, 5);
        graph.addEdge(4, 6, 2);
        graph.addEdge(5, 6, 1);

        int source = 0;
        int destination = 6;
        System.out.print("Greedy Best-First Search path: ");
        graph.greedyBestFirstSearch(source, destination);
    }
}