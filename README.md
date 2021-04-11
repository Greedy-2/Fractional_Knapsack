# Fractional_Knapsack




## 1. 부분 배낭 문제
![Fractional_Kanpsack](https://dudri63.github.io/image/algo14-1.png)

배낭(Knapsack) 문제는 n개의 물건이 있고, 각 물건은 무게와 가치를 가지고 있을 때, 최대의 가치를 갖도록 한정된 용량의 배낭에 넣을 물건들을 정하는 문제이다.
원래의 배낭 문제는 물건을 통째로 배낭에 넣어야 하는 것에 반해,
부분 배낭(Fractional Knapsack) 문제는 물건을 부분적으로 담는 것이 허용 된다.

부분 배낭 문제에서는 물건을 부분적으로 배낭에 담을 수 있으므로, 최적해을 위해서 ‘욕심을
내어’ 단위 무게당 가장 값나가는 물건을 배낭에 넣고, 계속해서 그 다음으로 값나가는 물건
을 넣는다. 그런데 만일 그 다음으로 값나가는 물건을 ‘통째로’ 배낭에 넣을 수 없게 되면, 배낭
에 넣을 수 있을 만큼만 물건을 부분적으로 배낭에 담도록 한다.


## 2. 알고리즘
```
FractionalKnapsack
입력: n개의 물건, 각 물건의 무게와 가치, 배낭의 용량 C
출력: 배낭에 담은 물건 리스트 L과 배낭에 담은 물건 가치의 합 v

1.   각 물건에 대해 단위 무게 당 가치를 계산한다.
2.   물건들을 단위 무게 당 가치를 기준으로 내림차순으로 정렬하고, 정렬된 물건 리스트를 S라고 하자.
3.   L=∅, w=0, v=0 
// L은 배낭에 담을 물건 리스트, w는 배낭에 담긴 물건들의 무게의 합, v는 배낭에 담긴 물건들의 가치의 합
4.   S에서 단위 무게 당 가치가 가장 큰 물건 x를 가져온다.


5.   while ( (w+x의 무게) ≤ C ) { 
6.   x를 L에 추가시킨다.
7.   w = w+x의 무게
8.   v = v+x의 가치
9.   x를 S에서 제거한다.
10.  S에서 단위 무게 당 가치가 가장 큰 물건 x를 가져온다.
     }
     

11.  if ((C-w) > 0) { // 배낭에 물건을 부분적으로 담을 여유가 있으면 
12. 	물건 x를 (C-w)만큼만 L에 추가한다. 
13. 	v = v +(C-w)만큼의 x의 가치
      }
14.  return L, v
```



## 3. 구현하기


```java
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

```

### main 메소드

```java
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
        Collections.sort(S); // compareTo 기준으로 정렬
        new Fractional_Knapsack(S, c,n);    // 함수 실행
   }
```

`n`은 물건 종류의 갯수이고 `c`는 배낭에 들어갈 수 있는 용량이다.
`Thing`클래스를 자료형으로 가지는 `S` arraylist를 만들어서
`S`안에 각 물건의 이름 , 무게 , 가치를 하는데 가치를 넣을 땐
입력받는 값은 총가치이므로 arraylist에 넣을땐 단위무게당 가치를 넣어야 하므로 `v / w`를 넣는다.
`Collection.sort()`는  `Thing`클래스에서 구현한 `compareTo`메소드로 인해
`S`의 배열은 물건들을 단위무게당 가치를 기준으로 내림차순으로 정렬한다.


### 알고리즘 1 -4

```java
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
   
```
물건들의 정보를 저장할 `Thing`클래스를 만든다.
변수에는 물건 이름인 `name`, 무게 `w`, 가치 `v`
그리고 `this.`을 이용해 참조가 가능하도록 만든다.
`compareTo`를 이용해 이 클래스를 가진 리스트를 정렬할 때
어떤 변수 기준으로 오름차순 또는 내림차순으로 할지 결정한다.
여기서는 가치 값을 내림차순으로 정했다 그리고 `toString`함수는 
이 클래스를 자료형으로 사용한 리스트를 출력하면 값이 알아볼 수 없는 형태로
출력되어서 어떤 식으로 출력할지 따로 정해준것이다. 
사실 이 코드에서 사용은 안 되지만 코드를 짜는 중간에 확인할때 사용하기위해 넣었다.


### 알고리즘 5-10

```java
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
```
 `total_w`, `total_v`는 각각 가방에 넣을 물건들의 무게의 합과 가치의 합이다. 이는 물건을 넣기전이므로 `total_w`와 `total_v`를 0으로 초기화 시키고 
배열 `S`는 단위무게당 가치를 기준으로 내림차순 정렬되어있기 때문에 배열 단위무게당 가치가 가장 큰 물건 즉, 첫번째값들(`name`, `w`, `v / w`)을 물건`x`라고 한다.
while문은 현재 가방무게와 집어넣은 무게값이 가방한도를 넘지 않으면 무한반복한다. 
가방 리스트 `L`를 생성하고 여기에 조건을 while문을 통과한 물건`x`를 추가시킨다. 
현재 가방무게와 현재 가치 값들에도 들어온 값만큼 변화를 준다. 먼저 가장 가치가 높은 `x` 가져온다. ( 백금 10g 60(만원) )
리스트 L ( 백금 10g ) ( w = 0+10, v = 0+10x6(60) ).
그리고 `L`에 추가된 `x`를 `S`에서 제거한다.
다음으로 가치가 높은 `x` 가져온다. ( 금 15g 5(만원) ) 
리스트 L ( 백금 10g, 금 15g) ( w =10 + 15, v = 60 + 15x5(75) ).
다음 리스트는 ( 은 25g 4(천원) ). w = 10 + 15 + 25 < 40 의 조건문이 false가 되므로 반복문을 탈출한다.


### 알고리즘 11-14

```java
if (c > total_w) {  // while문을 나와서도 현재까지 배낭에 담은 물건들의 무게가 배낭의 용량 C 보다 작으면
            L.add(x.name+" "+(c-total_w)+"g");
            total_v = total_v + (c - total_w) * (int)x.v;
        }
        System.out.println(L);
        System.out.printf("총가치는 %d원\n",total_v);
```

 while 문을 통과한 후에도 가방 용량`C`에 남은 공간이 있다면 `S`에 남아있는 물건 중 단위무게당 가치가 가장 높은 `x`를 남아있는 공간(`C-total_w`)만큼 쪼개서 담아야한다.
리스트 `L`에 `x`의 무게를 (C-total_w)만큼 담고 `total_v`에는 `x`의 (C-total_w)g만큼의 가치를 추가하기 위해서 `total_v`에 `(C-total_w) * x.v / x.w`만큼 추가한다. 
따라서 `total_v`의 최종 값은 `total_v + (C-total_w) * (x.v / x.w)`이다.

## 4. 실행결과

![](https://postfiles.pstatic.net/MjAyMTA0MTFfMTU0/MDAxNjE4MTM1NDA4Njc5.G11CKR6Sej30H90elIn7alRl-HqnKRoB2lyyB9QLcLMg.3l1JXhWnQ1oTTZy9S81WKgK3dTT7PCEONBZiYuM-IdAg.PNG.codnjs060/image.png?type=w773)

 물건의 개수는 4개, 가방의 용량은 40g으로 입력하고 주석 50g 50000원, 은 25g 100000원, 백금 10g 600000원, 금 15g 750000를 입력했을 때 
리스트 `L`에 백금과 금은 입력한 무게만큼 다 저장되고 가방의 남은 용량만큼 은이 15g들어간다.
이때, 가방에 든 물건들의 가치의 합은 600000 + 750000 + 60000(은 15g의 가치)로 1410000이된다.

## 5. 시간 복잡도


Line 1
n개의 물건 각각의 단위 무게 당 가치를 계산하는 데는 O(n) 시간 걸린다.
Line 2
물건의 단위 무게 당 가치에 대해서 내림차순으로 정렬하기 위해 O(nlogn) 시간이 걸린다.
Line 5-10
while-루프의 수행은 n번을 넘지 않으며, 루프 내부의 수행은 O(1) 시간이 걸린다. 
Line 11-14
각각 O(1) 시간 걸린다.

이 알고리즘의 시간복잡도는 O(n)+O(nlogn)+nxO(1)+O(1) = O(nlogn)이다.
따라서 위의 예시처럼 4개의 물건을 입력했을때의 시간 복잡도는 4log4이다.


## 6.참조 

양성봉, 『알기 쉬운 알고리즘』. 파주: (주)생능출판사, 2013







        
        

