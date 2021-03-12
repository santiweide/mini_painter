package view.gui.draw;

import model.persistence.draw.Point;
import view.interfaces.draw.IShape;

import java.awt.*;

public class TriangleShape implements IShape {
    @Override
    public void draw(Graphics g) {

    }

    @Override
    public IShape move(int dx, int dy) {
        return null;
    }

    @Override
    public boolean contains(Point startPoint) {
        return false;
    }

    @Override
    public void setIsSelected(boolean b) {

    }

    @Override
    public boolean getIsSelected() {
        return false;
    }
}
