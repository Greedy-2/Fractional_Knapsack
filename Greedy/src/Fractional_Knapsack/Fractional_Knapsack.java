package Fractional_Knapsack;

import java.util.*;

public class Fractional_Knapsack {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n;
        int c;
        n = sc.nextInt();
        c = sc.nextInt();
        ArrayList <BackPack> S = new ArrayList<>();
        for(int i=0; i<n; i++) {
            String name; int w; int v;
            name = sc.next(); w = sc.nextInt(); v = sc.nextInt();
            S.add(new BackPack(name, w, v/w));
        }
        System.out.println(S.toString());
        Collections.sort(S);
        System.out.println(S.toString());
        new Fractional_Knapsack(S,c);
    }

    static class BackPack implements Comparable<BackPack>{
        String name;
        int w;
        int v;

        public BackPack(String name, int w, int v){
            this.name = name;
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(BackPack o) {
            return o.v-this.v;
        }

        @Override
        public String toString() {
            return name+" "+w+" "+v;
        }
    }
    public Fractional_Knapsack(ArrayList S, int c) {

        int total_w = 0;
        int total_v = 0;
        BackPack x = (BackPack) S.get(0);
        int i = 0;
        ArrayList L = new ArrayList<>();

        while (total_w+(int)x.w <= c) {
            L.add(x.name+" "+x.w+"g");
            total_w = total_w+ (int) x.w;
            total_v = total_v + ((int)x.w * (int)x.v);
            i += 1;
            x =(BackPack) S.get(i);
        }
        if (c >total_w) {
            L.add(x.name+" "+(c-total_w)+"g");

            total_v = total_v + (c - total_w) * (int)x.v;
        }
        System.out.println(L);
        System.out.printf("총가치는 %dg",total_v);
    }


}
