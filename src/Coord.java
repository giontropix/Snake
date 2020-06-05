public class Coord {
    private final int x;
    private final int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean equals(Object o) {
        if (o instanceof Coord) {
            Coord c = (Coord)o;
            return c.x == this.x && c.y == this.y;
        }
        return false;
    }
}