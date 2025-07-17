import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class RemoveCoincidentPairs {
    public static PointPairs removeCoincidentPairs(PointPairs sources) {
        PointPairs results = new PointPairs();

        //get list of points
        TreeSet<Point> points = getPoints(sources);

        //get neighbors set per point
        TreeMap<Point, TreeSet<Point>> neighborsPerPoint = new TreeMap<Point, TreeSet<Point>>();
        for (Point p : points) {
            neighborsPerPoint.put(p, getNeighborsPerPoint(p, sources));
        }
        TreeSet<Point> keptPoints = new TreeSet<Point>();
        for (Point p : neighborsPerPoint.keySet()) {
            if (!pointIsAProperSubsetOfOtherPointsInCollection(p, neighborsPerPoint)) {
                keptPoints.add(p);
            }
        }

        Iterator<String> pointPairIterator = sources.iterator();
        while (pointPairIterator.hasNext()) {
            PointPair pp = sources.get(pointPairIterator.next());
            if (keptPoints.contains(pp.p1) && keptPoints.contains(pp.p2)) {
                results.put(pp);
            }
        }
        return results;
    }

    private static boolean pointIsAProperSubsetOfOtherPointsInCollection(
            Point testPoint, TreeMap<Point, TreeSet<Point>> neighborsPerPoint) {
        boolean result = false;
        for (Point p : neighborsPerPoint.keySet()) {
            if (p == testPoint) {
                continue;
            }
            boolean ptestPp = neighborsPerPoint.get(testPoint).containsAll(neighborsPerPoint.get(p));
            boolean pPPTest = neighborsPerPoint.get(p).containsAll(neighborsPerPoint.get(testPoint));
            if (ptestPp && !pPPTest || !ptestPp && pPPTest) {
                result = true;
            }
        }
        return result;
    }

    private static TreeSet<Point> getPoints(PointPairs pointPairs) {
        TreeSet<Point> resultPoints = new TreeSet<Point>();

        Iterator<String> iterator = pointPairs.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            PointPair pp = pointPairs.get(key);
            resultPoints.add(pp.firstPoint());
            resultPoints.add(pp.secondPoint());
        }
        return resultPoints;
    }

    private static TreeSet<Point> getNeighborsPerPoint(Point p, PointPairs pointPairs) {
        TreeSet<Point> results = new TreeSet<Point>();
        Iterator<String> iterator = pointPairs.iterator();

        while (iterator.hasNext()) {
            PointPair pp = pointPairs.get(iterator.next());
            Point p1 = pp.firstPoint();
            Point p2 = pp.secondPoint();
            if (p1 != p && p2 != p) {
                continue;
            }
            Point tempP = p2;
            if (p2 == p) {
                tempP = p1;
            }
            results.add(tempP);
        }
        results.add(p);
        return results;
    }

//    private static String makeSignature(TreeSet<Point> points) {
//        String result = "";
//        for (Point p : points
//        ) {
//            result += p.getLabel();
//        }
//        return result;
//    }


}
