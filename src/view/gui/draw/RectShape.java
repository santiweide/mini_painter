package view.gui.draw;

import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import model.persistence.draw.Point;
import model.persistence.draw.PositionConfig;
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
    private final PositionConfig positionConfig;
    /**
     * filled / outlined / filled and outlined
     */
    private final ShapeShadingType shapeShadingType;
    private final Color primaryColor;
    private final Color secondaryColor;
    private boolean isSelected;

    public RectShape(PositionConfig positionConfig, IApplicationState appState) {
        this.positionConfig = positionConfig;
        this.primaryColor = ColorMap.getColor(appState.getActivePrimaryColor());
        this.secondaryColor = ColorMap.getColor(appState.getActiveSecondaryColor());
        this.shapeShadingType = appState.getActiveShapeShadingType();
    }

    public RectShape(PositionConfig positionConfig, Color primaryColor, Color secondaryColor, ShapeShadingType shapeShadingType, boolean isSelected) {
        this.positionConfig = positionConfig;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.shapeShadingType = shapeShadingType;
        this.isSelected = isSelected;
    }

    @Override
    public void draw(Graphics p) {
        Graphics2D g = (Graphics2D) p;
        if(isSelected) {
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0));
            g.drawRect(positionConfig.getAdjustedStart().getX()-5, positionConfig.getAdjustedStart().getY()-5, positionConfig.getWidth()+10, positionConfig.getHeight()+10);
        }
        if (shapeShadingType.equals(ShapeShadingType.OUTLINE)) {
            g.setColor(primaryColor);
            g.setStroke(new BasicStroke(5));
            g.drawRect(positionConfig.getAdjustedStart().getX(), positionConfig.getAdjustedStart().getY(), positionConfig.getWidth(), positionConfig.getHeight());
        } else if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)) {
            g.setColor(primaryColor);
            g.fillRect(positionConfig.getAdjustedStart().getX(), positionConfig.getAdjustedStart().getY(), positionConfig.getWidth(), positionConfig.getHeight());
        } else if (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            g.setColor(primaryColor);
            g.setStroke(new BasicStroke(5));
            g.drawRect(positionConfig.getAdjustedStart().getX(), positionConfig.getAdjustedStart().getY(), positionConfig.getWidth(), positionConfig.getHeight());
            g.setColor(secondaryColor);
            g.fillRect(positionConfig.getAdjustedStart().getX(), positionConfig.getAdjustedStart().getY(), positionConfig.getWidth(), positionConfig.getHeight());
        }
    }

    @Override
    public IShape move(int dx, int dy) {
        PositionConfig newPositionConfig = new PositionConfig();
        newPositionConfig.setStartPoint(new Point(positionConfig.getStartPoint().getX() + dx, positionConfig.getStartPoint().getY() + dy));
        newPositionConfig.setEndPoint(new Point(positionConfig.getEndPoint().getX() + dx, positionConfig.getEndPoint().getY() + dy));
        return new RectShape(newPositionConfig, primaryColor, secondaryColor, shapeShadingType, isSelected);
    }

    @Override
    public boolean contains(Point startPoint) {
        return (positionConfig.getAdjustedStart().getX() < startPoint.getX() && positionConfig.getAdjustedStart().getY() < startPoint.getY()
                && positionConfig.getAdjustedStart().getX() + positionConfig.getWidth() > startPoint.getX() && positionConfig.getAdjustedStart().getY() + positionConfig.getWidth() > startPoint.getY());
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
