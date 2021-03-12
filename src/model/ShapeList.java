package model;

import model.interfaces.IShapeList;
import model.interfaces.IShapeListObserver;
import view.gui.draw.GroupShape;
import view.interfaces.draw.IShape;

import java.util.*;

/**
 * @author algorithm
 */
public class ShapeList implements IShapeList {
    private final List<IShape> shapeList;
    private List<IShape> selectedShapeList;
    private final List<IShape> clipBoardShapeList;
    private final List<IShapeListObserver> observerList;


    public ShapeList() {
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
        for (IShapeListObserver observer : observerList) {
            observer.update();
        }
    }

    @Override
    public void addSelectedList(IShape shape) {
        shape.setIsSelected(true);
        selectedShapeList.add(shape);
        notifyObserver();
    }

    @Override
    public List<IShape> getSelectList() {
        return selectedShapeList;
    }

    @Override
    public void clearSelectList() {
        for (IShape shape : selectedShapeList) {
            shape.setIsSelected(false);
        }
        selectedShapeList.clear();
        notifyObserver();
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
    public void clearClipBoard() {
        clipBoardShapeList.clear();
    }

    @Override
    public void setSelectList(List<IShape> newSelectedList) {
        selectedShapeList = newSelectedList;
    }

    @Override
    public void addAll(List<IShape> newShapeList) {
        shapeList.addAll(newShapeList);
        notifyObserver();
    }

    @Override
    public void removeAll(List<IShape> newShapeList) {
        shapeList.removeAll(newShapeList);
        notifyObserver();
    }

    @Override
    public void addGroup() {
        // 从shapeList删除，从selectedList删除
        // group后加入shapeList和selectedList
        shapeList.removeAll(selectedShapeList);
        List<IShape> groupList = new ArrayList<>();
        selectedShapeList.forEach(shape -> {
            shape.setIsSelected(false);
            groupList.add(shape);
            shapeList.remove(shape);
        });
        GroupShape groupShape = new GroupShape(groupList);
        selectedShapeList.clear();
        // add groupedItem to Selected List
        groupShape.setIsSelected(true);
        selectedShapeList.add(groupShape);
        shapeList.add(groupShape);
        notifyObserver();
    }

    @Override
    public GroupShape removeGroup() {
        // selectedList shapedList
        GroupShape lastGrouped = null;
        for (int i = selectedShapeList.size() - 1; i >= 0; i--) {
            IShape p = shapeList.get(i);
            if (p instanceof GroupShape) {
                // 从shapeList删除最后一个GroupShape类型的IShape
                lastGrouped = (GroupShape) p;
                shapeList.remove(lastGrouped);
                selectedShapeList.remove(lastGrouped);
                lastGrouped.getGroupItemList().forEach(grouped -> {
                    // 把lastGroup中的list添加到shapeList 和 selectedList中
                    grouped.setIsSelected(true);
                    shapeList.add(grouped);
                    selectedShapeList.add(grouped);
                });
                notifyObserver();
                break;
            }
        }
        return lastGrouped;
    }
}



