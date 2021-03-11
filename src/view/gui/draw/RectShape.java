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
    private IApplicationState appState;

    public RectShape(ShapeConfig shapeConfig, IApplicationState appState){
        this.shapeConfig = shapeConfig;
        this.appState = appState;
    }

    @Override
    public void draw(Graphics p) {
        Graphics2D g = (Graphics2D) p;
        if(appState.getActiveShapeShadingType().equals(ShapeShadingType.OUTLINE)){
            g.setColor(ColorMap.getColor(appState.getActivePrimaryColor()));
            g.drawRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        } else if(appState.getActiveShapeShadingType().equals(ShapeShadingType.FILLED_IN)){
            g.setColor(ColorMap.getColor(appState.getActivePrimaryColor()));
            g.fillRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        } else if(appState.getActiveShapeShadingType().equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
            g.setColor(ColorMap.getColor(appState.getActivePrimaryColor()));
            g.setStroke(new BasicStroke(5));
            g.drawRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
            g.setColor(ColorMap.getColor(appState.getActiveSecondaryColor()));
            g.fillRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        }
    }

    @Override
    public void addAdjustPos(int dx, int dy) {
        Point adjustStart = shapeConfig.getAdjustedStart();
        Point adjustEnd = shapeConfig.getAdjustedEnd();
        adjustStart.setX(adjustStart.getX() + dx);
        adjustStart.setX(adjustStart.getX() + dx);
        adjustEnd.setX(adjustEnd.getX() + dy);
        adjustEnd.setX(adjustEnd.getX() + dy);
    }

}
