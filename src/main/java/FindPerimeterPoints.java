import org.apache.commons.math3.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class FindPerimeterPoints {
    private static Logger logger = Logger.getLogger(FindPerimeterPoints.class.getName());

    public static PointPairs findPerimeterPoints(XYPoints xyPoints) {
        XYPoints deduplicatedPoints = deduplicate(xyPoints);
        PointPairs resultBlock1 = pointProjectionAcrossConnectingSegments(deduplicatedPoints);
        PointPairs resultBlock0 = RemoveCoincidentPairs.removeCoincidentPairs(resultBlock1);
        return resultBlock0;
    }

    private static XYPoints deduplicate(XYPoints points){
        XYPoints result = new XYPoints();
        Map<Pair<Double,Double>,Point> filteredPoints = new HashMap<Pair<Double, Double>,Point>();
        Iterator<Point> iterator = points.iterator();
        while(iterator.hasNext()){
            Point p = iterator.next();
            Pair<Double, Double> pointPairBy2DLocation = new Pair<Double, Double>(p.getX(),p.getY());
            if(filteredPoints.containsKey(pointPairBy2DLocation)){
                logger.info("Duplicate point found: "+ p.toString());
                continue;
            }
            filteredPoints.put(pointPairBy2DLocation,p);
        }
        for (Pair<Double,Double> key: filteredPoints.keySet()){
            result.addPoint(filteredPoints.get(key));
        }
        return result;
    }


    /**
     * zero based vector to points - dot product
     *
     * @param p1
     * @param p2
     * @return dot product
     */
    private static double dotProduct(Point p1, Point p2) {

        double dotProd = (p1.getX() * p2.getX() + p1.getY() * p2.getY());
//        logger.info("orthogonal: " + p1.toString() + "    testDiff: " + p2.toString() + "  projection: " + dotProd);
        return dotProd;
    }


    private static PointPairs pointProjectionAcrossConnectingSegments(XYPoints xyPoints) {

        // from middlePoint project out to every other point
        // discard points if some other point is farther out than that point itself, in that direction

        XYPoints resultBlock = new XYPoints();

        PointPairs perimeterPointPairs = new PointPairs();
        PointPairs insidePoints = new PointPairs();

        Iterator<Point> selfIterator = xyPoints.iterator();
        while (selfIterator.hasNext()) {
            Point pointSelf = selfIterator.next();

            Iterator<Point> iteratorOther = xyPoints.iterator();
            Point pointOther = null;
            while (iteratorOther.hasNext()) {
                pointOther = iteratorOther.next();
                if (pointSelf == pointOther) {
                    continue;
                }

                PointPair pp = new PointPair(pointSelf, pointOther);
                String hashKey = pp.hash();

                if (perimeterPointPairs.contains(pp)) {
                    continue;
                }

                if (insidePoints.contains(pp)) {
                    continue;
                }

//                    logger.info("\nP1: " + pointSelf.getLabel() + "  P2: " + pointOther.getLabel());
                if (exteriorMaximumSegment(pointSelf, pointOther, xyPoints)) {

//                        logger.info("Add " + pointSelf);
//                        logger.info("Add " + pointOther + "\n");

                    perimeterPointPairs.put(pp);
                    continue;
                }
                insidePoints.put(pp);

            }
        }
        logger.info("Perimeter pairs: " + perimeterPointPairs.size() + "   Inside pairs: " + insidePoints.size());
        return perimeterPointPairs;
    }

    private static boolean exteriorMaximumSegment(Point p1, Point p2, XYPoints xyPoints) {
        final Double uncertainty = 1.000000000;
        boolean result = true;
        TreeMap<Double, Point> lateralPointProjections = new TreeMap<Double, Point>();
        Point segmentVector = p1.subtract(p2);

        Point orthoganalP1P2 = new Point(segmentVector.getY(), -segmentVector.getX(), "orthoganlVector");

        boolean positiveProjectionFound = false;
        boolean negativeProjectionFound = false;

        // loop over all points to see that none project along the orthogonal, past the connecting segment
        Iterator<Point> iterator1 = xyPoints.iterator();
        while (iterator1.hasNext() && !(positiveProjectionFound && negativeProjectionFound)) {
            //
            Point testPoint = iterator1.next();
//            if (testPoint == p1 || testPoint == p2) {
//                continue;
//            }

            Double projection = dotProduct(orthoganalP1P2, p1.subtract(testPoint));
//            logger.info("P1: " + p1 + "   P2: " + p2 + "   testPoint: " + testPoint + "   Projection: " + projection);

            Double sign = Math.signum(projection);

            if (sign > 0.5) {
                positiveProjectionFound = true;
            }
            if (sign < -0.5) {
                negativeProjectionFound = true;
            }

            if (Math.abs(projection) < 1) {
                Double lateral = dotProduct(segmentVector, p1.subtract(testPoint));
                lateralPointProjections.put(lateral, testPoint);
            }
//            logger.info("Projection sign: " + sign + "  +:" + positiveProjectionFound + "  -: " + negativeProjectionFound);

        }
        boolean bothSigns = positiveProjectionFound && negativeProjectionFound;
        boolean largestSegment = true;
        if (lateralPointProjections.size() > 2) {
//            lateralPointProjections.put(0.0,p1);
//            lateralPointProjections.put(dotProduct(p1,p2.subtract(p1)),p2);
            Point firstP = lateralPointProjections.get(lateralPointProjections.firstKey());
            Point lastP = lateralPointProjections.get(lateralPointProjections.lastKey());
            largestSegment = firstP == p1 && lastP == p2 || lastP == p1 && firstP == p2;
        }
        result = !bothSigns && largestSegment;


//        logger.info("Points across segment: " + p1.getLabel() + " : " + p2.getLabel() + "  +" + positiveProjectionFound +
//                "  -" + negativeProjectionFound + "  largest segment: " + largestSegment + "  result: " + result);
        return result;
    }


}
