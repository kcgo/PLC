package sample.tqi.com.br.planodecarreira.f_Tutor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.ListaTalentos;

/**
 * Created by katia.goncalves on 17/01/2018.
 */

public class RecyclerAdapterTutor extends RecyclerView.Adapter <RecyclerAdapterTutor.ViewHolder> {
    Context context;
    List<ListaTalentos> homeTutor;


    public RecyclerAdapterTutor ( Context context, List <ListaTalentos> homeTutor ) {
        this.context = context;
        this.homeTutor = homeTutor;

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tutor, parent, false);
        ViewHolder viewHolder= new ViewHolder( itemView );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position ) {
        holder.tv_talento.setText( homeTutor.get( position ).getTalento() );
        holder.tv_id.setText( String.valueOf(homeTutor.get(position).getId()) );

    }

    @Override
    public int getItemCount() {
        return homeTutor.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_talento;
        TextView tv_unidade;
        TextView tv_status;
        TextView tv_id;

        public ViewHolder( View itemView ) {
            super( itemView );
            tv_id = (TextView) itemView.findViewById( R.id.tv_id);
            tv_talento = (TextView) itemView.findViewById(R.id.tv_talento);
            tv_unidade = (TextView) itemView.findViewById( R.id.tv_unidade );
            tv_status = (TextView) itemView.findViewById( R.id.tv_status);
        }
    }
}