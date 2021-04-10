package greedy.mst;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class dijkstra {
    static int INF = 10000000; // 전역 변수 선언


    public static int[] solve(int[][] graph, int start){

        int [] dist = new int [graph.length];
        Arrays.fill(dist, INF);

        dist[start] = 0;  //   [INF], [0], [INF], [INF] ,[INF], [INF], [INF]
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.add(new Node(0, start));

        while (!queue.isEmpty()){
            Node edge = queue.poll();  // 가장 적은놈을 빼서 edge에 저장
            int here = edge.loca;

            for (int i = 0; i<graph.length-1; i++){
                if (graph[here][i]!= INF){   // 이동 할 수 있는 노드라면
                    int there = i+1 ;  // 0번째부터 이기에 1 더해준다 (0은 인위적인 인덱스라)
                    int length = graph[here][i];
                    int next_dist = dist[here]+length;

                    if (next_dist<dist[there]){
                        dist[there] = next_dist;
                        queue.add(new Node(next_dist,there));
                    }
                }
            }
        }
        return dist;
    }

    public static class Node implements Comparable<Node> {
        int dist;
        int loca;

        public Node(int dist, int loca) {
            this.dist = dist;
            this.loca = loca;
        }


        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) {

        int [][] graph = {{  },  // 노드 번호로 직관적으로 참조하기위해 0번째는 그냥 인위적으로 생성
                {INF , 2, 3, 6, INF,INF},
                {INF,INF,INF,INF,INF,4},
                {INF,INF,INF,1,INF,INF},
                {INF,INF,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF}};

        int [] dist = solve(graph,1);
        for (int i = 1; i<dist.length; i++){   // 0번째는 필요없는 인위적인 index라 1부터 참조
            if (dist[i] != INF){
                System.out.printf("1번 노드에서 %d번 노드까지의 최단거리 : %d \n",i,dist[i]);
            }
            else {
                System.out.printf("1번 노드에서 %d번 노드까지의 최단거리 : INF(갈 수 없다) \n",i);
            }
        }
    }
}