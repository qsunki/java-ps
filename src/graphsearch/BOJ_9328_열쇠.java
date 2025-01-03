package graphsearch;

import java.io.*;
import java.util.*;

public class BOJ_9328_열쇠 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    /**
     * 1. 가능한 입구 Queue에 넣기
     * 2. bfs 순회하면서 문서 찾기
     * 2-1. 열쇠 획득 시 boolean[] 체크
     * 3. 대문자 만났을 때
     * 3-1. 열쇠 있으면 계속 순회
     * 3-2. 열쇠 없으면 별도 Queue에 넣고 순회
     * 3-3. 순회 끝났으면 대문자 Queue에서 다시 순회
     * 3-4. 획득한 열쇠가 없으면 종료
     */
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        char[][] building = new char[101][];
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            for (int i = 0; i < h; i++) {
                building[i] = br.readLine().toCharArray();
            }
            boolean[] key = new boolean[26];
            for (char c : br.readLine().toCharArray()) {
                if (c == '0') {
                    break;
                }
                key[c - 'a'] = true;
            }
            Queue<Node> queue = new ArrayDeque<>();
            Queue<Node> locked = new ArrayDeque<>();
            boolean[][] visited = new boolean[h][w];
            int docFound = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!isEdge(i, j, h, w)) {
                        continue;
                    }
                    if (isWall(building[i][j])) {
                        continue;
                    }
                    visited[i][j] = true;
                    if (isDoor(building[i][j])) {
                        locked.offer(new Node(i, j, building[i][j]));
                        continue;
                    }
                    if (isKey(building[i][j])) {
                        addKey(key, building[i][j]);
                    }
                    if (isDoc(building[i][j])) {
                        ++docFound;
                    }
                    queue.offer(new Node(i, j, building[i][j]));
                }
            }
            int lockedSize = locked.size();
            for (int i = 0; i < lockedSize; i++) {
                Node poll = locked.poll();
                if (hasKey(key, poll.c)) {
                    queue.offer(poll);
                } else {
                    locked.offer(poll);
                }
            }
            docFound += bfs(building, key, queue, h, w, locked, visited);
            sb.append(docFound).append("\n");
        }

        System.out.println(sb);
    }

    static int bfs(char[][] building, boolean[] key, Queue<Node> queue, int h, int w, Queue<Node> locked, boolean[][] visited) {
        int docFoundCnt = 0;
        while (true) {
            int keyFoundCnt = 0;
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = node.x + dx[i];
                    int ny = node.y + dy[i];
                    if (isNotValid(nx, ny, h, w) || visited[nx][ny]) {
                        continue;
                    }
                    if (isWall(building[nx][ny])) {
                        continue;
                    }
                    if (isDoor(building[nx][ny]) && !hasKey(key, building[nx][ny])) {
                        locked.offer(new Node(nx, ny, building[nx][ny]));
                        continue;
                    }
                    if (isKey(building[nx][ny])) {
                        ++keyFoundCnt;
                        addKey(key, building[nx][ny]);
                    }
                    if (isDoc(building[nx][ny])) {
                        ++docFoundCnt;
                    }
                    visited[nx][ny] = true;
                    queue.offer(new Node(nx, ny, building[nx][ny]));
                }
            }
            if (keyFoundCnt == 0) {
                break;
            }
            int size = locked.size();
            for (int i = 0; i < size; i++) {
                Node poll = locked.poll();
                if (hasKey(key, poll.c)) {
                    queue.offer(poll);
                } else {
                    locked.offer(poll);
                }
            }
        }
        return docFoundCnt;
    }

    static boolean isNotValid(int x, int y, int h, int w) {
        return x < 0 || y < 0 || x >= h || y >= w;
    }

    static boolean isDoor(char c) {
        return c >= 'A' && c <= 'Z';
    }

    static boolean isKey(char c) {
        return c >= 'a' && c <= 'z';
    }

    static boolean hasKey(boolean[] key, char c) {
        return key[c - 'A'];
    }

    static boolean isEmpty(char c) {
        return c == '.';
    }

    static boolean isWall(char c) {
        return c == '*';
    }

    static boolean isDoc(char c) {
        return c == '$';
    }

    static void addKey(boolean[] key, char c) {
        key[c - 'a'] = true;
    }

    private static boolean isEdge(int x, int y, int h, int w) {
        return x == 0 || y == 0 || x == h - 1 || y == w - 1;
    }

    static class Node {
        int x, y;
        char c;

        public Node(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}
