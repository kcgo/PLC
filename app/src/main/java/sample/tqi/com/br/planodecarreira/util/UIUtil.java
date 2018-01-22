package sample.tqi.com.br.planodecarreira.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.support.v4.app.ActivityCompat.requestPermissions;

/**
 * Created by Alexandre on 06/02/2017.
 */

public class UIUtil {
    // Storage Permissions
    private static final int REQUEST = 1;
    private static String[] PERMISSIONS_PHONE_CALL = {Manifest.permission.CALL_PHONE};
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static String[] PERMISSIONS_GPS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

//    private ArrayList<Card> cards;

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST
            );
        }
    }

    public static void verifyPhoneCallPermissions(Context context, String phone) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            requestPermissions(
                    (Activity) context,
                    PERMISSIONS_PHONE_CALL,
                    REQUEST
            );
        }  else {
            getPhoneCall(context, phone);
        }
    }

    public static void getPhoneCall(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }

    public static void searchAndHideSoftKeybordFromView(View view, final Activity act) {
        if(!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(act);
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View nextViewInHierarchy = ((ViewGroup) view).getChildAt(i);
                searchAndHideSoftKeybordFromView(nextViewInHierarchy, act);
            }
        }
    }

    public static void hideSoftKeyboard (Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static Drawable changeColorIcon(Drawable drawable, int color, Context context){
        ColorStateList csl = AppCompatResources.getColorStateList(context, color);
        Drawable wrapDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrapDrawable, csl);
        return wrapDrawable;
    }

    public static void openPageWeb(Context context, String url){
        Intent webIntent = new Intent(Intent.ACTION_VIEW);
        webIntent.setData(Uri.parse(url));
        context.startActivity(webIntent);
    }

    public static String getCleanText(String baseText){
        String number = baseText.replaceAll("[^0-9]*", "");
        return number;
    }

    public static String formattedPhone(String basePhone){
        String number = basePhone.replaceAll("[^0-9]*", "");
        if (number.length() > 11)
            number = number.substring(0, 11);
        int length = number.length();

       // String paddedNumber = padNumber(number, 11);

        /* Split phone number into parts... */
        String part1 ="";
        String part2 ="";
        String part3 = "";
        if (number.length() >= 10) {
            if (number.length() == 10) {
                part1 = number.substring(0, 2);
                part2 = number.substring(2, 6);
                part3 = number.substring(6, 10);
            } else {
                part1 = number.substring(0, 2);
                part2 = number.substring(2, 7);
                part3 = number.substring(7, 11);
            }
        }else{
            length =0;
        }


                /* build the masked phone number... */
        String phone =(length==0 ? "" : "(" + part1 + ")" + part2 + "-" + part3);

        return phone;
    }

    public static boolean isNetworkException(Throwable e) {
        return e instanceof UnknownHostException
                || e instanceof UnknownServiceException
                || e instanceof IOException;
    }

    public static double stringToDouble(String valor) {
        Double number = null;
        try {
            valor = valor.replaceAll("[.]", "");
            valor = valor.replaceAll("[,]", ".");
            valor = valor.replaceAll("[R$]", "");
            number = Double.parseDouble(valor);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }

    public static String stringToCurrency(String valor){
        String result;
        try {
            // Transformamos o número em monetário.
            valor = valor.replaceAll("[,]", ".");
//            DecimalFormat df = new DecimalFormat("###,##0.00");
//            valor = df.format(Double.parseDouble(valor));
            DecimalFormat format = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.getDefault());
            Locale BRAZIL = new Locale("pt", "BR");
            format.setCurrency(Currency.getInstance(BRAZIL));

            String symbol = format.getCurrency().getSymbol();
            format.setNegativePrefix("-"+symbol);
            format.setNegativeSuffix("");

            result = format.format(Double.parseDouble(valor));
            result = result.replace("$", "$ ");

        } catch (NumberFormatException e) {
            result = "-";
        }
        return result;
    }

    public static Bitmap decodeBitmap(String photo) {
        BitmapFactory.Options options;

        try {
            String imageInSD = photo;
            Bitmap bitmap = BitmapFactory.decodeFile(imageInSD);
            return bitmap;
        } catch (OutOfMemoryError e) {
            try {
                options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                options.inJustDecodeBounds = true;
                String imageInSD = photo;
                Bitmap bitmap = BitmapFactory.decodeFile(imageInSD, options);
                return bitmap;
            } catch (Exception ex) {
                return null;
            }
        }
    }

    //Função retorna a data no formato  - de dd/MM/yyyy para yyyy-MM-dd - 2017-10-22
    public static String getDataUnConvert (String sdata) {
        try {
            DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String inputDateStr = sdata;
            Date date = inputFormat.parse(inputDateStr);
            String outputDateStr = outputFormat.format(date);
            return outputDateStr;
        } catch (ParseException e) {
            Log.d("erro",  e.getMessage());
            e.printStackTrace();
            return sdata;
        }
    }

    //Função retorna a data no formato  - de yyyy-MM-dd para dd/MM/yyyy - 22-10-2017
    public static String getDataConvert (String sdata) {
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            String inputDateStr = sdata;
            Date date = inputFormat.parse(inputDateStr);
            String outputDateStr = outputFormat.format(date);
            return outputDateStr;
        } catch (ParseException e) {
            Log.d("erro",  e.getMessage());
            e.printStackTrace();
            return sdata;
        }
    }

    public static String getDateAgo(String dateExpire) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            Date date = sdf.parse(dateExpire);
            Date now = new Date(System.currentTimeMillis());
            long days = getDateDiff(now, date, TimeUnit.DAYS);

            return days + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }

}
