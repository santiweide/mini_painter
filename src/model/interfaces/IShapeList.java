package model.interfaces;

import view.gui.PaintCanvas;
import view.gui.draw.GroupShape;
import view.interfaces.PaintCanvasBase;
import view.interfaces.draw.IShape;

import java.util.Collection;
import java.util.List;

/**
 * Shape Array that could be observed
 *
 * @author algorithm
 */
public interface IShapeList {
    void add(IShape shape);

    void remove(IShape shape);

    List<IShape> getShapeList();

    /**
     * register observer
     *
     * @param observer observers that change when notifyObserver is called
     */
    void registerObserver(IShapeListObserver observer);

    void notifyObserver();

    void addSelectedList(IShape shape);

    List<IShape> getSelectList();

    void clearSelectList();

    void addClipBoard(IShape shape);

    List<IShape> getClipBoard();

    void clearClipBoard();

    void setSelectList(List<IShape> newSelectedList);

    void addAll(List<IShape> newShapeList);

    void removeAll(List<IShape> newShapeList);

    void addGroup();

    GroupShape removeGroup();
}
