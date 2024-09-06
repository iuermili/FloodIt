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
        for (Coord coord : flooded_list_copy) {
            for (Coord neighbor : coord.neighbors(board_size)) {
                int x = neighbor.getX(), y = neighbor.getY();
                if (!flooded_list_copy.contains(neighbor) && color == tiles[x][y].getColor()) {
                    flooded_list.add(neighbor);
                }
            }
        }
    }

    // An alternative implementation goes here.
    public static void flood1(WaterColor color,
                             LinkedList<Coord> flooded_list,
                             Tile[][] tiles,
                             Integer board_size) {
        Set<Coord> flooded_set = new HashSet<>(flooded_list);
        for (Coord coord : flooded_set) {
            for (Coord neighbor : coord.neighbors(board_size)) {
                int x = neighbor.getX(), y = neighbor.getY();
                if (!flooded_set.contains(neighbor) && color == tiles[x][y].getColor()) {
                    flooded_list.add(neighbor);
                }
            }
        }
    }

}
