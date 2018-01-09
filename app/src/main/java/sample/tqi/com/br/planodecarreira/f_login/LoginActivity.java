package sample.tqi.com.br.planodecarreira.f_login;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import io.fabric.sdk.android.Fabric;
import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_perfil_acesso.PerfilAcesso;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

public class LoginActivity extends Activity implements LoginView {
    private static final String TAG = LoginActivity.class.getSimpleName();

    public static final String SITE_KEY = "6LfEzD8UAAAAAFt-MIRl3OzSxGBCN3totM3wtmPe";
    public static final String SITE_SECRET_KEY = "6LfEzD8UAAAAAIKdVayFXOku7qe49iUuB_T8q0gJ";
    String userResponseToken;

    private WaitDialog waitDialog;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;
    private LoginPresenter loginPresenter;
    private EditText edit_text_usuário;
    private EditText edit_text_senha;
    private Switch sw_salvar_usuario;
    private Button btn_Recaptcha;
    private View v;

    private String user_name;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
        waitDialog = new WaitDialog(this);
        userResponseToken = "";

        edit_text_usuário = findViewById(R.id.edit_text_usuário);
        edit_text_senha = findViewById(R.id.edit_text_senha);
        sw_salvar_usuario = findViewById(R.id.sw_salvar_usuario);
        btn_Recaptcha = findViewById(R.id.recaptcha);

        Button button = findViewById(R.id.bt_acessar);
        button.setOnClickListener(view -> {
            if (fieldsValidade()) {
                waitDialog.show();
                if (sw_salvar_usuario.isChecked()) {
                    DataStorage.setSaveUser(edit_text_usuário.getText().toString());
                } else {
                    DataStorage.setSaveUser("");
                }
                if (btn_Recaptcha.getVisibility() == View.VISIBLE) {
                    if (!userResponseToken.isEmpty()) {
                        loginPresenter.validadeCaptcha(edit_text_usuário.getText().toString(), edit_text_senha.getText().toString(), userResponseToken, LoginActivity.this);
                    } else {
                        waitDialog.dismiss();
                        Context context = getApplicationContext();
                        CharSequence text = "Valide o captcha";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                } else {
                    loginPresenter.login(edit_text_usuário.getText().toString(), edit_text_senha.getText().toString(), LoginActivity.this);
                }
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Informe todos os campos";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        user_name = DataStorage.getUserName() != null ? DataStorage.getUserName() : "";
        if (!user_name.equals("")) {
            edit_text_usuário.setText(user_name);
        }
    }

    public Boolean fieldsValidade() {
        Boolean retorno = true;
        if (edit_text_senha.getText().toString().isEmpty()) {
            retorno = false;
        } else if (edit_text_senha.getText().equals("")) {
            retorno = false;
        }
        return retorno;
    }


    public void onClickListner( View v ) {
        CheckBox box = findViewById(R.id.recaptcha);
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()) {
            case R.id.recaptcha:
                if (checked) {
                    box.setAnimation(box.getAnimation());
                } else box.setAnimation(null);
        }
    }





    public void recaptchaClick(View v){
        SafetyNet.getClient(this).verifyWithRecaptcha(SITE_KEY)
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                        userResponseToken = response.getTokenResult();
                        if (!userResponseToken.isEmpty()) {
                           sendRequest();
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                       public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            Log.d(TAG, "Error message: " +
                                    CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        } else {
                            Log.d(TAG, "Unknown type of error: " + e.getMessage());
                        }
                    }
                });
    }

    public void sendRequest() {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener <String>() {
                    @Override
                    public void onResponse( String response ) {
                        try {
                            JSONObject obj = new JSONObject( response );
                            if (obj.getString( "success" ).equals( "true" )) {
                               // moveNewActivity();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse( VolleyError error ) {
                    Toast.makeText( LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG ).show();
                }
            }) {

            @Override
            protected Map <String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap <>();
                params.put( "secret", SITE_SECRET_KEY );
                params.put( "response", userResponseToken );
                return params;
            }
        };
    }

    private void moveNewActivity() {
        Intent intent = new Intent( getApplicationContext(), PerfilAcesso.class );
        startActivity( intent );
        finish();
    }

    @Override
    public void showSuccess() {
        waitDialog.dismiss();
        moveNewActivity();
    }

    @Override
    public void showError( String message ) {
        waitDialog.dismiss();
        btn_Recaptcha.setVisibility(View.VISIBLE);
        edit_text_senha.setText("");
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        View v = inflater.inflate(R.layout.activity_login, container, false);
    }
}