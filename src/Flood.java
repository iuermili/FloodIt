// Imports for the parameters of flood
import java.lang.reflect.Array;
import java.util.*;


public class Flood {

    // the time complexity of the flood() function is O(n^2)
    public static void flood(WaterColor color,
                             LinkedList<Coord> flooded_list,
                             Tile[][] tiles,
                             Integer board_size) {
        Stack<Coord> stack = new Stack<>(); // initialize stack with flooded coordinates
        for (Coord coord : flooded_list) {
            stack.push(coord);
        }

        while (!stack.isEmpty()) {
            Coord current = stack.pop(); // pop coord
            List<Coord> neighbors = current.neighbors(board_size); // neighbors of current tile

            // process each neighbor
            for (Coord neighbor : neighbors) {
                // neighbor is not flooded and has same color (O(n) operation)
                if (!flooded_list.contains(neighbor) &&
                        tiles[neighbor.getY()][neighbor.getX()].getColor() == color) {

                    // mark neighbor as flooded
                    stack.push(neighbor); // push, to later check its neighbors
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
        Stack<Coord> stack = new Stack<>(); // initialize stack with flooded coordinates
        for (Coord coord : flooded_list) {
            stack.push(coord);
        }

        while (!stack.isEmpty()) {
            Coord current = stack.pop(); // pop coord
            List<Coord> neighbors = current.neighbors(board_size); // neighbors of current tile

            // process each neighbor
            for (Coord neighbor : neighbors) {
                // neighbor is not flooded and has same color (0(1) operation)
                if (!floodedSet.contains(neighbor) &&
                        tiles[neighbor.getY()][neighbor.getX()].getColor() == color) {

                    // mark neighbor as flooded
                    stack.push(neighbor); // push, to later check its neighbors
                    flooded_list.add(neighbor); // update original flooded list
                    floodedSet.add(neighbor); // sync the set with the flooded list
                }
            }
        }
    }

}