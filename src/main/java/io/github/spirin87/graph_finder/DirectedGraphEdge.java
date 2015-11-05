package io.github.spirin87.graph_finder;

/**
 * Directed graph edge
 * <p>
 * @author spirin87@gmail.com
 * <p>
 * Nov 5, 2015, 1:33:52 PM
 */
public class DirectedGraphEdge extends GraphEdge {

    private long x;

    private long y;

    public DirectedGraphEdge(long x, long y) {
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
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (x ^ (x >>> 32));
        result = prime * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DirectedGraphEdge other = (DirectedGraphEdge) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DirectedGraphEdge [x=" + x + ", y=" + y + "]";
    }
}
