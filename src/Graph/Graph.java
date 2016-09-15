package Graph;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.Map.Entry;

public class Graph {
    private final Map<String, Graph.Vertex> graph;
    public static HashMap<String, String[]> ListeKeyStation = null;

    public Graph(Graph.Edge[] var1) {
        this.graph = new HashMap(var1.length);
        Graph.Edge[] var2 = var1;
        int var3 = var1.length;

        int var4;
        Graph.Edge var5;
        for(var4 = 0; var4 < var3; ++var4) {
            var5 = var2[var4];
            if(!this.graph.containsKey(var5.v1)) {
                this.graph.put(var5.v1, new Graph.Vertex(var5.v1));
            }

            if(!this.graph.containsKey(var5.v2)) {
                this.graph.put(var5.v2, new Graph.Vertex(var5.v2));
            }
        }

        var2 = var1;
        var3 = var1.length;

        for(var4 = 0; var4 < var3; ++var4) {
            var5 = var2[var4];
            ((Graph.Vertex)this.graph.get(var5.v1)).neighbours.put(this.graph.get(var5.v2), Integer.valueOf(var5.dist));
        }

    }

    public void dijkstra(String var1) {
        if(!this.graph.containsKey(var1)) {
            System.out.println("IMPOSSIBLE\n");
        } else {
            Graph.Vertex var2 = (Graph.Vertex)this.graph.get(var1);
            TreeSet var3 = new TreeSet();
            Iterator var4 = this.graph.values().iterator();

            while(var4.hasNext()) {
                Graph.Vertex var5 = (Graph.Vertex)var4.next();
                var5.previous = var5 == var2?var2:null;
                var5.dist = var5 == var2?0:2147483647;
                var3.add(var5);
            }

            this.dijkstra((NavigableSet)var3);
        }
    }

    private void dijkstra(NavigableSet<Graph.Vertex> var1) {
        label22:
        while(true) {
            if(!var1.isEmpty()) {
                Graph.Vertex var2 = (Graph.Vertex)var1.pollFirst();
                if(var2.dist != 2147483647) {
                    Iterator var4 = var2.neighbours.entrySet().iterator();

                    while(true) {
                        if(!var4.hasNext()) {
                            continue label22;
                        }

                        Entry var5 = (Entry)var4.next();
                        Graph.Vertex var3 = (Graph.Vertex)var5.getKey();
                        int var6 = var2.dist + ((Integer)var5.getValue()).intValue();
                        if(var6 < var3.dist) {
                            var1.remove(var3);
                            var3.dist = var6;
                            var3.previous = var2;
                            var1.add(var3);
                        }
                    }
                }
            }

            return;
        }
    }

    public void printPath(String var1) {
        if(!this.graph.containsKey(var1)) {
            System.out.println("IMPOSSIBLE\n");
        } else {
            ((Graph.Vertex)this.graph.get(var1)).printPath();
        }
    }

    public void printAllPaths() {
        Iterator var1 = this.graph.values().iterator();

        while(var1.hasNext()) {
            Graph.Vertex var2 = (Graph.Vertex)var1.next();
            var2.printPath();
            System.out.println();
        }

    }

    public static class Vertex implements Comparable<Graph.Vertex> {
        public final String name;
        public int dist = 2147483647;
        public Graph.Vertex previous = null;
        public final Map<Graph.Vertex, Integer> neighbours = new HashMap();

        public Vertex(String var1) {
            this.name = var1;
        }

        private void printPath() {
            if(this == this.previous) {
                System.out.printf("%s\n", new Object[]{((String[])Graph.ListeKeyStation.get(this.name))[1].replace("\"", "")});
            } else if(this.previous == null) {
                System.out.println("IMPOSSIBLE\n");
            } else {
                this.previous.printPath();
                System.out.printf("%s\n", new Object[]{((String[])Graph.ListeKeyStation.get(this.name))[1].replace("\"", "")});
            }

        }

        public int compareTo(Graph.Vertex var1) {
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
