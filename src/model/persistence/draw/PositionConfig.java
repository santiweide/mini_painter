package model.persistence.draw;

import static java.lang.Math.min;
import static java.lang.Math.max;

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
        int startX = min(startPoint.getX(), endPoint.getX());
        int startY = min(startPoint.getY(), endPoint.getY());
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

    public void updateBorder(PositionConfig positionConfig) {
        startPoint.setX(min(positionConfig.startPoint.getX(), startPoint.getX()));
        startPoint.setY(min(positionConfig.startPoint.getY(), startPoint.getY()));
        endPoint.setX(max(positionConfig.endPoint.getX(), endPoint.getX()));
        endPoint.setY(max(positionConfig.endPoint.getY(), endPoint.getY()));
    }
}
