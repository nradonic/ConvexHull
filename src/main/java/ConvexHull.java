public class ConvexHull {
    //    private static final Logger logger = Logger.getLogger(ConvexHull.class.getName());
    public final static int maxRadius = 400;
    public final static int minRadius = 50;
    private static int kount = 100;
    private static DisplayFrame displayFrame = new DisplayFrame();

    public static void main(String[] args) {
        CreateDrawEncirclePoints(kount);
        while (true) {
            boolean goState = displayFrame.sizeSelectorPanel.checkPushedGoButton();
            boolean toggleState = displayFrame.sizeSelectorPanel.toggleButtonState();
            if (!goState && !toggleState) {
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interrupted");
                    break;
                }
            }
            if (goState) {
                kount = displayFrame.sizeSelectorPanel.getSelectedSize();
                CreateDrawEncirclePoints(kount);
            }
        }
    }

    private static void CreateDrawEncirclePoints(int kount) {
        XYPoints xyPoints = MakeInitialPoints.makeInitialPoints(kount);
        //System.out.println("Points:\n" + xyPoints.toString());

        Point midPoint = xyPoints.getAverageCenter();
        System.out.println("Midpoint: " + midPoint.toString());


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