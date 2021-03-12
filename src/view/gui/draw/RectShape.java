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
 * 画一个长方形
 * @author algorithm
 */
public class RectShape implements IShape {
    private ShapeConfig shapeConfig;
    private ShapeShadingType shapeShadingType;
    private Color primaryColor;
    private Color secondaryColor;

    public RectShape(ShapeConfig shapeConfig, IApplicationState appState){
        this.shapeConfig = shapeConfig;
        this.primaryColor = ColorMap.getColor(appState.getActivePrimaryColor());
        this.secondaryColor = ColorMap.getColor(appState.getActiveSecondaryColor());
        this.shapeShadingType = appState.getActiveShapeShadingType();
    }

    @Override
    public void draw(Graphics p) {
        Graphics2D g = (Graphics2D) p;
        if(shapeShadingType.equals(ShapeShadingType.OUTLINE)){
            g.setColor(primaryColor);
            g.drawRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        } else if(shapeShadingType.equals(ShapeShadingType.FILLED_IN)){
            g.setColor(primaryColor);
            g.fillRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        } else if(shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(primaryColor);
            g.setStroke(new BasicStroke(5));
            g.drawRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
            g.setColor(secondaryColor);
            g.fillRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        }
    }

    @Override
    public void move(int dx, int dy) {
        Point adjustStart = shapeConfig.getAdjustedStart();
        Point adjustEnd = shapeConfig.getAdjustedEnd();
        adjustStart.setX(adjustStart.getX() + dx);
        adjustStart.setX(adjustStart.getX() + dx);
        adjustEnd.setX(adjustEnd.getX() + dy);
        adjustEnd.setX(adjustEnd.getX() + dy);
    }

}
