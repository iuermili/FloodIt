// Imports for the parameters of flood

import java.lang.reflect.Array;
import java.util.*;


public class Flood {

    // Students implement this flood function.
    public static void flood(WaterColor color,
                              LinkedList<Coord> flooded_list,
                              Tile[][] tiles,
                              Integer board_size) {


        Set<Coord> floodedSet = new HashSet<>(flooded_list);
        Queue<Coord> queue = new LinkedList<>(flooded_list); // initializes queue with flooded coordinates

        while (!queue.isEmpty()) {
            Coord current = queue.poll(); // dequeue tile
            List<Coord> neighbors = current.neighbors(board_size); // neighbors of current tile

            // process each neighbor
            for (Coord neighbor : neighbors) {
                // neighbor is not flooded and has same color
                if (!floodedSet.contains(neighbor) &&
                        tiles[neighbor.getY()][neighbor.getX()].getColor() == color) {

                    // mark neighbor as flooded
                    floodedSet.add(neighbor);
                    queue.add(neighbor); // enqueue for further exploration
                    flooded_list.add(neighbor); // add to original flooded list
                }
            }
        }
    }

    // An alternative implementation goes here.
    public static void flood1(WaterColor color,
                             LinkedList<Coord> flooded_list,
                             Tile[][] tiles,
                             Integer board_size) {
        // YOUR CODE
    }

}
