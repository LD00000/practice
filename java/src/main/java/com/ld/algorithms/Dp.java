package com.ld.algorithms;

/**
 * 动态规划
 *
 * @author lidong9144@163.com 17-7-4.
 */
public class Dp {

    public static void main(String[] args) {
        Dp dp = new Dp();
//        dp.dp1(new int[]{2, 3, 4}, 50);
        int [][] dp2d = new int[][]{new int[]{1,2,3}, new int[]{1,1,1}};
        dp.dp2(dp2d, 2, 3);
    }

    public void dp1(int[] penny, int aim) {
        int n = penny.length;

        int[][] pd = new int[n][aim + 1];
        for (int i = 0; i < n; i++) {
            pd[i][0] = 1;
        }
        for (int i = 1; penny[0] * i <= aim; i++) {
            pd[0][penny[0] * i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= aim; j++) {
                if (j >= penny[i]) {
                    pd[i][j] = pd[i - 1][j] + pd[i][j - penny[i]];
                } else {
                    pd[i][j] = pd[i - 1][j];
                }
            }
        }


        for (int j = 0; j < aim + 1; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        for (int j = 0; j < aim + 1; j++) {
            System.out.print("- ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < aim + 1; j++) {
                System.out.print(pd[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dp2(int[][] map, int n, int m) {
        int[][] dp = new int[n][m];

        dp[0][0] = map[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + map[i][0];
        }
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + map[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                int a = dp[i - 1][j];
                int b = dp[i][j - 1];
                if (a > b) {
                    dp[i][j] = b + map[i][j];
                } else {
                    dp[i][j] = a + map[i][j];
                }
            }
        }


        for (int j = 0; j < n; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        for (int j = 0; j < n; j++) {
            System.out.print("- ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dp3(int n) {

    }

}
