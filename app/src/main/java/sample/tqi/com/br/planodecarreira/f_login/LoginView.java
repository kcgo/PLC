package sample.tqi.com.br.planodecarreira.f_login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface LoginView {

    void showSuccess();

    void showError(String message);
}
