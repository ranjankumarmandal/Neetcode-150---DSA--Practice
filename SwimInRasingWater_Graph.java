import java.util.*;

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[0])
        );

        pq.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0];
            int r = curr[1];
            int c = curr[2];

            if (r == n - 1 && c == n - 1) {
                return time;
            }

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    pq.offer(new int[]{
                            Math.max(time, grid[nr][nc]),
                            nr,
                            nc
                    });
                }
            }
        }

        return -1;
    }
}
