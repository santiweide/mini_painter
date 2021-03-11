package view.gui.draw;

import model.ShapeShadingType;
import model.persistence.ApplicationState;
import model.persistence.draw.Point;
import model.persistence.draw.ShapeConfig;
import utils.ColorMap;
import view.interfaces.draw.IShape;

import java.awt.Graphics;

/**
 * 画一个长方形
 * @author algorithm
 */
public class RectShape implements IShape {
    private ShapeConfig shapeConfig;
    private ApplicationState appState;

    public RectShape(ShapeConfig shapeConfig, ApplicationState appState){
        this.shapeConfig = shapeConfig;
        this.appState = appState;
    }

    @Override
    public void draw(Graphics g) {
        if(appState.getActiveShapeShadingType().equals(ShapeShadingType.OUTLINE)){
            g.setColor(ColorMap.getColor(appState.getActivePrimaryColor()));
            g.drawRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        } else if(appState.getActiveShapeShadingType().equals(ShapeShadingType.FILLED_IN)){
            g.setColor(ColorMap.getColor(appState.getActivePrimaryColor()));
            g.fillRect(shapeConfig.getAdjustedStart().getX(), shapeConfig.getAdjustedStart().getY(), shapeConfig.getWidth(), shapeConfig.getHeight());
        }
    }

}
