public class PointPair {
    Point p1;
    Point p2;

    public PointPair(Point p1, Point p2) {
        if (p1.getLabel().compareTo(p2.getLabel()) < 0) {
            this.p1 = p1;
            this.p2 = p2;
            return;
        }
        this.p2 = p1;
        this.p1 = p2;
    }

    public Point firstPoint() {
        return p1;
    }

    public Point secondPoint() {
        return p2;
    }

    public String hash() {
        return p1.getLabel() + p2.getLabel();
    }

    public String toString() {
        return p1.toString() + "  :  " + p2.toString() + "\n";
    }
}
