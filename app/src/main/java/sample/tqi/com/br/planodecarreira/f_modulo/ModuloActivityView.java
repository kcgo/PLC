package sample.tqi.com.br.planodecarreira.f_modulo;

import java.util.List;

import sample.tqi.com.br.planodecarreira.model.domain.Modulo;

/**
 * Created by katia.goncalves on 22/01/2018.
 */

public interface ModuloActivityView {
    void showSuccess( Modulo modulo );

    void showError( String string );

    void buildList( List<Modulo> modulos );
}
