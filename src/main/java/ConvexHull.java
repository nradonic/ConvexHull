import java.util.logging.Logger;

public class ConvexHull {
    private static Logger logger = Logger.getLogger(ConvexHull.class.getName());
    public final static int maxRadius = 400;
    public final static int minRadius = 50;

    public static void main(String[] args) {

        ConvexHull convexHull = new ConvexHull();
        XYPoints xyPoints = MakeInitialPoints.makeInitialPoints(800);
        System.out.println("Points:\n" + xyPoints.toString());

        Point midPoint = xyPoints.getAverageCenter();
        System.out.println("Midpoint: " + midPoint.toString());

        DisplayFrame displayFrame = new DisplayFrame();
        displayFrame.drawPoints(xyPoints);
        displayFrame.addMidPoint(midPoint);

        PointPairs perimeterPoints = FindPerimeterPoints.findPerimeterPoints(xyPoints);
        System.out.println("Result Points:\n" + perimeterPoints.toString());


        XYPoints invertedPoints = InvertPoints.invertPoints(xyPoints);
        PointPairs perimeterPairs2 = FindPerimeterPoints.findPerimeterPoints(invertedPoints);
        PointPairs interiorPairs = InvertPoints.invertPointPairs(perimeterPairs2);
        displayFrame.drawPointSegments(interiorPairs);
        displayFrame.drawPointSegments(perimeterPoints);
    }
}


// TODO: this Convex Hull calculation project works to
// - generate points 50-950,50-950 range pairs
// -determines which line pairs are extenal most pairs - corresponding to line segment that does not have
//   neighbors on both sides
// - coincident points are deleted
// - points in the middle of the segment are deleted
// - resulting points are graphed in a JFrame 1000px x 1000px
// - segment endpoints are circled in yellow and blue - with different diameters for coincident ends
// - geometric center is dotted in green