import java.util.Random;

public class MakeInitialPoints {
    private final static int halfW = 1000 / 2;

    public static XYPoints makeInitialPoints(int numberOfPoints) {
        XYPoints xyPoints = new XYPoints();


//        for (int i = 0; i < numberOfPoints; i++) {
//            xyPoints.addPoint((double) new Random().nextInt(90) * 10 + 50,
//                    (double) new Random().nextInt(90) * 10 + 50);
//        }
        for (int i = 0; i < numberOfPoints; i++) {
            Double radius = new Random().nextDouble() * ConvexHull.maxRadius + ConvexHull.minRadius;
            Double angle = new Random().nextDouble() * 2 * Math.PI;
            Double x = Math.floor(Math.cos(angle) * radius + halfW);
            Double y = Math.floor(Math.sin(angle) * radius + halfW);
            xyPoints.addPoint(x, y);
        }


//        xyPoints.addPoint(100.0, 100.0);
//        xyPoints.addPoint(100.0, 100.0);
////        xyPoints.addPoint(100.0, 200.0);
////        xyPoints.addPoint(100.0, 400.0);
////        xyPoints.addPoint(200.0, 200.0);
//        xyPoints.addPoint(100.0, 900.0);
//        xyPoints.addPoint(900.0, 100.0);

//        xyPoints.addPoint(100.0, 500.0);
//        xyPoints.addPoint(500.0, 900.0);
//        xyPoints.addPoint(500.0, 100.0);
//        xyPoints.addPoint(900.0, 500.0);
//        xyPoints.addPoint(500.0, 200.0);
//        xyPoints.addPoint(500.0, 800.0);
//        xyPoints.addPoint(200.0, 500.0);
//        xyPoints.addPoint(800.0, 500.0);

        return xyPoints;
    }
}
