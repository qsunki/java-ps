package coordinatecompression;

import java.io.*;
import java.util.*;

public class BOJ_18869_멀티버스II {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        Map<List<Integer>, Integer> rankPatterns = new HashMap<>();
        int[] planets = new int[n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                planets[j] = Integer.parseInt(st.nextToken());
            }
            int[] sortedPlanets = planets.clone();
            Arrays.sort(sortedPlanets);
            Map<Integer, Integer> rankMapper = new HashMap<>();
            int rank = 1;
            rankMapper.put(sortedPlanets[0], rank);
            for (int j = 1; j < n; ++j) {
                if (sortedPlanets[j] != planets[j - 1]) {
                    rank++;
                }
                rankMapper.put(sortedPlanets[j], rank);
            }
            List<Integer> rankPattern = new ArrayList<>(n);
            for (int planet : planets) {
                int index = rankMapper.get(planet);
                rankPattern.add(index);
            }
            rankPatterns.put(rankPattern, rankPatterns.getOrDefault(rankPattern, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<List<Integer>, Integer> entry : rankPatterns.entrySet()) {
            ans += entry.getValue() * (entry.getValue() - 1) / 2;
        }
        System.out.println(ans);
    }

}
