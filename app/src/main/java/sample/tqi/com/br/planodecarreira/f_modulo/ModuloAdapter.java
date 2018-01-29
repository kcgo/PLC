package sample.tqi.com.br.planodecarreira.f_modulo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_lista_tarefa.ListaTarefaAdapter;
import sample.tqi.com.br.planodecarreira.model.domain.Feedback;
import sample.tqi.com.br.planodecarreira.model.domain.Tarefa;

/**
 * Created by alexandre.azevedo on 16/01/2018.
 */

public class ModuloAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<Feedback> feedbacks;

    public ModuloAdapter( Context c , List<Feedback> feedbacks) {
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
        this.feedbacks = feedbacks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vItem = mLayoutInflater.inflate(R.layout.item_modulo, parent, false);
        ModuloAdapter.ViewHolderItem mvhvItem = new ModuloAdapter.ViewHolderItem(vItem);

        return mvhvItem;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Feedback feedback = feedbacks.get(position);
        ModuloAdapter.ViewHolderItem vhItem = (ModuloAdapter.ViewHolderItem) holder;

        vhItem.txtRementente.setText(feedback.getRemtente());
        vhItem.txtFeedback.setText(feedback.getFeedback());
    }

    @Override
    public int getItemCount() {
        return feedbacks != null ? feedbacks.size() : 0;
    }


    class ViewHolderItem extends RecyclerView.ViewHolder {
        public TextView txtRementente;
        public TextView txtFeedback;

        public ViewHolderItem(View itemView) {
            super(itemView);
            txtRementente = (TextView) itemView.findViewById(R.id.tv_remetente_item_modulo);
            txtFeedback = (TextView) itemView.findViewById(R.id.tv_feedback_item_modulo);
        }
    }
}
