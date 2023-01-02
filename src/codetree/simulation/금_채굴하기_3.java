package codetree.simulation;

import java.util.Scanner;

public class 금_채굴하기_3 {
    static int n;
    static int m;
    static int[][] arr;

    public static int solution() {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                /* 풀이 3. 중복되는 경우 제외하여 효율적으로 탐색 */
                int goldCount = 0;
                for (int k = 0; k <= 2 * (n - 1); k++) {
                    goldCount += getGoldCountInBorder(i, j, k);
                    int miningCost = getMiningCost(k);
                    if (m * goldCount >= miningCost) {
                        answer = Math.max(answer, goldCount);
                    }
                }
            }
        }

        return answer;
    }

    public static int getMiningCost(int k) {
        return k * k + (k + 1) * (k + 1);
    }

    public static int getGoldCountInBorder(int x, int y, int k) {
        int count = 0;

        // 대각선 방향 정의
        int[] dx = {1, 1, -1, -1};
        int[] dy = {-1, 1, 1, -1};

        // k = 0일 때는 현재 위치만 확인
        if (k == 0) {
            return arr[x][y];
        }

        // 순회 시작점 설정
        int nx = x - k;
        int ny = y;

        for (int i = 0; i < 4; i++) {
            for (int step = 0; step < k; step++) {
                if (inRange(nx, ny)) {
                    count += arr[nx][ny];
                }
                nx += dx[i];
                ny += dy[i];
            }
        }

        return count;
    }

    public static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 1이상 20이하
        m = sc.nextInt(); // 1이상 10이하
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int answer = solution();
        System.out.println(answer);
    }
}
