// Imports for the parameters of flood
import java.lang.reflect.Array;
import java.util.*;


public class Flood {
    // In total, we have n + (n*n) time complexity
    // Therefore, the time complexity of the flood() function is O(n^2)
    public static void flood(WaterColor color,
                              LinkedList<Coord> flooded_list,
                              Tile[][] tiles,
                              Integer board_size) {
        // initialize a copy of flooded_list to check all possible neighbors
        // without negatively impacting the game logic
        LinkedList<Coord> coordsToCheck = new LinkedList<>(flooded_list);
        // This takes O(n) time

        while (!coordsToCheck.isEmpty()) { // This loop is O(n)
            Coord current = coordsToCheck.removeFirst(); // pop first coordinate
            List<Coord> neighbors = current.neighbors(board_size); // neighbors of current tile
            // getting the neighbors takes a constant time as it will always <= 4 neighbors.

            // process each neighbor
            // loop takes constant time as size of neighbors is <= 4.
            for (Coord neighbor : neighbors) {
                if (!flooded_list.contains(neighbor) && // looking up in linked list takes O(n) at worst.
                        tiles[neighbor.getY()][neighbor.getX()].getColor() == color) {
                    // color lookup is constant time
                    coordsToCheck.add(neighbor); // add, to later check the neighbor's neighbors
                    flooded_list.add(neighbor); // update original flooded list
                }
            }
        }
    }

    // the time complexity of the flood1() function is n + n + n
    // Which is a time complexity of O(n)
    public static void flood1(WaterColor color,
                             LinkedList<Coord> flooded_list,
                             Tile[][] tiles,
                             Integer board_size) {
        // initialize set with flooded coordinates for O(1) lookup time
        Set<Coord> floodedSet = new HashSet<>(flooded_list);
        // initialize a copy of flooded_list to check all possible neighbors
        // initializing the Hash Set takes O(n) time
        LinkedList<Coord> coordsToCheck = new LinkedList<>(flooded_list);
        // This takes O(n) to copy the linked list

        // while there are still coordinates to check
        // Loop is O(n)
        while (!coordsToCheck.isEmpty()) {
            Coord current = coordsToCheck.removeFirst(); // pop first coordinate
            List<Coord> neighbors = current.neighbors(board_size); // neighbors of current coordinate

            // process each neighbor
            // getting neighbors and iterating through is constant time
            for (Coord neighbor : neighbors) {
                // neighbor is not flooded (0(1)) and has the same color as the user's input (0(1))
                if (!floodedSet.contains(neighbor) && // lookup in the HashSet is O(1) constant time.
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
