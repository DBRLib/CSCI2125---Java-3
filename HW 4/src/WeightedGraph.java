import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Deven Ronquillo
 * @version 11/30/17
 *
 * A weighted graph.
 *
 * Keeps track of edges, verticies, sources, and destinations.
 *
 * Note: can easily be expanded to handle multiple destinations.
 */
public class WeightedGraph {

    private Edge[] edges;
    private Vertex[] verticies;

    private int edgeCount;
    private int vertexCount;

    private int source;
    private int destination;

    /**
     * Weighter graph constructor.
     *
     * @param edges An array of all edges to construct the graph.
     * @param source The vertex to set as the root of all paths.
     * @param destination The intended vertex to end at.
     */
    public WeightedGraph(Edge[] edges, int source, int destination){

        System.out.println("Starting graph creation...");

        this.edges = edges;
        this.source = source;
        this.destination = destination;

        this.vertexCount = findAllVerticies(this.edges);
        this.verticies = new Vertex[vertexCount];

        System.out.println("----------Vercicies to make----------: "+vertexCount);

        for (int n = 0; n < this.vertexCount; n++) {

            System.out.println("Made vertex: "+ n);

            this.verticies[n] = new Vertex();
        }


        this.edgeCount = edges.length;

        System.out.println("----------Edges to Link----------: "+vertexCount);

        for (int edgeToAdd = 0; edgeToAdd < this.edgeCount; edgeToAdd++) {

            System.out.println("linking Edge:" +edgeToAdd+" of weight: "+edges[edgeToAdd].getEdgeWeight()+"to vertecies: "+edges[edgeToAdd].getTailVertex()+" and "+edges[edgeToAdd].getHeadVertex());

            this.verticies[edges[edgeToAdd].getTailVertex()].getConnectedEdges().add(edges[edgeToAdd]);
            this.verticies[edges[edgeToAdd].getHeadVertex()].getConnectedEdges().add(edges[edgeToAdd]);
        }

        System.out.println("Graph creation finished!");
    }

    /**
     * Finds the maximum number of verticies in a set of edges.
     *
     * @param edges An array of edges to scan.
     * @return An int describing the maximum number of verticies.
     */
    private int findAllVerticies(Edge[] edges) {

        int verticies = 0;

        System.out.println("Scaning edges for verticies...");

        for (Edge e : edges) {

            if (e.getHeadVertex() > verticies) {

                verticies = e.getHeadVertex();
            }
            if (e.getTailVertex() > verticies) {

                verticies = e.getTailVertex();
            }
        }

        verticies++;

        System.out.println(verticies+" found!");

        return verticies;
    }

    /**
     * Takes a source vertex and from there applies Dijsktra's Algorithm to find all possible shortes paths for it.
     */
    public void calculateShortestDistances() {

        System.out.println("Setting up our source vertex...");

        this.verticies[source].setTracebackDistance(0);
        int nextNode = source;

        for (int i = 0; i < this.verticies.length; i++) {

            System.out.println("Getting edges for vertex: "+nextNode+"...");

            ArrayList<Edge> currentNodeEdges = this.verticies[nextNode].getConnectedEdges();

            System.out.println(currentNodeEdges.size()+" were found!");

            for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {

                System.out.println("Getting neighbor node...");

                int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourVertex(nextNode);

                System.out.println("Neighbor vertex: "+neighbourIndex +" found!");

                if (!this.verticies[neighbourIndex].isKnown()) {

                    System.out.println("Neighbor is unknown!");

                    int tentative = this.verticies[nextNode].getTracebackDistance() + currentNodeEdges.get(joinedEdge).getEdgeWeight();

                    System.out.println("Tentative distance to neighbor: "+tentative);

                    if (tentative < verticies[neighbourIndex].getTracebackDistance()) {

                        System.out.println("Set neighbors distance!");

                        verticies[neighbourIndex].setTracebackDistance(tentative);
                        verticies[neighbourIndex].setTraceback(nextNode);
                    }
                }
            }

            System.out.println("Vertex: "+nextNode+" now fully explored!");

            verticies[nextNode].setKnown(true);

            System.out.println("moving to the closest neighbor of vertex: "+nextNode+"...");

            nextNode = getNodeShortestDistanced();
        }

        System.out.println("Finsihed calculating shortest path!");
    }

    /**
     * Finds the vertex with the smallest traceback distance with respect to our source.
     *
     * @return int index of the closest vertex.
     */
    private int getNodeShortestDistanced() {
        int storedNodeIndex = 0;
        int storedDist = Integer.MAX_VALUE;

        for (int i = 0; i < this.verticies.length; i++) {

            int currentDist = this.verticies[i].getTracebackDistance();

            if (!this.verticies[i].isKnown() && currentDist < storedDist) {

                storedDist = currentDist;
                storedNodeIndex = i;
            }
        }
        return storedNodeIndex;
    }

    /**
     * Prints the shortest path found from our source to destination.
     */
    public void printPath() {

        ArrayList<Integer> traceback = new ArrayList<>();
        int nextNode = destination;

        while(verticies[nextNode].getTracebackDistance() != 0){

            traceback.add(new Integer(nextNode));

            nextNode = verticies[nextNode].getTraceback();
        }

        StringBuilder tracebackString = new StringBuilder();

        tracebackString.append(source);

        for(int i = traceback.size() - 1; i >= 0; i--){

            tracebackString.append(",");

            tracebackString.append(traceback.get(i));

        }

        System.out.println("\n\nThe shortest distance from node:" + source + " to node: " + destination + " has a weight of: " + verticies[destination].getTracebackDistance() + " by following path: " + tracebackString+".");
    }

    /**
     * Prints path to a file.
     *
     * @param output File to print to.
     */
    public void writePath(String output) {

        ArrayList<Integer> traceback = new ArrayList<>();
        int nextNode = destination;

        while(verticies[nextNode].getTracebackDistance() != 0){

            traceback.add(new Integer(nextNode));

            nextNode = verticies[nextNode].getTraceback();
        }

        StringBuilder tracebackString = new StringBuilder();

        tracebackString.append(source);

        for(int i = traceback.size() - 1; i >= 0; i--){

            tracebackString.append(",");

            tracebackString.append(traceback.get(i));

        }

        try{

            FileWriter fileWriter = new FileWriter(output);

            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.printf("The shortest distance from node: %d to node: %d has a weight of: %d by following path: %s.", source, destination, verticies[destination].getTracebackDistance(), tracebackString);

            printWriter.close();
            fileWriter.close();

            System.out.println("Finished writing to file!");
        }
        catch (IOException e){

            System.err.println("IOException: "+e.getMessage());
        }

    }
}
