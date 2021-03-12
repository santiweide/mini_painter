package view.interfaces.draw;

import model.persistence.draw.Point;

import java.awt.Graphics;

/**
 * @author algorithm
 */
public interface IShape {

    /**
     * 绘制Shape
     * @param g swing的画布
     */
    void draw(Graphics g);

    /**
     * move
     * @param dx
     * @param dy
     */
    void move(int dx, int dy);
}
