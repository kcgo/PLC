package sample.tqi.com.br.planodecarreira;

/**
 * Created by alex on 18/10/16.
 */

public interface Presenter<V> {

    void attachView(V view);

    void dettachView();

}