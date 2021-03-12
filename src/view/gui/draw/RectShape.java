package view.gui.draw;

import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import model.persistence.draw.Point;
import model.persistence.draw.ShapeConfig;
import utils.ColorMap;
import view.interfaces.draw.IShape;

import java.awt.*;

/**
 * draw rectangles
 *
 * @author algorithm
 */
public class RectShape implements IShape {
    /**
     * position info
     */
    private final ShapeConfig shapeConfig;
    /**
     * filled / outlined / filled and outlined
     */
    private final ShapeShadingType shapeShadingType;
    private final Color primaryColor;
    private final Color secondaryColor;

    public RectShape(ShapeConfig shapeConfig, IApplicationState appState) {
        this.shapeConfig = shapeConfig;
        this.primaryColor = ColorMap.getColor(appState.getActivePrimaryColor());
        this.secondaryColor = ColorMap.getColor(appState.getActiveSecondaryColor());
        this.shapeShadingType = appState.getActiveShapeShadingType();
    }

    public RectShape(ShapeConfig shapeConfig, Color primaryColor, Color secondaryColor, ShapeShadingType shapeShadingType) {
        this.shapeConfig = shapeConfig;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shapeShadingType = shapeShadingType;
    }

    @Override
    public void draw(Graphics p) {
        Graphics2D g = (Graphics2D) p;
        if (shapeShadingType.equals(ShapeShadingType.OUTLINE)) {
            g.setColor(primaryColor);
            g.setStroke(new BasicStroke(5));
            g.drawRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        } else if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)) {
            g.setColor(primaryColor);
            g.fillRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        } else if (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            g.setColor(primaryColor);
            g.setStroke(new BasicStroke(5));
            g.drawRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
            g.setColor(secondaryColor);
            g.fillRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        }
    }

    @Override
    public IShape move(int dx, int dy) {
        ShapeConfig newShapeConfig = new ShapeConfig();
        newShapeConfig.setStartPoint(new Point(shapeConfig.getStartPoint().getX() + dx, shapeConfig.getStartPoint().getY() + dy));
        newShapeConfig.setEndPoint(new Point(shapeConfig.getEndPoint().getX() + dx, shapeConfig.getEndPoint().getY() + dy));
        return new RectShape(newShapeConfig, primaryColor, secondaryColor, shapeShadingType);
    }

    @Override
    public boolean contains(Point startPoint) {
        return (shapeConfig.getAdjustedStart().getX() < startPoint.getX() && shapeConfig.getAdjustedStart().getY() < startPoint.getY()
                && shapeConfig.getAdjustedStart().getX() + shapeConfig.getWidth() > startPoint.getX() && shapeConfig.getAdjustedStart().getY() + shapeConfig.getWidth() > startPoint.getY());
    }

}
