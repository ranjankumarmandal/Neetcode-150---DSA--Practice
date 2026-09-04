import java.util.*;

class ShortestPathToGetAllKeys_Graph {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();

        int sr = 0, sc = 0, keyCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);

                if (c == '@') {
                    sr = i;
                    sc = j;
                } else if (c >= 'a' && c <= 'f') {
                    keyCount++;
                }
            }
        }

        int target = (1 << keyCount) - 1;
        boolean[][][] visited = new boolean[m][n][1 << keyCount];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc, 0});
        visited[sr][sc][0] = true;

        int[][] dirs = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                int keys = cur[2];

                if (keys == target) {
                    return steps;
                }

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    char cell = grid[nr].charAt(nc);

                    if (cell == '#') {
                        continue;
                    }

                    if (cell >= 'A' && cell <= 'F') {
                        int requiredKey = 1 << (cell - 'A');

                        if ((keys & requiredKey) == 0) {
                            continue;
                        }
                    }

                    int newKeys = keys;

                    if (cell >= 'a' && cell <= 'f') {
                        newKeys |= 1 << (cell - 'a');
                    }

                    if (!visited[nr][nc][newKeys]) {
                        visited[nr][nc][newKeys] = true;
                        queue.offer(new int[]{nr, nc, newKeys});
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}