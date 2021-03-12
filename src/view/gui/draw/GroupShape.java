package view.gui.draw;

import model.persistence.draw.Point;
import model.persistence.draw.PositionConfig;
import view.interfaces.draw.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author algorithm
 */
public class GroupShape implements IShape {

    private final List<IShape> groupItemList;
    private final PositionConfig positionConfig;
    private boolean isSelected;

    public GroupShape(List<IShape> groupItemList) {
        this.groupItemList = groupItemList;
        positionConfig = new PositionConfig();
        positionConfig.setStartPoint(new Point(20000, 2000));
        positionConfig.setEndPoint(new Point(0, 0));
    }

    @Override
    public void draw(Graphics p) {
        Graphics2D g = (Graphics2D) p;
        if(isSelected){
            groupItemList.forEach(shape -> {
                positionConfig.updateBorder(shape.getPositionConfig());
            });
        }
        g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0));
        g.drawRect(positionConfig.getAdjustedStart().getX() - 5, positionConfig.getAdjustedStart().getY() - 5, positionConfig.getWidth() + 10, positionConfig.getHeight() + 10);
        groupItemList.forEach(shape -> {
            shape.draw(g);
        });
    }

    @Override
    public IShape move(int dx, int dy) {
        List<IShape> groupItems = new ArrayList<>();
        groupItemList.forEach(shape -> groupItems.add(shape.move(dx, dy)));
        return new GroupShape(groupItems);
    }

    @Override
    public boolean contains(Point startPoint) {
        for (IShape shape : groupItemList) {
            if (shape.contains(startPoint)) {
                return true;
            }
        }
        return false;
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

    public List<IShape> getGroupItemList() {
        return groupItemList;
    }

}
