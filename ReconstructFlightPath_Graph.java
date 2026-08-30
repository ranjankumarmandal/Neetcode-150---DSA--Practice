import java.util.*;

class Solution {
    private final Map<String, PriorityQueue<String>> graph = new HashMap<>();
    private final LinkedList<String> result = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>())
                    .offer(ticket.get(1));
        }

        dfs("JFK");
        return result;
    }

    private void dfs(String airport) {
        PriorityQueue<String> destinations = graph.get(airport);

        while (destinations != null && !destinations.isEmpty()) {
            dfs(destinations.poll());
        }

        result.addFirst(airport);
    }
}
