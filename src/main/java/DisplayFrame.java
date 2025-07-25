import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

import static java.lang.Math.floor;

public class DisplayFrame extends JFrame {
    MyPanel myPanel = new MyPanel();
    Dimension baseDimension = new Dimension(1000, 1000);
    SizeSelectorPanel sizeSelectorPanel = new SizeSelectorPanel();


    public DisplayFrame() {
        this.setLayout(new BorderLayout());
        this.add(myPanel, BorderLayout.NORTH);
        this.add(sizeSelectorPanel, BorderLayout.SOUTH);
        myPanel.setPreferredSize(baseDimension);
        this.add(sizeSelectorPanel);
        this.setTitle("Convex Hull - inner and outer convex hulls");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        pack();

        setVisible(true);
        setLocationRelativeTo(null);

        repaint();
    }

    public void drawPoints(XYPoints xyPoints) {
        myPanel.drawPoints(xyPoints);
        repaint();
    }

    public void drawPointSegments(PointPairs perimeterPoints) {
        myPanel.drawPointSegments(perimeterPoints);
        repaint();
    }

    public void addMidPoint(Point midPoint) {
        myPanel.addMidPoint(midPoint);
    }
}

class MyPanel extends JPanel {
    private final int radius = 5;
    private final int diameter = 2 * radius;

    int baseX = this.getSize().width / 2;
    int baseY = this.getSize().height / 2;
    XYPoints xyPoints = new XYPoints();
    PointPairs perimeterPoints = new PointPairs();
//    XYPointScaling xyPointScaling = new XYPointScaling();

    Point midPoint;

    public void drawPoints(XYPoints xyPoints) {
        this.xyPoints = xyPoints;
    }

    public void drawPointSegments(PointPairs perimeterPoints) {
        this.perimeterPoints.addAll(perimeterPoints);
    }

    public void addMidPoint(Point midPoint) {
        this.midPoint = midPoint;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawPointSegments(g2d);
        drawPointsArray(g2d);
    }

    private void drawPointsArray(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.black);
        int diameter = 2 * radius;
        int baseX = this.getSize().width / 2;
        int baseY = this.getSize().height / 2;

        Iterator<Point> iterator = xyPoints.iterator();
        while (iterator.hasNext()) {
            Point point = iterator.next();

            int x = (int) floor(point.getX()) - radius + baseX;
            int y = (int) floor(point.getY()) - radius + baseY;
//            System.out.println("Base X,Y: " + baseX + "  " + baseY);
            g2d.fillOval(x, y, diameter, diameter);
        }

        if (midPoint != null) {
            g2d.setColor(Color.green);
            int x = (int) floor(midPoint.getX()) - diameter + baseX;
            int y = (int) floor(midPoint.getY()) - diameter + baseY;

            g2d.fillOval(x, y, 2 * diameter, 2 * diameter);
        }

        // Crosshairs
//        int baseX = g2d.getClipBounds().width / 2;
//        int baseY = g2d.getClipBounds().height / 2;

        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(baseX + 10, baseY, baseX - 10, baseY);
        g2d.drawLine(baseX, baseY + 10, baseX, baseY - 10);
    }

    private void drawPointSegments(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(2));
        int baseX = this.getSize().width / 2;
        int baseY = this.getSize().height / 2;

        Iterator<String> ipp = perimeterPoints.iterator();
        while (ipp.hasNext()) {
            g2d.setColor(Color.red);
            PointPair pp = perimeterPoints.get(ipp.next());
            Point p1 = pp.firstPoint();
            Point p2 = pp.secondPoint();
            g2d.drawLine(p1.getIntX() + baseX, p1.getIntY() + baseY, p2.getIntX() + baseX, p2.getIntY() + baseY);

            g2d.setColor(Color.blue);
            g2d.drawOval(pp.firstPoint().getIntX() - diameter + baseX, pp.firstPoint().getIntY() - diameter + baseY,
                    (2 * diameter), 2 * diameter);
            g2d.setColor(Color.yellow);
            g2d.drawOval(pp.secondPoint().getIntX() - 3 * radius + baseX, pp.secondPoint().getIntY() - 3 * radius + baseY,
                    (3 * diameter), 3 * diameter);
        }
        this.perimeterPoints.clear();
        g2d.setColor(Color.black);

    }

}