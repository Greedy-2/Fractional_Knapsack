##### Greedy2

# 과제: 그리디 알고리즘 자유주제



### 1. 부분 배낭 문제
![Fractional_Kanpsack](https://dudri63.github.io/image/algo14-1.png)

배낭(Knapsack) 문제는 n개의 물건이 있고, 각 물건은 무게와 가치를 가지고 있을 때, 최대의 가치를 갖도록 한정된 용량의 배낭에 넣을 물건들을 정하는 문제이다.
원래의 배낭 문제는 물건을 통째로 배낭에 넣어야 하는 것에 반해,
부분 배낭(Fractional Knapsack) 문제는 물건을 부분적으로 담는 것이 허용 된다.

부분 배낭 문제에서는 물건을 부분적으로 배낭에 담을 수 있으므로, 최적해을 위해서 ‘욕심을
내어’ 단위 무게당 가장 값나가는 물건을 배낭에 넣고, 계속해서 그 다음으로 값나가는 물건
을 넣는다. 그런데 만일 그 다음으로 값나가는 물건을 ‘통째로’ 배낭에 넣을 수 없게 되면, 배낭
에 넣을 수 있을 만큼만 물건을 부분적으로 배낭에 담도록 한다.


### 2. 알고리즘

FractionalKnapsack
입력: n개의 물건, 각 물건의 무게와 가치, 배낭의 용량 C
출력: 배낭에 담은 물건 리스트 L과 배낭에 담은 물건 가치의 합 v

1. 각 물건에 대해 단위 무게 당 가치를 계산한다.
2. 물건들을 단위 무게 당 가치를 기준으로 내림차순으로 정렬하고, 정렬된 물건 리스트를 S라고 하자.
3. L=∅, w=0, v=0 
// L은 배낭에 담을 물건 리스트, w는 배낭에 담긴 물건들의 무게의 합, v는 배낭에 담긴 물건들의 가치의 합
4. S에서 단위 무게 당 가치가 가장 큰 물건 x를 가져온다
5. while ( (w+x의 무게) ≤ C ) { 
6.      x를 L에 추가시킨다.
7.      w = w+x의 무게
8.      v = v+x의 가치
9.      x를 S에서 제거한다.
10.	S에서 단위 무게 당 가치가 가장 큰 물건 x를 가져온다.
     }
11. if ((C-w) > 0) { // 배낭에 물건을 부분적으로 담을 여유가 있으면 
12. 	물건 x를 (C-w)만큼만 L에 추가한다. 
13. 	v = v +(C-w)만큼의 x의 가치
      }
14. return L, v


### 3. 시간복잡도

Line 1
n개의 물건 각각의 단위 무게 당 가치를 계산하는 데는 O(n) 시간 걸린다.
Line 2
물건의 단위 무게 당 가치에 대해서 내림차순으로 정렬하기 위해 O(nlogn) 시간이 걸린다.
Line 5-10
while-루프의 수행은 n번을 넘지 않으며, 루프 내부의 수행은 O(1) 시간이 걸린다. 
Line 11-14
각각 O(1) 시간 걸린다.

알고리즘의 시간복잡도는 O(n)+O(nlogn)+nxO(1)+O(1) = O(nlogn)이다.


### 4. 알고리즘 구현

```java
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
        System.out.printf("총가치는 %d원", total_v);
    }


}
```


### 5. 소스코드 설명

```java

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
```
물건을 넣기전이므로 먼저 가방 무게와 가치를 0으로 초기화 시키고 가치가 높은순으로 정렬되어 있는 리스트 중 첫번째 값들을 가져온다. ( ex) 백금 10g 6(만원) )
while문은 현재 가방무게와 집어넣은 무게값이 가방한도를 넘지 않으면 무한반복한다. 가방 리스트 L에 ( 백금 10g )를 추가시킨다. 현재 가방무게와 현재 가치
값들에도 들어온 값만큼 변화를 준다. ( w = 0+10, v = 0+10x6(60) )  그 후 다음으로 가치가 높은 리스틀 가져온다 ( 금 15g 5(만원) ) 리스트 L ( 백금 10g, 금 15g)
( w =10 + 15, v = 60 + 15x5(75) ). 다음 리스트는 ( 은 25g 4(천원) ). w = 10 + 15 + 25 < 40 의 조건문이 false가 되므로 반복문을 탈출한다.




### 6. 결과








### 7. 참조 

양성봉, 『알기 쉬운 알고리즘』. 파주: (주)생능출판사, 2013




