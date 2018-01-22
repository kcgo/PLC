package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_log;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario.ComentarioAdapter;

/**
 * Created by alexandre.azevedo on 18/01/2018.
 */

public class LogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private Context context;

    public LogAdapter(Context c) {
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vItem = mLayoutInflater.inflate(R.layout.item_log, parent, false);
        LogAdapter.ViewHolderItem mvhvItem = new LogAdapter.ViewHolderItem(vItem);

        return mvhvItem;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        public ViewHolderItem(View itemView) {
            super(itemView);
        }
    }
}
