package sample.tqi.com.br.planodecarreira.f_perfil_acesso;

import java.util.ArrayList;

import sample.tqi.com.br.planodecarreira.model.domain.PerfilAcesso;

/**
 * Created by katia.goncalves on 10/01/2018.
 */

public interface PerfilAcessoView {
    void showError( String string );

     void buildAdapter( ArrayList <PerfilAcesso> lista);

}
