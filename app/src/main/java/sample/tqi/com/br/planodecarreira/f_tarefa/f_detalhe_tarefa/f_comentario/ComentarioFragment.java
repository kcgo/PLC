package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario.ComentarioAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComentarioFragment extends Fragment {

    private RecyclerView recyclerView;

    public ComentarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_comentario, container, false);

        findViewById(layout);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        ComentarioAdapter adapter = new ComentarioAdapter(getContext());
        recyclerView.setAdapter(adapter);

        return layout;
    }

    private void findViewById(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list_comment);
    }
}