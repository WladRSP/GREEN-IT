//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Graph.Graph;
import Graph.Graph.Edge;
import java.util.HashMap;
import java.util.Scanner;

class Solution {
    Solution() {
    }

    public static void main(String[] var0) {
        Scanner scanner = new Scanner(System.in);
        String var2 = scanner.nextLine().split("StopArea:")[1];
        String var3 = scanner.nextLine().split("StopArea:")[1];
        int var4 = scanner.nextInt();
        scanner.nextLine();
        if(var4 > 10000) {
            var4 = 10000;
        }

        HashMap var6 = new HashMap();
        HashMap var7 = new HashMap();
        int var8 = 0;

        String var5;
        int var9;
        for(var9 = 0; var9 < var4; ++var9) {
            String[] scanner0 = new String[9];
            var5 = scanner.nextLine();
            scanner0 = var5.split(",");
            String[] scanner1 = new String[9];
            String scanner2 = scanner0[0].split("StopArea:")[1];
            var6.put(scanner2, scanner0);
            var7.put(scanner2, new Integer(var8));
            ++var8;
        }

        var9 = scanner.nextInt();
        scanner.nextLine();
        new HashMap();
        Edge[] var20 = new Edge[var9];

        for(int var21 = 0; var21 < var9; ++var21) {
            String[] scanner3 = new String[2];
            var5 = scanner.nextLine();
            scanner3 = var5.split(" ");
            String scanner4 = scanner3[0].split("StopArea:")[1];
            String scanner5 = scanner3[1].split("StopArea:")[1];
            int scanner6 = ((Integer)var7.get(scanner4)).intValue();
            int scanner7 = ((Integer)var7.get(scanner5)).intValue();
            double scanner8 = distance(((String[])var6.get(scanner4))[4], ((String[])var6.get(scanner4))[3], ((String[])var6.get(scanner5))[4], ((String[])var6.get(scanner5))[3]).doubleValue();
            var20[var21] = new Edge(scanner4, scanner5, (int)(scanner8 * 10000.0D));
        }

        Graph var22 = new Graph(var20);
        Graph.ListeKeyStation = var6;
        var22.dijkstra(var2);
        var22.printPath(var3);
    }

    public static Double distance(String var0, String scanner, String var2, String var3) {
        Double var4 = Double.valueOf(Double.valueOf(var0).doubleValue() * 3.141592653589793D / 180.0D);
        Double var5 = Double.valueOf(Double.valueOf(var2).doubleValue() * 3.141592653589793D / 180.0D);
        Double var6 = Double.valueOf(Double.valueOf(scanner).doubleValue() * 3.141592653589793D / 180.0D);
        Double var7 = Double.valueOf(Double.valueOf(var3).doubleValue() * 3.141592653589793D / 180.0D);
        Double var8 = Double.valueOf((var5.doubleValue() - var4.doubleValue()) * Math.cos((var6.doubleValue() + var7.doubleValue()) / 2.0D));
        Double var9 = Double.valueOf(var7.doubleValue() - var6.doubleValue());
        Double scanner0 = Double.valueOf(Math.sqrt(var8.doubleValue() * var8.doubleValue() + var9.doubleValue() * var9.doubleValue()) * 6371.0D);
        return scanner0;
    }
}
