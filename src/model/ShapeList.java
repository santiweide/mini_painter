package model;

import model.interfaces.IShapeList;
import model.interfaces.IShapeListObserver;
import view.interfaces.draw.IShape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author algorithm
 */
public class ShapeList implements IShapeList {
    private final List<IShape> shapeList;
    private List<IShape> selectedShapeList;
    private final List<IShape> clipBoardShapeList;
    private final List<IShapeListObserver> observerList;

    public ShapeList(){
        shapeList = new ArrayList<>();
        observerList = new ArrayList<>();
        selectedShapeList = new ArrayList<>();
        clipBoardShapeList = new ArrayList<>();
    }

    @Override
    public void add(IShape shape) {
        //IShape shape1 = shape;
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

    @Override
    public void addSelectedList(IShape shape) {
        selectedShapeList.add(shape);
    }

    @Override
    public List<IShape> getSelectList() {
        return selectedShapeList;
    }

    @Override
    public void clearSelectList() {
        selectedShapeList.clear();
    }

    @Override
    public void addClipBoard(IShape shape) {
        clipBoardShapeList.add(shape);
    }

    @Override
    public List<IShape> getClipBoard() {
        return clipBoardShapeList;
    }

    @Override
    public void setSelectList(List<IShape> newSelectedList) {
        selectedShapeList = newSelectedList;
    }

}
