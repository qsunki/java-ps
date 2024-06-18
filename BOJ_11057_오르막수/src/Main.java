import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] dp = new int[10];

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        dp[0] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j < 10; j++) {
                dp[j] = (dp[j] + dp[j - 1]) % 10007;
            }
        }
        System.out.println(dp[9]);
    }

}
