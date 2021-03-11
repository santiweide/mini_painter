package view.gui;

import model.interfaces.IShapeList;
import model.interfaces.IShapeListObserver;
import view.interfaces.PaintCanvasBase;
import view.interfaces.draw.IShape;


import java.awt.Graphics2D;
import java.awt.Graphics;


/**
 * @author algorithm
 */
public class PaintCanvas extends PaintCanvasBase implements IShapeListObserver {
    /**
     * 存储已经画好的图形
     */
    private final IShapeList shapeList;

    public PaintCanvas(IShapeList shapeList){
        this.shapeList = shapeList;
        // shapeList被Mouse更新的话，PaintCanvas也要更新
        shapeList.registerObserver(this);
    }

    @Override
    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    @Override
    public void paint(Graphics g){

        for (IShape iShape : shapeList.getShapeList()) {
            iShape.draw(g);
        }
    }

    @Override
    public void update() {
        repaint();
    }
}
