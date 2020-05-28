/**
 * @author Deven Ronquillo
 * @version 30/11/17
 *
 * A runner class to tie together HW4.
 */
public class FindShortestRoadPath {

    public static void main(String[] args) {

        int source = 1;
        int destination = 1276;

        WeightedGraph g = new WeightedGraph(MapDataParser.parse(args[0]), Integer.valueOf(args[1]), Integer.valueOf(args[2]));
        g.calculateShortestDistances();
        g.printPath();
        g.writePath(args[3]);
    }
}
