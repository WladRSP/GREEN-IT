import Graph.graph;
import Graph.graph.Edge;

import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.out;

public class Solution {

    public static final float blop = ((float) Math.PI) / 180.0f;

    public static void main(String[] args) {
        long debut = System.currentTimeMillis();
        final Scanner scanner = new Scanner(System.in); // 136 bytes
        final String departureStop = scanner.nextLine().split("StopArea:")[1]; // 24 bytes
        final String arrivalStop = scanner.nextLine().split("StopArea:")[1]; // 24 bytes
        scanner.nextInt();
        scanner.nextLine();

        final HashMap<String, String[]> var6 = new HashMap<String, String[]>(); // 48 bytes
        String var5; // 24 bytes
        String[] scanner0;
        int index;

        for (index = 0; index < 1019; ++index) {
            var5 = scanner.nextLine();
            scanner0 = var5.split(",");
            String scanner2 = scanner0[0].split("StopArea:")[1];
            var6.put(scanner2, scanner0);
        }


        scanner.nextInt();
        scanner.nextLine();
        final Edge[] var20 = new Edge[2405];  // 9640 bytes

        String[] scanner3;
        for (index = 0; index < 2405; ++index) {
            var5 = scanner.nextLine();
            scanner3 = var5.split(" ");
            String scanner4 = scanner3[0].split("StopArea:")[1];
            String scanner5 = scanner3[1].split("StopArea:")[1];
            float scanner8 = distance((Float.parseFloat(var6.get(scanner4)[4])), Float.parseFloat((var6.get(scanner4))[3]), Float.parseFloat((var6.get(scanner5))[4]), Float.parseFloat((var6.get(scanner5))[3]));
            var20[index] = new Edge(scanner4, scanner5, (int) (scanner8 * 10000.0));
        }

        final graph var22 = new graph(var20); // 16 bytes
        graph.ListeKeyStation = var6; // 48 bytes
        var22.dijkstra(departureStop);
        var22.printPath(arrivalStop);
        out.println(System.currentTimeMillis() - debut);
    }

    public static float distance(float point1, float point2, float point3, float point4) {
        float var9 = blop * (point4 - point2);
        float cos = (float) Math.cos(var9);
        float var8 = blop * (point3 - point1) * cos / 2.0f; // 16 Bytes every time
        return (float) (Math.sqrt(var8 * var8 + var9 * var9) * 6371.0f);
    }
}
