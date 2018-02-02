package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.roughike.bottombar.BottomBar;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario.ComentarioFragment;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_log.LogFragment;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_status.StatusFragment;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_visao_geral.VisaoGeralFragment;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;
import sample.tqi.com.br.planodecarreira.util.DataStorage;

public class DetalheTarefaActivity extends AppCompatActivity {

    private BottomBar bottomBar;
    private Toolbar toolbar;
    private Tarefa tarefa;
    private TabLayout.Tab tabStatus;
    private int mCurrentTab = 0;
    private VisaoGeralFragment visaogeralfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_tarefa);
        initComponents();
    }

    private void findViewById() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        toolbar = (Toolbar) findViewById(R.id.tb_activity_detalhe_tarefa);

        if (DataStorage.getTypeAccess().equals(getString(R.string.talento))) {
            bottomBar = !tarefa.getEstado_tarefa().equals(getString(R.string.fazer)) ? (BottomBar) findViewById(R.id.bottomBarStatusBlocked) : (BottomBar) findViewById(R.id.bottomBar);
        } else {
            bottomBar = !tarefa.getEstado_tarefa().equals(getString(R.string.feita)) ? (BottomBar) findViewById(R.id.bottomBarStatusBlocked) : (BottomBar) findViewById(R.id.bottomBar);
        }
    }

    private void initComponents() {
        getBundle();
        findViewById();

        bottomBar.setVisibility(View.VISIBLE);
        bottomBar.setClickable(false);

        bottomBar.setTabSelectionInterceptor( ( oldTabId, newTabId ) -> {
            if (DataStorage.getTypeAccess().equals(getString(R.string.talento))) {
                if (newTabId == R.id.tab_status && (!tarefa.getEstado_tarefa().equals(getString(R.string.fazer)))) {
                    return true;
                }
            } else {
                if (newTabId == R.id.tab_status && (!tarefa.getEstado_tarefa().equals(getString(R.string.feita)))) {
                    return true;
                }
            }

            return false;
        } );

        bottomBar.setOnTabSelectListener( tabId -> {
            if (tabId == R.id.tab_geral && mCurrentTab != 0) {
                mCurrentTab = 0;
                replaceFragment(new VisaoGeralFragment(), getIntent().getExtras());

                return;
            }
            if (tabId == R.id.tab_status && mCurrentTab != 1) {
                mCurrentTab = 1;
                replaceFragment(new StatusFragment(), getIntent().getExtras());

                return;
            }
            if (tabId == R.id.tab_comentario && mCurrentTab != 2) {
                mCurrentTab = 2;
                replaceFragment(new ComentarioFragment(), getIntent().getExtras());

                return;
            }
            if (tabId == R.id.tab_log && mCurrentTab != 3) {
                mCurrentTab = 3;
                replaceFragment(new LogFragment(), getIntent().getExtras());

                return;
            }
        } );


        toolbar.setTitle(getString(R.string.st_detail_task));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener( v -> finish() );
        bottomBar.selectTabAtPosition(0);

        loadData();
    }

    public void getBundle(){
        tarefa = (Tarefa) getIntent().getExtras().getSerializable("tarefa");
    }

    public void loadData(){
        new Handler().post(() -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            visaogeralfragment = new VisaoGeralFragment();
            visaogeralfragment.setArguments(getIntent().getExtras());
            fragmentTransaction.add(R.id.main_container, visaogeralfragment, VisaoGeralFragment.class.toString());
            fragmentTransaction.commit();
            bottomBar.setClickable(true);
            mCurrentTab = 0;
        });
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
