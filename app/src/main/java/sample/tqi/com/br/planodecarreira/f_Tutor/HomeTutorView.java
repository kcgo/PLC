package sample.tqi.com.br.planodecarreira.f_Tutor;

import java.util.List;

import sample.tqi.com.br.planodecarreira.model.domain.Talento;

/**
 * Created by katia.goncalves on 17/01/2018.
 */

public interface HomeTutorView {

    void showError( String string );

    void buildAdapter( List <Talento> listatalentos );




}



