package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_visao_geral;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sample.tqi.com.br.planodecarreira.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisaoGeralFragment extends Fragment {

    public VisaoGeralFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visao_geral, container, false);
    }
}