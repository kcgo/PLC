package sample.tqi.com.br.planodecarreira.f_home_talento;

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
import sample.tqi.com.br.planodecarreira.f_modulo.ModuloActivity;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_lista_tarefa.ListaTarefaAdapter;
import sample.tqi.com.br.planodecarreira.model.domain.Modulo;

import static sample.tqi.com.br.planodecarreira.util.UIUtil.getDataConvert;

/**
 * Created by alexandre.azevedo on 15/01/2018.
 */

public class HomeTalentoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<Modulo> modulos;

    public HomeTalentoAdapter(Context c, List<Modulo> modulos) {
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
        this.modulos = modulos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vItem = mLayoutInflater.inflate(R.layout.item_career_history, parent, false);
        HomeTalentoAdapter.ViewHolderItem mvhvItem = new HomeTalentoAdapter.ViewHolderItem(vItem);

        return mvhvItem;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Modulo modulo = modulos.get(position);
        ViewHolderItem vhItem = (ViewHolderItem) holder;

        vhItem.txtModulo.setText(modulo.getNome());
        vhItem.txtDataFinal.setText(getDataConvert(modulo.getData_final()));
        vhItem.txtNivel.setText(modulo.getNivel());
        vhItem.txtArea.setText(modulo.getArea());

        vhItem.btnDesc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ModuloActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modulos != null ? modulos.size() : 0;
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        public Button btnDesc;
        public TextView txtModulo;
        public TextView txtDataFinal;
        public TextView txtNivel;
        public TextView txtArea;

        public ViewHolderItem(View itemView) {
            super(itemView);
            btnDesc = (Button) itemView.findViewById(R.id.btn_desc);
            txtModulo = (TextView) itemView.findViewById(R.id.txt_modulo_career_history);
            txtDataFinal = (TextView) itemView.findViewById(R.id.txt_data_final_career_history);
            txtNivel = (TextView) itemView.findViewById(R.id.txt_nivel_career_history);
            txtArea = (TextView) itemView.findViewById(R.id.txt_area_career_history);
        }
    }
}