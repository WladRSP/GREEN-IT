package Graph;

import java.util.*;

import static java.lang.System.out;

public class graph {
    private final Map<String, Graph.graph.Vertex> graph;
    public static HashMap<String, String[]> ListeKeyStation;

    public graph(Graph.graph.Edge[] var1) {
        int var3 = var1.length;
        this.graph = new HashMap<String, Vertex>(var3);

        Graph.graph.Edge var5;
        for (int i = 0; i < var3; ++i) {
            var5 = var1[i];
            if (!this.graph.containsKey(var5.v1)) {
                this.graph.put(var5.v1, new graph.Vertex(var5.v1));
            }

            if (!this.graph.containsKey(var5.v2)) {
                this.graph.put(var5.v2, new graph.Vertex(var5.v2));
            }

            this.graph.get(var5.v1).neighbours.put(this.graph.get(var5.v2), var5.dist);
        }

    }

    public void dijkstra(String var1) {
        if (this.graph.containsKey(var1)) {
            TreeSet<Vertex> var3 = new TreeSet<Vertex>();

            for (Vertex var5 : this.graph.values()) {
                if (var5 == this.graph.get(var1)) {
                    var5.previous = this.graph.get(var1);
                    var5.dist = 0;
                } else {
                    var5.previous = null;
                    var5.dist = 2147483647;
                }
                var3.add(var5);
            }

            this.dijkstra(var3);
        } else {
            out.println("IMPOSSIBLE\n");
        }
    }

    private void dijkstra(NavigableSet<Graph.graph.Vertex> var1) {
        int var6;
        while (!var1.isEmpty()) {
            Graph.graph.Vertex var2 = var1.pollFirst();
            if (var2.dist != 2147483647) {
                for (Map.Entry<Vertex, Integer> var5 : var2.neighbours.entrySet()) {
                    Vertex var3 = var5.getKey();
                    var6 = var2.dist + var5.getValue();
                    if (var6 < var3.dist) {
                        var1.remove(var3);
                        var3.dist = var6;
                        var3.previous = var2;
                        var1.add(var3);
                    }
                }
            }
        }
    }

    public void printPath(String var1) {
        if (this.graph.containsKey(var1)) {
            this.graph.get(var1).printPath();
        } else {
            out.println("IMPOSSIBLE\n");
        }
    }

    public static class Vertex implements Comparable<Graph.graph.Vertex> {
        public final String name;
        public int dist = 2147483647;
        public Graph.graph.Vertex previous = null;
        public final Map<Graph.graph.Vertex, Integer> neighbours = new HashMap<Vertex, Integer>();

        public Vertex(String var1) {
            this.name = var1;
        }

        private void printPath() {
            if (this == this.previous) {
                out.printf("%s\n", Graph.graph.ListeKeyStation.get(this.name)[1].replace("\"", ""));
            } else if (this.previous == null) {
                out.println("IMPOSSIBLE\n");
            } else {
                this.previous.printPath();
                out.printf("%s\n", Graph.graph.ListeKeyStation.get(this.name)[1].replace("\"", ""));
            }
        }

        public int compareTo(Graph.graph.Vertex var1) {
            return Integer.compare(this.dist, var1.dist);
        }
    }

    public static class Edge {
        public final String v1;
        public final String v2;
        public final int dist;

        public Edge(String var1, String var2, int var3) {
            this.v1 = var1;
            this.v2 = var2;
            this.dist = var3;
        }
    }
}
