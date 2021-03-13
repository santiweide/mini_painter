# JPaint Project Report

[github link](https://github.com/santiweide/mini_painter)

[TOC]

## Project review
No missing features and extra credits, but some bugs.

### Sprint4 - Group dash borders' position is not precise
* When pressing group with shapes more than rectangle selected, the dashed border is not calculated properly. The groups' invisible dashed border uses min north west point and max south east point to print a rectangle, but the triangle and the ellipse may be exclueded. Still looking for a better algorithm.


## Design Notes

## Adapter
### MouseAdapters for different mouse modes
The inputs of the project including the inputs from clicking gui buttons, and inputs of mouse events. The button clicks are handled in `controller.JPaintController` with `uiModule.addEvent`, and for mouse events, we design mouse listeners extending `java.awt.event.MouseAdapter`. Since there are three mouse modules, "DRAW", "SELECT" and "MOVE", we introduce three mouse adapters, `view.gui.mouse.DrawMouseAdapter`, `view.gui.mouse.SelectMouseAdapter`, and `view.gui.mouse.PaintMouseAdapter` to corresponde to different mouse modes.
// this is a picture from draw.io

## Observer
### PaintCanvas Observes the ShapeList
After the mouse event, we should paint. The Shapes we paint are stored in the shapeList, which is a singleton in the whole program.
// this is a picture from draw.io


## Factory
### ShapeFactory
There are four shapes defined in the prject. When we draw, we could get Rectangle, Triangle and Ellipse. When we click group, we could get GroupShape. All the shape should support draw, select and move. So, they could all be implemnting an interface IShape, with `draw()`, `contains()` and `move()` like methods. The shape factory uses the ApplicationState to get current shape, and gives out corresponding shape objects. 

// this is a picture from draw.io



## Command
### IOperator is designed for Undo and Redo
Since we should be able to redo and undo, some operations should be able to act like an object. So we design an unterface `IUndoable`, which could be stored in stack inside `controler.OperationHistory`. CreateShape, MoveShape, and other operators are encapsulated and pushed into stack once they are operated. When the UNDO is called, the undo stack pops and redu stack pushes; when the REDO is called, redo stack pops and undo stack pushes. 
// this is a picture from draw.io

## Composite
### The GroupShape 
As Groups could act as a whole, it means Group is like IShape; Moreover, Group could be added into Group, so it comes to composite pattern. The group is like a directory, and Rectangle, Triangle and Ellipse are like file. We could access files by directory, so could we access Rectangle, Triangle and Ellipse by GroupShape. So the design are as follows:
// this is a picture from draw.io
