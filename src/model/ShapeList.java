package model;

import model.interfaces.IShapeList;
import model.interfaces.IShapeListObserver;
import view.interfaces.draw.IShape;

import java.util.ArrayList;
import java.util.List;

/**
 * @author algorithm
 */
public class ShapeList implements IShapeList {
    private final List<IShape> shapeList;
    private final List<IShapeListObserver> observerList;

    public ShapeList(){
        shapeList = new ArrayList<>();
        observerList = new ArrayList<>();
    }

    @Override
    public void add(IShape shape) {
        shapeList.add(shape);
        notifyObserver();
    }

    @Override
    public void remove(IShape shape) {
        shapeList.remove(shape);
        notifyObserver();
    }

    @Override
    public List<IShape> getShapeList() {
        return shapeList;
    }

    @Override
    public void registerObserver(IShapeListObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void notifyObserver() {
        for(IShapeListObserver observer : observerList){
            observer.update();
        }
    }

}
