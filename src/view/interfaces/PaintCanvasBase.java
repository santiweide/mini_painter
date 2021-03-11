package view.interfaces;

import model.persistence.draw.Point;

import javax.swing.*;
import java.awt.*;

/**
 * 管理所有的图形展示
 * @author algorithm
 */
public abstract class PaintCanvasBase extends JComponent {
    /**
     * 画图的，借助swing
     * @return
     */
    public abstract Graphics2D getGraphics2D();

}
