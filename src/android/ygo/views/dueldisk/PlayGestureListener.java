package android.ygo.views.dueldisk;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.ygo.action.Action;
import android.ygo.action.ActionDispatcher;
import android.ygo.op.*;

public class PlayGestureListener extends GestureDetector.SimpleOnGestureListener {
    DuelDiskView view;

    public PlayGestureListener(DuelDiskView view) {
        this.view = view;
    }

    public void onUp(MotionEvent event) {
        Drag drag = view.getDuel().getDrag();
        if (drag != null) {
            drag.dropTo(event.getX(), event.getY());
            view.getDuel().setDrag(null);
            Action action = ActionDispatcher.dispatch(drag);
            action.execute();
        }
        view.updateActionTime();
    }

    @Override
    public boolean onDown(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Click click = new Click(view.getDuel(), event.getX(), event.getY());
        Action action = ActionDispatcher.dispatch(click);
        action.execute();
        view.updateActionTime();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Press press = new Press(view.getDuel(), event.getX(), event.getY());
        Action action = ActionDispatcher.dispatch(press);
        action.execute();
        view.updateActionTime();
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        DoubleClick dblClick = new DoubleClick(view.getDuel(), event.getX(), event.getY());
        Action action = ActionDispatcher.dispatch(dblClick);
        action.execute();
        view.updateActionTime();
        return super.onDoubleTap(event);
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float dx, float dy) {
        Drag drag = view.getDuel().getDrag();
        if (drag == null) {
            StartDrag startDrag = new StartDrag(view.getDuel(), event1.getX(), event1.getY());
            Action action = ActionDispatcher.dispatch(startDrag);
            action.execute();

            drag = new Drag(startDrag, view.getDuel(), event1.getX(), event1.getY());
            view.getDuel().setDrag(drag);
        }
        drag.move(event2.getX(), event2.getY());
        view.updateActionTime();
        return true;
    }
}
