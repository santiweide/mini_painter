package view.gui.draw;

import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import model.persistence.draw.Point;
import model.persistence.draw.PositionConfig;
import utils.ColorMap;
import view.interfaces.draw.IShape;

import java.awt.*;

public class TriangleShape implements IShape {

    /**
     * position info
     */
    private final PositionConfig positionConfig;
    /**
     * filled / outlined / filled and outlined
     */
    private final ShapeShadingType shapeShadingType;
    private final Color primaryColor;
    private final Color secondaryColor;
    private boolean isSelected;
    private int[] x;
    private int[] y;

    public TriangleShape(PositionConfig positionConfig, IApplicationState appState) {
        this.positionConfig = positionConfig;
        this.primaryColor = ColorMap.getColor(appState.getActivePrimaryColor());
        this.secondaryColor = ColorMap.getColor(appState.getActiveSecondaryColor());
        this.shapeShadingType = appState.getActiveShapeShadingType();
        x = new int[]{positionConfig.getAdjustedStart().getX(), positionConfig.getAdjustedEnd().getX(), positionConfig.getAdjustedStart().getX()};
        y = new int[]{positionConfig.getAdjustedStart().getY() - 5, positionConfig.getAdjustedEnd().getY() + 5, positionConfig.getAdjustedEnd().getY() + 5};
    }

    public TriangleShape(PositionConfig positionConfig, Color primaryColor, Color secondaryColor, ShapeShadingType shapeShadingType, boolean isSelected) {
        this.positionConfig = positionConfig;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shapeShadingType = shapeShadingType;
        this.isSelected = isSelected;
        x = new int[]{positionConfig.getAdjustedStart().getX(), positionConfig.getAdjustedEnd().getX(), positionConfig.getAdjustedStart().getX()};
        y = new int[]{positionConfig.getAdjustedStart().getY() - 5, positionConfig.getAdjustedEnd().getY() + 5, positionConfig.getAdjustedEnd().getY() + 5};

    }

    @Override
    public void draw(Graphics p) {
        Graphics2D g = (Graphics2D) p;
        if (isSelected) {
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0));
            g.drawPolygon(new int[]{positionConfig.getAdjustedStart().getX() - 5, positionConfig.getAdjustedEnd().getX() + 5, positionConfig.getAdjustedStart().getX() - 5},
                    new int[]{positionConfig.getAdjustedStart().getY() - 5, positionConfig.getAdjustedEnd().getY() + 5, positionConfig.getAdjustedEnd().getY() + 5},
                    3);
        }
        if (shapeShadingType.equals(ShapeShadingType.OUTLINE)) {
            g.setColor(primaryColor);
            g.setStroke(new BasicStroke(5));
            g.drawPolygon(x, y, 3);
        } else if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)) {
            g.setColor(primaryColor);
            g.fillPolygon(x, y, 3);
        } else if (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            g.setColor(primaryColor);
            g.setStroke(new BasicStroke(5));
            g.drawPolygon(x, y, 3);
            g.setColor(secondaryColor);
            g.fillPolygon(x, y, 3);
        }
    }

    @Override
    public IShape move(int dx, int dy) {
        PositionConfig newPositionConfig = new PositionConfig();
        newPositionConfig.setStartPoint(new Point(positionConfig.getStartPoint().getX() + dx, positionConfig.getStartPoint().getY() + dy));
        newPositionConfig.setEndPoint(new Point(positionConfig.getEndPoint().getX() + dx, positionConfig.getEndPoint().getY() + dy));
        return new TriangleShape(newPositionConfig, primaryColor, secondaryColor, shapeShadingType, isSelected);
    }

    public static boolean isInside(int[] x, int[] y, Point P) {
        /*利用叉乘法进行判断,假设P点就是M点*/
        int a = 0, b = 0, c = 0;

        Point MA = new Point(P.getX() - x[0], P.getY() - y[0]);
        Point MB = new Point(P.getX() - x[1], P.getY() - y[1]);
        Point MC = new Point(P.getX() - x[2], P.getY() - y[2]);

        /*向量叉乘*/
        a = MA.getX() * MB.getY() - MA.getY() * MB.getX();
        b = MB.getX() * MC.getY() - MB.getY() * MC.getX();
        c = MC.getX() * MA.getY() - MC.getY() * MA.getX();

        return ((a <= 0 && b <= 0 && c <= 0) ||
                (a > 0 && b > 0 && c > 0));
    }

    @Override
    public boolean contains(Point startPoint) {

        return isInside(x, y, startPoint);
    }

    @Override
    public PositionConfig getPositionConfig() {
        return positionConfig;
    }


    @Override
    public void setIsSelected(boolean b) {
        isSelected = b;
    }

    @Override
    public boolean getIsSelected() {
        return isSelected;
    }

}
