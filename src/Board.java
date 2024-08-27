import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.lang.reflect.Method;

/**
 * A Board represents the current state of the game. Boards know their
 * dimension, the collection of coordinates that are in the current
 * flooded region, and mapping from coordinates to tiles.
 *
 * @author <put your name here>
 */

public class Board {
    LinkedList<Coord> flooded;
    Tile[][] tiles;
    int size;

    /**
     * Constructs a square game board of the given size, initializes the list of
     * inside tiles to include just the tile in the upper left corner, and puts
     * all the other tiles in the outside list.
     */
    public Board(int size) {
        flooded = new LinkedList<>();
        tiles = new Tile[size][size];
        this.size = size;
        for (int y = 0; y != size; ++y)
            for (int x = 0; x != size; ++x) {
                Coord coord = new Coord(x, y);
                tiles[y][x] = new Tile(coord);
            }
        initialize_flooded();
    }

    /*
     * Constructs a square game board of the given size, initializing the
     * colors of th tiles according to the two-dimensional array of colors.
     * This is handy for testing purposes.
     */
    public Board(int size, WaterColor[][] colors) {
        flooded = new LinkedList<>();
        tiles = new Tile[size][size];
        this.size = size;
        for (int y = 0; y != size; ++y)
            for (int x = 0; x != size; ++x) {
                Coord coord = new Coord(x, y);
                tiles[y][x] = new Tile(coord,colors[y][x]);
            }
        initialize_flooded();
    }

    public void initialize_flooded() {
        // this code is intentionally slow to discourage copying. -Jeremy
        flooded.add(Coord.ORIGIN);
        WaterColor color = tiles[0][0].getColor();
        boolean changed;
        do {
            changed = false;
            for (int y = 0; y < size; ++y)
                for (int x = 0; x < size; ++x) {
                    Tile t = tiles[y][x];
                    Coord c = t.getCoord();
                    if (!flooded.contains(c) && t.getColor() == color) {
                        for (Coord n : c.neighbors(size))
                            if (flooded.contains(n)) {
                                flooded.add(c);
                                changed = true;
                                break;
                            }
                    }
                }
        } while (changed);
    }

    /**
     * Returns the tile at the specified coordinate.
     */
    public Tile get(Coord coord) {
        return tiles[coord.getY()][coord.getX()];
    }

    /**
     * Returns the size of this board.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns true iff all tiles on the board have the same color.
     */
    public boolean fullyFlooded() {
        return flooded.size() == size * size;
    }

    /**
     * Updates this board by changing the color of the current flood region
     * and extending its reach.
     */
    public void flood(int k, WaterColor color) {

        // Change the colors of the already-flooded tiles
        for (Coord coord : flooded)
            this.get(coord).setColor(color);

        // Call the student's flood functions.

        if (k == 0)
            Flood.flood(color, flooded, tiles, size);
        else if (k == 1)
            Flood.flood1(color, flooded, tiles, size);
        else {
            System.out.println("We don't handle k > 1.");
            System.exit(-1);
        }
    }

    /**
     * Returns the "best" GameColor for the next move.
     * <p>
     * Modify this comment to describe your algorithm. Possible
     * strategies to pursue include maximizing the number of tiles in
     * the current flooded region, or maximizing the size of the
     * perimeter of the current flooded region.
     */
    public WaterColor suggest() {
        WaterColor cornerColor = this.get(Coord.ORIGIN).getColor();
        return WaterColor.pickOneExcept(cornerColor);
    }

    /**
     * Returns a string representation of this board. Tiles are given as their
     * color names, with those inside the flooded region written in uppercase.
     */
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                Coord curr = new Coord(x, y);
                WaterColor color = get(curr).getColor();
                ans.append(flooded.contains(curr)
                        ? color.toString().toUpperCase()
                        : color);
                ans.append("\t");
            }
            ans.append("\n");
        }
        return ans.toString();
    }

    /**
     * Simple testing.
     */
    public static void main(String... args) {
        // Print out boards of size 1, 2, ..., 5
        int n = 5;
        for (int size = 1; size <= n; size++) {
            Board someBoard = new Board(size);
            System.out.println(someBoard);
        }
    }

    /**
     * colorCorner: Sets a specified area connected to the the initial flooded corner equal to the same color
     * as the initial flooded corner; to be used in testing
     * <p>
     * num: number of squares including the the corner square to be colored cornerColor
     * type: 0 - triangle fill
     * 1 - horizontal fill
     * 2 - vertical fill
     * 3 - fill around the border (make sure that you don't indicate more squares than the border)
     * cornerColor: the color that you'd like to set the corner region
     */

    void colorCorner(int num, int type, WaterColor cornerColor) {

        //guard
        if (num > size * size || num < 1) {
            throw new IllegalArgumentException("colorCorner: num must be at least 1 and less or equal to the total squares");
        } else if (type < 0 || type > 3) {
            throw new IllegalArgumentException("colorCorner: type must be between 0 and 3 (inclusive)");
        }


        //make the corner a unique color
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (y == 0 && x == 0) this.get(Coord.ORIGIN).setColor(cornerColor);

                Coord coord = new Coord(x, y);
                this.get(coord).setColor(WaterColor.pickOneExcept(cornerColor));
            }
        }

        //creates the corner
        switch (type) {

            //triangle flood
            case 0:
                int layer = 0;
                while (num > 0) {
                    for (int i = 0; i <= layer; i++) {
                        Coord toChange = new Coord(i, layer - i);
                        this.get(toChange).setColor(cornerColor);
                        num--;
                    }
                    layer++;
                }
                break;

            //horizontal fill
            case 1:

                // vertical fill
            case 2:
                for (int i = 0; i < num; i++) {
                    Coord toChange = type == 1 ?
                            new Coord(i / this.size, i % this.size) :
                            new Coord(i % this.size, i / this.size);
                    this.get(toChange).setColor(cornerColor);
                }
                break;

            // fill the border
            case 3:
                if (num >= 4 * (size - 1))
                    throw new IllegalArgumentException("colorCorner: Too many squares for border!");

                int delta_x = 1, delta_y = 0, x = 0, y = 0;
                for (int i = 0; i < num; i++) {

                    Coord toChange = new Coord(x, y);
                    this.get(toChange).setColor(cornerColor);

                    if ((i + 1) % size == 0) {
                        int temp = delta_x;
                        delta_x = -delta_y;
                        delta_y = temp;
                        i++;
                        num++;
                    }
                    x += delta_x;
                    y += delta_y;
                }
                break;
        }
    }

    @Override
    public boolean equals(Object obj) {
        // if the memory references are equal,
        // then the objects are same hence return true.
        if (this == obj) {
            return true;
        }
        // if the second object is null, but the first
        // object is not null, return false.
        if (obj == null) {
            return false;
        }
        // if the class type of these two objects are not equal,
        // return false.
        if (getClass() != obj.getClass()) {
            return false;
        }

        Board that = (Board) obj;
        if (this.size != that.size) {
            return false;
        }
        for (int i = 0; i != size; ++ i) {
            for (int j = 0; j != size; ++ j) {
                if (! this.tiles[i][j].equals(that.tiles[i][j]))
                    return false;
            }
        }
        boolean[][] this_flooded = new boolean[size][size];
        boolean[][] that_flooded = new boolean[size][size];
        for (Coord c : this.flooded) {
            this_flooded[c.getY()][c.getX()] = true;
        }
        for (Coord c : that.flooded) {
            that_flooded[c.getY()][c.getX()] = true;
        }

       return Arrays.deepEquals(this_flooded, that_flooded);
    }
}
