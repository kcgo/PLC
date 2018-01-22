package sample.tqi.com.br.planodecarreira.f_tarefa.f_lista_tarefa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.DetalheTarefaActivity;
import sample.tqi.com.br.planodecarreira.model.domain.Modulo;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;

import static sample.tqi.com.br.planodecarreira.util.UIUtil.getDataConvert;

/**
 * Created by alexandre.azevedo on 17/01/2018.
 */

public class ListaTarefaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<Tarefa> tarefas;

    public ListaTarefaAdapter(Context c, List<Tarefa> tarefas) {
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
        this.tarefas = tarefas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vItem = mLayoutInflater.inflate(R.layout.item_lista_tarefa, parent, false);
        ListaTarefaAdapter.ViewHolderItem mvhvItem = new ListaTarefaAdapter.ViewHolderItem(vItem);

        return mvhvItem;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Tarefa tarefa = tarefas.get(position);
        ViewHolderItem vhItem = (ViewHolderItem) holder;

        vhItem.txtTarefa.setText(tarefa.getNome());
        vhItem.txtData.setText(getDataConvert(tarefa.getData_atribuicao()));
        vhItem.txtPeso.setText(String.valueOf(tarefa.getPeso()));
        vhItem.txtStatus.setText(tarefa.getStatus());

        vhItem.btnDesc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetalheTarefaActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tarefas != null ? tarefas.size() : 0;
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        public Button btnDesc;
        public TextView txtTarefa;
        public TextView txtPeso;
        public TextView txtStatus;
        public TextView txtData;

        public ViewHolderItem(View itemView) {
            super(itemView);
            btnDesc = (Button) itemView.findViewById(R.id.btn_desc);
            txtTarefa = (TextView) itemView.findViewById(R.id.txt_nome_lista_tarefa);
            txtPeso = (TextView) itemView.findViewById(R.id.txt_peso_lista_tarefa);
            txtStatus = (TextView) itemView.findViewById(R.id.txt_status_lista_tarefa);
            txtData = (TextView) itemView.findViewById(R.id.txt_data_atribuicao_lista_tarefa);
        }
    }
}