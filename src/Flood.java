// Imports for the parameters of flood

import java.lang.reflect.Array;
import java.util.*;


public class Flood {

    // Students implement this flood function.
    public static void flood(WaterColor color,
                              LinkedList<Coord> flooded_list,
                              Tile[][] tiles,
                              Integer board_size) {
        LinkedList<Coord> flooded_list_copy = new LinkedList<>(flooded_list);
        for (Coord coord : flooded_list_copy){
            List<Coord> neighbors = coord.neighbors(board_size);
            for (Coord neighbor : neighbors) {
                if (tiles[neighbor.getY()][neighbor.getX()].getColor() == color){
                    if (!flooded_list_copy.contains(neighbor)) {
                        flooded_list.add(neighbor);
                    }
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
