package backtracking;

import java.io.*;
import java.util.*;

public class BOJ_16987_계란으로계란치기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Egg[] eggs = new Egg[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(s, w, false);
        }
        eggToEgg(eggs, 0, 0);
        System.out.println(ans);
    }

    static void eggToEgg(Egg[] eggs, int idx, int brokenCnt) {
        if (idx == eggs.length) {
            ans = Math.max(ans, brokenCnt);
            return;
        }
        if (eggs[idx].broken) {
            eggToEgg(eggs, idx + 1, brokenCnt);
            return;
        }
        boolean flag = false;
        for (Egg egg : eggs) {
            if (eggs[idx] == egg) {
                continue;
            }
            if (egg.broken) {
                continue;
            }
            flag = true;
            eggs[idx].s -= egg.w;
            if (eggs[idx].s <= 0) {
                ++brokenCnt;
                eggs[idx].broken = true;
            }
            egg.s -= eggs[idx].w;
            if (egg.s <= 0) {
                ++brokenCnt;
                egg.broken = true;
            }
            eggToEgg(eggs, idx + 1, brokenCnt);
            if (eggs[idx].s <= 0) {
                --brokenCnt;
                eggs[idx].broken = false;
            }
            if (egg.s <= 0) {
                --brokenCnt;
                egg.broken = false;
            }
            eggs[idx].s += egg.w;
            egg.s += eggs[idx].w;
        }
        if (!flag) {
            eggToEgg(eggs, idx + 1, brokenCnt);
        }
    }


    static class Egg {
        int s, w;
        boolean broken;

        Egg(int s, int w, boolean broken) {
            this.s = s;
            this.w = w;
            this.broken = broken;
        }
    }

}
