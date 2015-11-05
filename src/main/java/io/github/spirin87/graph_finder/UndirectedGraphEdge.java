package io.github.spirin87.graph_finder;

/**
 * Undirected graph edge
 * <p>
 * @author spirin87@gmail.com
 * <p>
 * Nov 5, 2015, 8:09:21 AM
 */
public class UndirectedGraphEdge extends GraphEdge {

    private long x;

    private long y;

    public UndirectedGraphEdge(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public long getX() {
        return x;
    }

    @Override
    public long getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return (int) ((x ^ (x >>> 32)) + (y ^ (y >>> 32)));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UndirectedGraphEdge other = (UndirectedGraphEdge) obj;
        if ((x == other.x && y == other.y) || (y == other.x && x == other.y))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "UndirectedGraphEdge [x=" + x + ", y=" + y + "]";
    }
}
