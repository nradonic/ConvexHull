
import java.util.Iterator;

import static org.junit.Assert.*;

public class InvertPointsTest {
    Point point1 = new Point(400, 500, "point1");
    Point point2 = new Point (500,600,"point2");
    Point point3 = new Point(400,400,"point3");
    Point point4 = new Point(600,600,"point4");

    XYPoints xyPoints = new XYPoints();

    @org.junit.Test
    public void invertPoints() {
        xyPoints = new XYPoints();
        xyPoints.addPoint(point1);
        xyPoints.addPoint(point2);
        xyPoints.addPoint(point3);
        xyPoints.addPoint(point4);

        XYPoints results = InvertPoints.invertPoints(xyPoints);
        Iterator<Point> iterator = results.iterator();
        Point result1 = iterator.next();
        assertEquals("Point 1 inverted: ","point1-inv",result1.getLabel());
        assertEquals("Point 1 X: ",150, result1.getIntX());
        assertEquals("Point 1 Y: ",500, result1.getIntY());

        Point result2 = iterator.next();
        assertEquals("Point 2 inverted: ","point2-inv",result2.getLabel());
        assertEquals("Point 2 X: ",500, result2.getIntX());
        assertEquals("Point 2 Y: ",850, result2.getIntY());


        Point result3 = iterator.next();
        assertEquals("Point 3 inverted: ","point3-inv",result3.getLabel());
        assertEquals("Point 3 X: ",282, result3.getIntX());
        assertEquals("Point 3 Y: ",282, result3.getIntY());


        Point result4 = iterator.next();
        assertEquals("Point 4 inverted: ","point4-inv",result4.getLabel());
        assertEquals("Point 4 X: ",718, result4.getIntX());
        assertEquals("Point 4 Y: ",718, result4.getIntY());

    }

    @org.junit.Test
    public void invertPointPairs() {
    }
}
