package sample.tqi.com.br.planodecarreira.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Alexandre on 15/02/2017.
 */

public class AlertMessage {
    public static AlertMessage instance;
    private static Context mContext;
    private static AlertDialog mAlertDialog;

    public AlertMessage() {
//        mAlertDialog = new AlertDialog();

    }

    public static AlertMessage getInstance(Context c){
        mContext = c;
        if (instance==null){
            instance = new AlertMessage();
        }
        return instance;
    }

    public void showMessage(String mTitle, String mMessage){

        if( mAlertDialog != null && mAlertDialog.isShowing() ) return;

        TextView titleView =  new TextView(mContext);
        titleView.setTextSize(20);
        titleView.setText(mTitle);
        titleView.setPadding(10, 20, 10, 0);
        titleView.setTextColor(Color.BLACK);
        titleView.setGravity(Gravity.CENTER);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage(mMessage);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", null);
        builder.setCustomTitle(titleView);
        mAlertDialog = builder.create();
        mAlertDialog.show();

        TextView messageView = (TextView)mAlertDialog.findViewById(android.R.id.message);
        messageView.setTextSize(15);
        messageView.setGravity(Gravity.CENTER);
    }

    public void showMessage(String mTitle, String mMessage, DialogInterface.OnClickListener negativeButton, DialogInterface.OnClickListener positiveButton){

        if( mAlertDialog != null && mAlertDialog.isShowing() ) return;

        TextView titleView =  new TextView(mContext);
        titleView.setTextSize(20);
        titleView.setText(mTitle);
        titleView.setPadding(10, 20, 10, 0);
        titleView.setTextColor(Color.BLACK);
        titleView.setGravity(Gravity.CENTER);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage(mMessage);
        builder.setCancelable(false);
        builder.setNegativeButton("Não", negativeButton);
        builder.setPositiveButton("Sim", positiveButton);
        builder.setCustomTitle(titleView);
        mAlertDialog = builder.create();
        mAlertDialog.show();

        TextView messageView = (TextView)mAlertDialog.findViewById(android.R.id.message);
        messageView.setTextSize(15);
        messageView.setGravity(Gravity.CENTER);
    }

    public void showMessage(String mTitle, String mMessage, DialogInterface.OnClickListener positiveButton){

        if( mAlertDialog != null && mAlertDialog.isShowing() ) return;

        TextView titleView =  new TextView(mContext);
        titleView.setTextSize(20);
        titleView.setText(mTitle);
        titleView.setPadding(10, 20, 10, 0);
        titleView.setTextColor(Color.BLACK);
        titleView.setGravity(Gravity.CENTER);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage(mMessage);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", positiveButton);
        builder.setCustomTitle(titleView);
        mAlertDialog = builder.create();
        mAlertDialog.show();

        TextView messageView = (TextView)mAlertDialog.findViewById(android.R.id.message);
        messageView.setTextSize(15);
        messageView.setGravity(Gravity.CENTER);
    }
}
