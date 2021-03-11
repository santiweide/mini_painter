package controller;

import model.interfaces.IUndoable;

import java.util.Stack;

public class OperationHistory {
    /**
     * 操作栈
     */
    private static final Stack<IUndoable> undoOperation = new Stack<IUndoable>();
    private static final Stack<IUndoable> redoOperation = new Stack<IUndoable>();

    public static void add(IUndoable cmd) {
        undoOperation.push(cmd);
        redoOperation.clear();
    }

    public static boolean undo() {
        boolean result = !undoOperation.empty();
        if (result) {
            IUndoable c = undoOperation.pop();
            redoOperation.push(c);
            c.undo();
        }
        return result;
    }

    public static boolean redo() {
        boolean result = !redoOperation.empty();
        if (result) {
            IUndoable c = redoOperation.pop();
            undoOperation.push(c);
            c.redo();
        }
        return result;
    }

}
