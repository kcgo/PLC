package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.Comentario;

/**
 * Created by alexandre.azevedo on 17/01/2018.
 */

public class ComentarioAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<Comentario> comentarios;

    public ComentarioAdapter(Context c, List<Comentario> comentarios) {
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
        this.comentarios = comentarios;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vItem = mLayoutInflater.inflate(R.layout.item_comentario, parent, false);
        ComentarioAdapter.ViewHolderItem mvhvItem = new ComentarioAdapter.ViewHolderItem(vItem);

        return mvhvItem;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Comentario comentario = comentarios.get(position);
        ComentarioAdapter.ViewHolderItem vhItem = (ComentarioAdapter.ViewHolderItem) holder;

        vhItem.txtRemetente.setText(comentario.getRemetente());
        vhItem.txtComentario.setText(comentario.getComentario());
    }

    @Override
    public int getItemCount() {
        return comentarios != null ? comentarios.size() : 0;
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        public TextView txtRemetente;
        public TextView txtComentario;

        public ViewHolderItem(View itemView) {
            super(itemView);
            txtRemetente = (TextView) itemView.findViewById(R.id.txt_remetente_comentario);
            txtComentario = (TextView) itemView.findViewById(R.id.txt_comentario_comentario);
        }
    }
}