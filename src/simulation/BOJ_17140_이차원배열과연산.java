package simulation;

import java.io.*;
import java.util.*;

public class BOJ_17140_이차원배열과연산 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int r, c, k;
    static int rSize, cSize;
    static Map<Integer, Integer> counter = new HashMap<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        rSize = 3;
        cSize = 3;
        int[][] arr = new int[100][100];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = simulate(arr);
        System.out.println(ans);
    }

    static int simulate(int[][] arr) {
        int time = 0;
        while (time <= 100) {
            if (r <= rSize && c <= cSize && arr[r - 1][c - 1] == k) {
                return time;
            }
//            System.out.println("time: " + (time + 1));
            if (rSize >= cSize) { // r연산
//                System.out.println("r operate");
//                System.out.println("r: " + rSize + " c: " + cSize + "");
                int tmpMaxCSize = 0;
                for (int i = 0; i < rSize; i++) {
                    counter.clear();
                    for (int j = 0; j < cSize; j++) {
                        if (arr[i][j] == 0) {
                            continue;
                        }
                        counter.put(arr[i][j], counter.getOrDefault(arr[i][j], 0) + 1);
                    }
                    int tmpCSize = counter.size() * 2;
                    if (tmpCSize > 100) {
                        tmpCSize = 100;
                    }
                    tmpMaxCSize = Math.max(tmpMaxCSize, tmpCSize);
                    Map.Entry<Integer, Integer>[] entries = counter.entrySet().toArray(new Map.Entry[0]);
                    Arrays.sort(entries, (e1, e2) -> {
                        int comp = Integer.compare(e1.getValue(), e2.getValue());
                        if (comp == 0) {
                            return Integer.compare(e1.getKey(), e2.getKey());
                        }
                        return comp;
                    });
                    int j = 0;
                    for (Map.Entry<Integer, Integer> entry : entries) {
                        arr[i][j++] = entry.getKey();
                        arr[i][j++] = entry.getValue();
                        if (j >= tmpCSize) {
                            break;
                        }
                    }
                    while (j < 100) {
                        arr[i][j++] = 0;
                    }
                }
                cSize = tmpMaxCSize;
            } else { // c연산
//                System.out.println("c operate");
//                System.out.println("r: " + rSize + " c: " + cSize + "");
                int tmpMaxRSize = 0;
                for (int j = 0; j < cSize; j++) {
                    counter.clear();
                    for (int i = 0; i < rSize; i++) {
                        if (arr[i][j] == 0) {
                            continue;
                        }
                        counter.put(arr[i][j], counter.getOrDefault(arr[i][j], 0) + 1);
                    }
                    int tmpRSize = counter.size() * 2;
                    if (tmpRSize > 100) {
                        tmpRSize = 100;
                    }
                    tmpMaxRSize = Math.max(tmpMaxRSize, tmpRSize);
                    Map.Entry<Integer, Integer>[] entries = counter.entrySet().toArray(new Map.Entry[0]);
                    Arrays.sort(entries, (e1, e2) -> {
                        int comp = Integer.compare(e1.getValue(), e2.getValue());
                        if (comp == 0) {
                            return Integer.compare(e1.getKey(), e2.getKey());
                        }
                        return comp;
                    });
                    int i = 0;
                    for (Map.Entry<Integer, Integer> entry : entries) {
                        arr[i++][j] = entry.getKey();
                        arr[i++][j] = entry.getValue();
                        if (i >= tmpRSize) {
                            break;
                        }
                    }
                    while (i < 100) {
                        arr[i++][j] = 0;
                    }
                }
                rSize = tmpMaxRSize;
            }
//            printArr(arr);
            ++time;
        }
        return -1;
    }

    static void printArr(int[][] arr) {
        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < cSize; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
