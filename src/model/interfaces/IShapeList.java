package model.interfaces;

import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import view.interfaces.draw.IShape;

import java.util.List;

/**
 * 观察者模式
 * @author algorithm
 */
public interface IShapeList {
    void add(IShape shape);
    void remove(IShape shape);
    List<IShape> getShapeList();

    /**
     * 注册观察者
     * @param paintCanvas 其实是个单例
     */
    void registerObserver(IShapeListObserver paintCanvas);
    void notifyObserver();

}
