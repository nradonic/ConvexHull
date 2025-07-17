import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class XYPoints {
    private ArrayList<Point> points = new ArrayList<Point>();

    public void addPoint(Double x, Double y) {
        points.add(new Point(x, y, "P" + Integer.toString(points.size())));
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public Iterator<Point> iterator() {
        Iterator<Point> iterator = points.listIterator();
        return iterator;
    }

    public String toString() {
        String result = "[\n";
        for (Point point : points) {
            result += "[ " + point.toString() + " ]\n";
        }
        result += "]\n";
        return result;
    }

    public Point getAverageCenter() {
        Double xTotal = 0.0;
        Double yTotal = 0.0;
        Double newX = 0.0;
        Double newY = 0.0;
        if (points.size() > 0) {
            for (Point pTemp : points) {
                xTotal += pTemp.getX();
                yTotal += pTemp.getY();
            }
            newX = xTotal / points.size();
            newY = yTotal / points.size();
        }
        return new Point(newX, newY, "averagePoint");
    }

    public double getMinX() {
        double min = Double.MAX_VALUE;
        for (Point point : points) {
            min = Math.min(min, point.getX());
        }
        return min;
    }

    public double getMaxX() {
        double max = Double.MIN_VALUE;
        for (Point point : points) {
            max = Math.max(max, point.getX());
        }
        return max;
    }

    public double getMinY() {
        double min = Double.MAX_VALUE;
        for (Point point : points) {
            min = Math.min(min, point.getY());
        }
        return min;
    }

    public double getMaxY() {
        double max = Double.MIN_VALUE;
        for (Point point : points) {
            max = Math.max(max, point.getY());
        }
        return max;
    }

    public XYPoints copy() {
        XYPoints copy = new XYPoints();
        for (Point point : points) {
            copy.addPoint(point);
        }
        return copy;
    }


}
