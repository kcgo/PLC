package sample.tqi.com.br.planodecarreira;

import android.app.Application;

/**
 * Created by alexandre.azevedo on 01/11/2017.
 */

public class PlanoCarreiraApplication extends Application {

    private static volatile PlanoCarreiraApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static final PlanoCarreiraApplication get() {
        return sInstance;
    }
}