package android.ygo.action;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.ygo.core.Card;
import android.ygo.op.Operation;
import android.ygo.utils.Configuration;
import android.ygo.utils.Utils;

import java.io.File;
import java.util.List;

public class DeckChangeAction extends BaseAction {
    public DeckChangeAction(Operation operation) {
        super(operation.getDuel(), operation.getContainer(), operation.getItem());
    }

    @Override
    public void execute() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Utils.getContext());
        builder.setTitle("请选择卡组");
        final String[] decks = Utils.decks();
        builder.setItems(decks, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String deck = decks[which];
                duel.start(deck);
                Utils.getContext().getDuelDiskView().updateActionTime();
            }
        });
        builder.create().show();
    }
}
