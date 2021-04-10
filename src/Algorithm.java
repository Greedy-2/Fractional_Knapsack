import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Algorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int C = 15; //이거 크게
            int weight;
            int value;




            int w = 0;
            for (weight = 1; weight <= n; weight++) {
                w += weight;
            }

            int v = 0;
            for (value = 2;value <= n; value++) {
                v += value;
            }
        System.out.println(w);
        System.out.println(v);




            List<Integer> L = new ArrayList<Integer>();
            L.add(5);
            L.add(7);

            if((C-w) > 0){
                L.add(C-w);//x를 C-w 만큼만 추가       x - (x % (C-w))??
                v = v + (C - w) * value / weight; // x의 value / weight로 바꾸기
            }

            System.out.println("배낭에 들어 있는 물건: " + L.toString());
            System.out.printf("배낭에 들어있는 가치의 합: %d" , v);
        }
    }



















/*
책에 나온 변수들

 C = capacity
 w = total weight
 L = list of thing
 v = total value
 x = 리스트 S에서 단위 무게당 가치(weight/value)가 가장 많이 나가는 물건



 return L, v


 11. if ((C-w) > 0){ //배낭이 꽉차지 않았다면
        L.add(x-(x % (C-w)));
        x = x % (C - w);
        v =


 }

 */




