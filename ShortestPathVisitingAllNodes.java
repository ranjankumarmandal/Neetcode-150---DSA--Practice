import java.util.*;

class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int target = (1 << n) - 1;

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][1 << n];

        for (int i = 0; i < n; i++) {
            int mask = 1 << i;
            queue.offer(new int[]{i, mask});
            visited[i][mask] = true;
        }

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] state = queue.poll();
                int node = state[0];
                int mask = state[1];

                if (mask == target) {
                    return steps;
                }

                for (int next : graph[node]) {
                    int nextMask = mask | (1 << next);

                    if (!visited[next][nextMask]) {
                        visited[next][nextMask] = true;
                        queue.offer(new int[]{next, nextMask});
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}
