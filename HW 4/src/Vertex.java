import java.util.ArrayList;

/**
 * @author Deven Ronquillo
 * @version 29/11/2017
 *
 * A Class describing a vertex for a weighted graph.
 * Can track the shortest distance to a a neighbor vertex, and edges it is the tail of, and if all edges belonging to it have been explored.
 */
public class Vertex {

    private boolean known;
    private int traceback;
    private int tracebackDistance = Integer.MAX_VALUE;
    private ArrayList<Edge> connectedEdges = new ArrayList<>();


    public int getTraceback() {
        return traceback;
    }

    public void setTraceback(int traceback) {
        this.traceback = traceback;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public int getTracebackDistance() {
        return tracebackDistance;
    }

    public void setTracebackDistance(int tracebackDistance) {
        this.tracebackDistance = tracebackDistance;
    }

    public ArrayList<Edge> getConnectedEdges() {
        return connectedEdges;
    }
}
