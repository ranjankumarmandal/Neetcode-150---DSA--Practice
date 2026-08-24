import java.util.*;

class Solution {
    public String foreignDictionary(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i];
            String b = words[i + 1];

            int len = Math.min(a.length(), b.length());

            if (a.length() > b.length() && a.substring(0, len).equals(b)) {
                return "";
            }

            for (int j = 0; j < len; j++) {
                char c1 = a.charAt(j);
                char c2 = b.charAt(j);

                if (c1 != c2) {
                    if (graph.get(c1).add(c2)) {
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new ArrayDeque<>();

        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {
            char current = queue.poll();
            result.append(current);

            for (char next : graph.get(current)) {
                indegree.put(next, indegree.get(next) - 1);

                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }

        return result.length() == graph.size() ? result.toString() : "";
    }
}
