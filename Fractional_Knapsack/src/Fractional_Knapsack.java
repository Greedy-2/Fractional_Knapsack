import java.util.*;

public class Fractional_Knapsack {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n;  // 물건 종류의 갯수
        int c;  // 배낭의 용량
        n = sc.nextInt();
        c = sc.nextInt();
        ArrayList <Thing> S = new ArrayList<>();  // Thing 자료형을 쓰는 Arraylist생성
        for(int i=0; i<n; i++) {
            String name; int w; int v;  // 각 물건의 종류,무게,가치
            name = sc.next(); w = sc.nextInt(); v = sc.nextInt();
            S.add(new Thing(name, w, v/w));    // 각 물건의 종류,무게,무게당 가치 삽입
        }
        int startTime = (int) System.currentTimeMillis(); // 시작 시간

        Collections.sort(S); // compareTo 기준으로 정렬
        new Fractional_Knapsack(S, c,n);    // 함수 실행

        int endTime = (int) System.currentTimeMillis();// 종료 시간
        int processTime = endTime - startTime; // 차이
        System.out.printf("수행 시간: %d sec, %d ms", processTime / 1000, processTime % 1000);


    }

    static class Thing implements Comparable<Thing>{
        String name;
        int w;
        int v;

        public Thing(String name, int w, int v){
            this.name = name;
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Thing o) {
            return o.v-this.v;
        }  // 무게당 가치기준으로 내림차순

        @Override
        public String toString() {
            return name+" "+w+" "+v;
        }
    }
    public Fractional_Knapsack(ArrayList S, int c, int n) {

        int total_w = 0;    // 배낭에 들어간 물건들의 무게
        int total_v = 0;    // 배낭에 들어간 물건들의 가치
        Thing x = (Thing) S.get(0);     // 정렬된 리스트에서 가장 앞의 index를 가져옴
        int i = 0;
        ArrayList L = new ArrayList<>();    // 추가된 물건을 삽입할 리스트 생성

        while (total_w+(int)x.w <= c) {     // 총 용랑의 현재의 들어간 용량과 들어갈 물건의 용량보다 작거나 같으면 반복
            L.add(x.name+" "+x.w+"g");
            total_w = total_w+ (int) x.w;
            total_v = total_v + ((int)x.w * (int)x.v);

            i += 1;
            if (i < n) {    // 만약 i와 n이 같아지면 인덱스 초과로 오류발생함으로 조건문 생성
                x = (Thing) S.get(i);
            }

        }
        if (c > total_w) {  // while문을 나와서도 현재까지 배낭에 담은 물건들의 무게가 배낭의 용량 C 보다 작으면
            L.add(x.name+" "+(c-total_w)+"g");
            total_v = total_v + (c - total_w) * (int)x.v;
        }
        System.out.println(L);
        System.out.printf("총가치는 %d원\n",total_v);
    }
}
