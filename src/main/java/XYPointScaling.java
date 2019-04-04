import java.awt.*;
import java.util.Iterator;

public class XYPointScaling implements Iterator<Point> {

    private XYPoints points = new XYPoints();
    private Rectangle rectangle = null;

    private double pointRange = 0;
    private double scale = 1.0;
    private Point offset = new Point(0.0, 0.0,"offset");

    private Iterator<Point> scaledIterator = null;

    public Point geometricMiddle() {
        double midX = (points.getMaxX() + points.getMinX()) / 2;
        double midY = (points.getMaxY() + points.getMinY()) / 2;
        offset = new Point(midX, midY,"geometricMiddle");
        return offset;
    }

    public void getPointRange() {
        pointRange = Math.max(points.getMaxX() - points.getMinX(),
                points.getMaxY() - points.getMinY());
    }

    public void calculateScaling() {

    }

    public void setScalingRange(XYPoints xyPoints, Rectangle rectangle) {
        this.points = xyPoints;
        this.rectangle = rectangle;
        int width = rectangle.width;
        int height = rectangle.height;
    }

    public Iterator<Point> iterator() {
        return points.iterator();
    }

    public boolean hasNext() {
        return scaledIterator.hasNext();
    }

    public Point next() {
        return null;
    }

    public void remove() {

    }


}