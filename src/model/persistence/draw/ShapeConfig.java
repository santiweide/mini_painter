package model.persistence.draw;

/**
 * 保存形状配置信息 来自ApplicationState
 * @author algorithm
 */
public class ShapeConfig {

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

    /**
     * 左上为起点
     * @return
     */
    public Point getAdjustedStart()
    {
        int startX = Math.min(startPoint.getX(), endPoint.getX());
        int startY = Math.min(startPoint.getY(), endPoint.getY());
        return new Point(startX, startY);
    }

    /**
     * 右下为终点
     * @return
     */
    public Point getAdjustedEnd()
    {
        int endX = Math.max(startPoint.getX(), endPoint.getX());
        int endY = Math.max(startPoint.getY(), endPoint.getY());
        return new Point(endX, endY);
    }

    public int getWidth()
    {
        Point adjustStart = getAdjustedStart();
        Point adjustEnd = getAdjustedEnd();
        return adjustEnd.getX() - adjustStart.getX();
    }

    public int getHeight()
    {
        Point adjustStart = getAdjustedStart();
        Point adjustEnd = getAdjustedEnd();
        return adjustEnd.getY() - adjustStart.getY();
    }
}
