import java.awt.geom.Point2D;

public class Point extends Point2D implements  Comparable<Point>{
    private double x = 0.0;
    private double y = 0.0;

    private String label = "";

    public Point(double x, double y, String label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
        this.label = label;

    }

    public void addPoint(Point point) {
        x += point.getX();
        y += point.getY();
    }

    public String toString() {
        return label + "  X: " + x + " ,  Y: " + y;
    }

    public void scalePoint(double scale) {
        if (scale != 0) {
            x *= scale;
            y *= scale;
        }
    }

    public Point clone() {
        Point point = new Point(x, y, "clone_" + label);
        return point;
    }

    public Point normalize(Point offset, double scale) {
        Point point = new Point(x - offset.getX(), y - offset.getY(), "normalize_" + label);
        point.scalePoint(scale);
        return point;
    }

    public int getIntX() {
        return (int) Math.round(x);
    }

    public int getIntY() {
        return (int) Math.round(y);
    }

    public Point subtract(Point p2) {
        Point result = new Point(x - p2.getX(), y - p2.getY(), "diff_" + label + p2.getLabel());
        return result;
    }

    public String getLabel() {
        return label;
    }

    public int compareTo(Point o) {
        return label.compareTo(o.label);
    }
}
