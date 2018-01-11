package sample.tqi.com.br.planodecarreira.f_perfil_acesso;

import android.content.Context;

import java.util.List;

import sample.tqi.com.br.planodecarreira.model.domain.PerfilAcesso;

/**
 * Created by katia.goncalves on 10/01/2018.
 */

public interface PerfilAcessoView {
    void showError( String string );

     void buildAdapter( List <PerfilAcesso> lista);

}
