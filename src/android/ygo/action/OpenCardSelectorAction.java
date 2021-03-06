package android.ygo.action;

import android.ygo.core.*;
import android.ygo.op.Operation;

public class OpenCardSelectorAction extends BaseAction {
    public OpenCardSelectorAction(Operation operation) {
        super(operation.getDuel(), operation.getContainer(), operation.getItem());
    }

    @Override
    public void execute() {
        duel.select(item, container);

        CardList list;
        SelectableItem source;
        Field field = (Field) container;
        if(item instanceof InfoWindow) {
            item = field.getItem();
        }

        if (item instanceof OverRay) {
            list = ((OverRay) item).getOverRayUnits();
            source = item;
        } else {
            list = (CardList) item;
            source = item;
        }
        if (list.size() == 0) {
            return;
        }

        if (list.getName().equals(CardList.DECK) ||
                list.getName().equals(CardList.EX)) {
            list.openAll();
        }

        CardSelector selector = new CardSelector(source, list);
        duel.setCardSelector(selector);
    }
}
