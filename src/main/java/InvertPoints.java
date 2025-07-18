import java.util.Iterator;

public class InvertPoints {

    public static XYPoints invertPoints(XYPoints xyPoints) {
        XYPoints results = new XYPoints();

        Iterator<Point> iterator = xyPoints.iterator();

        while (iterator.hasNext()) {

            Point point = iterator.next();
            Point invPoint = invertPoint(point);
            results.addPoint(invPoint);
        }
        //invert radius of point locations - mapping....
        return results;
    }

    private static Point invertPoint(Point point) {
        Double dx = point.getX();
        Double dy = point.getY();

        Double distance = Math.sqrt(dx * dx + dy * dy);
        Double primeDisplacement = (ConvexHull.halfRadius * 2 - distance);

        Double scale =(primeDisplacement/distance);
        Point point1 = new Point( dx * scale,  dy * scale, point.getLabel() + "-inv");
        return point1;
    }

    public static PointPairs invertPointPairs(PointPairs pointPairs) {
        PointPairs results = new PointPairs();
        Iterator<String> iterator = pointPairs.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            PointPair pp = pointPairs.get(key);
            Point p1 = invertPoint(pp.p1);
            Point p2 = invertPoint(pp.p2);
            results.put(new PointPair(p1, p2));

        }
        return results;
    }
//private Point
}
