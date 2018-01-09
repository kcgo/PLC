package sample.tqi.com.br.planodecarreira.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import sample.tqi.com.br.planodecarreira.R;

/**
 * Created by alex on 26/10/16.
 */

public class WaitDialog extends ProgressDialog {

    public WaitDialog(Context context) {
        super(context, R.style.PLCProgressTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_wait);
    }
}