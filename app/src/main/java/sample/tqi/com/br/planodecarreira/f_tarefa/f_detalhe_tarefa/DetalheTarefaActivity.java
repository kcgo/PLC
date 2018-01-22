package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario.ComentarioFragment;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_log.LogFragment;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_status.StatusFragment;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_visao_geral.VisaoGeralFragment;

public class DetalheTarefaActivity extends AppCompatActivity {

    private BottomBar bottomBar;
    private Toolbar toolbar;
    private int mCurrentTab = 0;
    private VisaoGeralFragment visaogeralfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_tarefa);

        findViewById();

        new Handler().post(() -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            visaogeralfragment = new VisaoGeralFragment();
            fragmentTransaction.add(R.id.main_container, visaogeralfragment, VisaoGeralFragment.class.toString());
            fragmentTransaction.commit();
            bottomBar.setClickable(true);
            mCurrentTab = 0;
        });

        bottomBar.setVisibility(View.VISIBLE);
        bottomBar.setClickable(false);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_geral && mCurrentTab != 0) {
                    mCurrentTab = 0;
                    replaceFragment(new VisaoGeralFragment());


                    return;
                }
                if (tabId == R.id.tab_status && mCurrentTab != 1) {
                    mCurrentTab = 1;
                    replaceFragment(new StatusFragment());

                    return;
                }
                if (tabId == R.id.tab_comentario && mCurrentTab != 2) {
                    mCurrentTab = 2;
                    replaceFragment(new ComentarioFragment());

                    return;
                }
                if (tabId == R.id.tab_log && mCurrentTab != 3) {
                    mCurrentTab = 3;
                    replaceFragment(new LogFragment());

                    return;
                }
            }
        });

        toolbar.setTitle(getString(R.string.st_detail_task));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bottomBar.selectTabAtPosition(0);
    }

    private void findViewById() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        toolbar = (Toolbar) findViewById(R.id.tb_activity_detalhe_tarefa);
    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, null);
    }

    public void replaceFragment(Fragment fragment, Bundle params) {
        fragment.setArguments(params);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout, R.anim.fadein, R.anim.fadeout);
        transaction
                .replace(R.id.main_container, fragment, fragment.getClass().toString()).addToBackStack(null).commit();
    }
}
