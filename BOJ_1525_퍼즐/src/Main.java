import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int ansState = 987654321;
    static int inputState;
    static int startX;
    static int startY;
    static int ans;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        int weight = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int input = nextInt();
                if (input == 0) {
                    input = 9;
                    startX = i;
                    startY = j;
                }
                inputState += input * weight;
                weight *= 10;
            }
        }
        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        if (inputState == ansState) {
            return;
        }
        Map<Integer, Boolean> visited = new HashMap<>();
        Queue<Node> q = new ArrayDeque<>();
        visited.put(inputState, true);
        q.add(new Node(inputState, startX, startY, 0));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int nCnt = cur.cnt + 1;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isNotValid(nx, ny)) {
                    continue;
                }
                int nextState = getNextState(cur, nx, ny);
                if (visited.containsKey(nextState)) {
                    continue;
                }
                if (nextState == ansState) {
                    ans = nCnt;
                    return;
                }
                visited.put(nextState, true);
                q.add(new Node(nextState, nx, ny, nCnt));
            }
        }
        ans = -1;
    }

    static int getNextState(Node cur, int nx, int ny) {
        int curLoc = cur.x * 3 + cur.y;
        int w1 = (int) Math.pow(10, curLoc);
        int curNum = (cur.state / w1) % 10;
        int nextLoc = nx * 3 + ny;
        int w2 = (int) Math.pow(10, nextLoc);
        int nextNum = (cur.state / w2) % 10;
        return cur.state - curNum * w1 + nextNum * w1 - nextNum * w2 + curNum * w2;
    }

    static boolean isNotValid(int x, int y) {
        return x < 0 || y < 0 || x >= 3 || y >= 3;
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static class Node {
        int state;
        int x;
        int y;
        int cnt;

        Node(int state, int x, int y, int cnt) {
            this.state = state;
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
