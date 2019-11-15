import java.util.*;

public class GraphImplementation implements Graph {

    private int vertices;
    private LinkedList<Integer> adjacencyListArr[];


    public GraphImplementation(int vertices){
        this.vertices = vertices;
        this.adjacencyListArr = new LinkedList[vertices];

        for(int i=0; i < vertices; i++){
            adjacencyListArr[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int target) throws Exception{
        if(src < 0 || target < 0 || src >= vertices || target >= vertices){
            throw new Exception("Graph cannot handle negative values.");
        }
        adjacencyListArr[src].add(target);
    }

    public List<Integer> neighbors(int vertex) throws Exception{
        if(vertex < 0 || vertex > vertices){
            throw new Exception("Vertex must be positive integer");
        }
        return adjacencyListArr[vertex];
    }

    private void topologicalSortUtil(int v, boolean visited[], Stack stack){
        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adjacencyListArr[v].iterator();
        while(it.hasNext()){
            i = it.next();
            if(!visited[i]){
                topologicalSortUtil(i, visited, (List<Integer>) stack);
            }
        }
        stack.push(v);
    }

    public List<Integer> topologicalSort(){
        Stack stack = new Stack();
        boolean[] visited = new boolean[vertices];
        for(int i=0; i < vertices; i++){
            visited[i] = false;
        }
        for(int i=0; i < vertices; i++){
            if(visited[i] == false){
                topologicalSortUtil(i, visited, (List<Integer>) stack);
            }
        }

        List<Integer> list = new ArrayList<>();
        while(stack.empty() == false){
            list.add((Integer) stack.pop());
        }
        return list;
    }



    private void topologicalSortUtil(int start, boolean[] visited, List<Integer> list){
        visited[start] = true;
        for(int i=0; i < adjacencyListArr[start].size(); i++){
            int vertex = adjacencyListArr[start].get(i);
            if(!visited[vertex]){
                topologicalSortUtil(vertex, visited, list);
            }
        }
        list.add(start);
    }

    public static void main(String[] args){
        GraphImplementation graph = new GraphImplementation(5);
        try {
            graph.addEdge(1, 3);
            graph.addEdge(3, 2);
            graph.addEdge(2, 4);
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            System.out.println(graph.neighbors(2));
        } catch (Exception e){
            e.printStackTrace();
        }
        List<Integer> sortedNodes = graph.topologicalSort();
        System.out.println(sortedNodes);
    }

}
