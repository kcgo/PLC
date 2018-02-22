package sample.tqi.com.br.planodecarreira.f_home_talento;

import android.view.MenuItem;

import java.util.List;

import sample.tqi.com.br.planodecarreira.model.domain.Modulo;

/**
 * Created by alexandre.azevedo on 18/01/2018.
 */

public interface HomeTalentoView {

    boolean onOptionItemSelected( MenuItem item );

    void showSuccess( Modulo modulo);

    void buildList(List<Modulo> modulos);

    void showError(String message);



    void sair( MenuItem item );

    void AlterarPerfil(MenuItem item);
}
