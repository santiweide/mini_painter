package utils;

import model.persistence.draw.PositionConfig;

import java.awt.*;

public class DashedBorderHelper {

    public static void paintGroupDashedBorder(Graphics2D g, PositionConfig positionConfig){
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0));
        g.drawRect(positionConfig.getAdjustedStart().getX()-5, positionConfig.getAdjustedStart().getY()-5, positionConfig.getWidth()+10, positionConfig.getHeight()+10);
    }

//    public static PositionConfig getGroupBorderPosition(PositionConfig[] positionList){
//        // get a min rect covering all shapes
//    }
}
