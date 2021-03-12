package model.persistence.draw;

/**
 * store position info
 *
 * @author algorithm
 */
public class PositionConfig {

    private Point startPoint;
    private Point endPoint;

    public void setStartPoint(Point startValuePoint) {
        this.startPoint = startValuePoint;
    }

    public void setEndPoint(Point endValuePoint) {
        this.endPoint = endValuePoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getAdjustedStart() {
        int startX = Math.min(startPoint.getX(), endPoint.getX());
        int startY = Math.min(startPoint.getY(), endPoint.getY());
        return new Point(startX, startY);
    }

    public Point getAdjustedEnd() {
        int endX = Math.max(startPoint.getX(), endPoint.getX());
        int endY = Math.max(startPoint.getY(), endPoint.getY());
        return new Point(endX, endY);
    }

    public int getWidth() {
        Point adjustStart = getAdjustedStart();
        Point adjustEnd = getAdjustedEnd();
        return adjustEnd.getX() - adjustStart.getX();
    }

    public int getHeight() {
        Point adjustStart = getAdjustedStart();
        Point adjustEnd = getAdjustedEnd();
        return adjustEnd.getY() - adjustStart.getY();
    }
}
