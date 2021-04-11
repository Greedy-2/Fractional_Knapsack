import java.util.*;

public class Fractional_Knapsack {
    static class BackPack implements Comparable<BackPack>{
        String name;
        double w;
        double v;


        public BackPack(String name, double w, double v){
            this.name = name;
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(BackPack o) {
           return (int)(o.v - this.v);
        } ////주석, 은 정렬 못함 -> 소수점 정렬 x

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
            L.add(x.name + " " + (c - total_w) + "g");
            total_v = total_v + (c - total_w) * ((int) x.v / (int) x.w);
            //(C-total_w)만큼의 가치만 total_v에 넣어야해서 (C-total_w)에 x의 단위 무게당 가치를 곱해야함
        }

        System.out.println(L);
        System.out.printf("총가치는 %d만원",total_v);
    }
}
