package controller.operator;

public class GroupOperator implements IOperator{
    @Override
    public void run() {
        // int groupLevel
        // group: groupLevel ++
        // ungroup: groupLevel --
        // group 的东西也可以作为 select List??
        // 不如在ShapeList之外搞一个...GroupList....
        // List<IGroup> GroupList
        // List<IGroup> selectedGroupList
        //
        // draw isGroup 的时候 有 酷炫特效，依赖外面的属性
        // 把 selectedList 加到 GroupList 里面
        // move 两种  IShape 和 IGroup
    }
}
