package controller;


import model.interfaces.IShapeList;
import model.persistence.ApplicationState;
import model.persistence.draw.Point;
import model.persistence.draw.ShapeConfig;
import model.persistence.draw.ShapeFactory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author algorithm
 */
public class ShapeMouseListener implements MouseListener, MouseMotionListener {

    private ShapeFactory shapeFactory = new ShapeFactory();
    private ApplicationState appState;
    private IShapeList shapeList;
    private ShapeConfig shapeConfig;

    public ShapeMouseListener(ApplicationState appState, IShapeList shapeList, ShapeConfig shapeConfig) {
        this.appState = appState;
        this.shapeList = shapeList;
        this.shapeConfig = shapeConfig;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 画的时候不动这个
        // System.out.println("Click "+e.getX()+","+e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shapeConfig.setStartPoint(new Point(e.getX(), e.getY()));
        System.out.println("Press " + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shapeConfig.setEndPoint(new Point(e.getX(), e.getY()));
        System.out.println("Released " + e.getX() + "," + e.getY());
        shapeList.add(shapeFactory.createShape(shapeConfig, appState));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
