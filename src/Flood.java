// Imports for the parameters of flood
import java.lang.reflect.Array;
import java.util.*;


public class Flood {
    // the time complexity of the flood() function is O(n^2)
    public static void flood(WaterColor color,
                              LinkedList<Coord> flooded_list,
                              Tile[][] tiles,
                              Integer board_size) {
        // initialize a copy of flooded_list to check all possible neighbors
        // without negatively impacting the game logic
        LinkedList<Coord> coordsToCheck = new LinkedList<>(flooded_list);

        while (!coordsToCheck.isEmpty()) {
            Coord current = coordsToCheck.removeFirst(); // pop first coordinate
            List<Coord> neighbors = current.neighbors(board_size); // neighbors of current tile

            // process each neighbor
            for (Coord neighbor : neighbors) {
                // neighbor is not flooded (O(n)) and has the same color as the user's input (0(1))
                if (!flooded_list.contains(neighbor) &&
                        tiles[neighbor.getY()][neighbor.getX()].getColor() == color) {
                    // mark neighbor as flooded
                    coordsToCheck.add(neighbor); // add, to later check the neighbor's neighbors
                    flooded_list.add(neighbor); // update original flooded list
                }
            }
        }
    }

    // the time complexity of the flood1() function is O(n)
    public static void flood1(WaterColor color,
                             LinkedList<Coord> flooded_list,
                             Tile[][] tiles,
                             Integer board_size) {
        // initialize set with flooded coordinates for O(1) lookup time
        Set<Coord> floodedSet = new HashSet<>(flooded_list);
        // initialize a copy of flooded_list to check all possible neighbors
        // without negatively impacting the game logic
        LinkedList<Coord> coordsToCheck = new LinkedList<>(flooded_list);

        // while there are still coordinates to check
        while (!coordsToCheck.isEmpty()) {
            Coord current = coordsToCheck.removeFirst(); // pop first coordinate
            List<Coord> neighbors = current.neighbors(board_size); // neighbors of current coordinate

            // process each neighbor
            for (Coord neighbor : neighbors) {
                // neighbor is not flooded (0(1)) and has the same color as the user's input (0(1))
                if (!floodedSet.contains(neighbor) &&
                        tiles[neighbor.getY()][neighbor.getX()].getColor() == color) {
                    // mark neighbor as flooded
                    coordsToCheck.add(neighbor); // add, to later check the neighbor's neighbors
                    flooded_list.add(neighbor); // update original flooded list
                    floodedSet.add(neighbor); // sync the set with the flooded list
                }
            }
        }
    }

}
