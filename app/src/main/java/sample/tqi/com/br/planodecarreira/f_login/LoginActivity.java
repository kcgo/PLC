package sample.tqi.com.br.planodecarreira.f_login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import sample.tqi.com.br.planodecarreira.f_perfil_acesso.PerfilAcessoActivity;
import sample.tqi.com.br.planodecarreira.ui.WaitDialog;
import sample.tqi.com.br.planodecarreira.util.DataStorage;
import sample.tqi.com.br.planodecarreira.util.UIUtil;

public class LoginActivity extends Activity implements LoginView {
    private static final String TAG = LoginActivity.class.getSimpleName();

    public static final String SITE_KEY="6LfEzD8UAAAAAFt-MIRl3OzSxGBCN3totM3wtmPe";
    public static final String SITE_SECRET_KEY="6LfEzD8UAAAAAIKdVayFXOku7qe49iUuB_T8q0gJ";
    String userResponseToken;

    private WaitDialog waitDialog;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;
    private LoginPresenter loginPresenter;
    private EditText edit_text_usuário;
    private EditText edit_text_senha;
    private Switch sw_salvar_usuario;
    private CheckedTextView btn_Recaptcha;
    private View v;
    private Button bt_acessar;


    private String user_name;
    private InputMethodService.InputMethodSessionImpl inputMethodManager;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        Fabric.with( this, new Crashlytics() );
        setContentView( R.layout.activity_login );

        loginPresenter = new LoginPresenter();
        loginPresenter.attachView( this );
        waitDialog = new WaitDialog(this);
        userResponseToken = "";

        edit_text_usuário = findViewById( R.id.edit_text_usuário );
        edit_text_senha = findViewById( R.id.edit_text_senha );
        sw_salvar_usuario = findViewById( R.id.sw_salvar_usuario );
        btn_Recaptcha = findViewById( R.id.recaptcha );



        Button button = findViewById( R.id.bt_acessar );
        button.setOnClickListener( view -> {
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
        } );


        btn_Recaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                if (btn_Recaptcha.isChecked())
                    btn_Recaptcha.setChecked(false);
                else {
                    SafetyNet.getClient(LoginActivity.this).verifyWithRecaptcha(SITE_KEY)
                            .addOnSuccessListener(LoginActivity.this, new OnSuccessListener <SafetyNetApi.RecaptchaTokenResponse>() {
                                @Override
                                public void onSuccess( SafetyNetApi.RecaptchaTokenResponse response ) {
                                    userResponseToken = response.getTokenResult();

                                    if (!userResponseToken.isEmpty()) {
                                        sendRequest();
                                    }
                                }
                            })
                            .addOnFailureListener(LoginActivity.this, new OnFailureListener() {
                                public void onFailure( @NonNull Exception e ) {
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
            }
        });

        user_name = DataStorage.getUserName() != null ? DataStorage.getUserName() : "";
        if (!user_name.equals("")){
            edit_text_usuário.setText(user_name);
        }
        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

        // Habilita evento para esconder o teclado ao clicar fora de um campo.
        UIUtil.searchAndHideSoftKeybordFromView(findViewById(android.R.id.content), LoginActivity.this);
    }

    public void onLoadFinished( Loader<Object> loader, Object data) {
    /* parameters not valid ... */

    /* show keyboard */
        ((InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE))
                .toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);

    /* parameters valid ... */
    }

    public Boolean fieldsValidade(){
        Boolean retorno = true;
        if (edit_text_senha.getText().toString().isEmpty()){
            retorno = false;
        } else if (edit_text_senha.getText().equals(""))
        {
            retorno= false;
        }
        return retorno;
    }

    public void sendRequest() {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject obj = new JSONObject( response );
                        if (obj.getString( "success" ).equals( "true" )) {
                            btn_Recaptcha.setChecked(true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText( LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG ).show()) {

            @Override
            protected Map <String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap <>();
                params.put( "secret", SITE_SECRET_KEY );
                params.put( "response", userResponseToken );
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void moveNewActivity() {
        Intent intent = new Intent( getApplicationContext(), PerfilAcessoActivity.class );
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
}