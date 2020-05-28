/**
 * @author Deven Ronquillo
 * @version 29/11/17
 *
 * A class describing a edg for a weighted graph.
 *  tracks the tail, head, and weight of an edge.
 */
public class Edge{

    private int edgeWeight;
    private int headVertex;
    private int tailVertex;

    /**
     * Constructor for Edge
     *
     * @param tailVertex The starting vertex of the edge.
     * @param headVertex The ending vertex of the edge.
     * @param edgeWeight The value associated with traversing the edge.
     */
    public Edge(int tailVertex, int headVertex, int edgeWeight){

        this.edgeWeight = edgeWeight;
        this.headVertex = headVertex;
        this.tailVertex = tailVertex;
    }

    public int getEdgeWeight() {
        return edgeWeight;
    }

    public int getHeadVertex() {
        return headVertex;
    }

    public int getTailVertex() {
        return tailVertex;
    }

    public int getNeighbourVertex(int vertex) {

        if (this.tailVertex == vertex){

            return this.headVertex;
        }
        else{

            return this.tailVertex;
        }
    }
}
