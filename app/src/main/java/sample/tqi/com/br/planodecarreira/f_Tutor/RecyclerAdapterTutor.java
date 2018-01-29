package sample.tqi.com.br.planodecarreira.f_Tutor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_modulo_tutor.ModuloTutorActivity;
import sample.tqi.com.br.planodecarreira.model.domain.Talento;

/**
 * Created by katia.goncalves on 17/01/2018.
 */

public class RecyclerAdapterTutor extends RecyclerView.Adapter <RecyclerAdapterTutor.ViewHolder> {
    Context context;
    List <Talento> listaTalentos;

    public RecyclerAdapterTutor( Context context, List <Talento> listaTalentos ) {
        this.context = context;
        this.listaTalentos = listaTalentos;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.list_item_tutor, parent, false );
        ViewHolder viewHolder = new ViewHolder( itemView );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position ) {
        Talento talento = listaTalentos.get(position);
        holder.tv_talento.setText( String.valueOf( talento.getNome() ) );
        holder.tv_status.setText( talento.getStatus() );
        holder.tv_unidade.setText( talento.getUnidade() );

        holder.btn_next_tarefas.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Bundle bundle = new Bundle();
                bundle.putSerializable( "talento", talento);
                Intent intent = new Intent(context, ModuloTutorActivity.class );
                intent.putExtras( bundle );
                context.startActivity( intent );
            }
        } );

    }
    @Override
    public int getItemCount() {
        return (listaTalentos != null? listaTalentos.size(): 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_talento;
        TextView tv_unidade;
        TextView tv_status;
        TextView tv_id;
        Button btn_next_tarefas;

        public ViewHolder( View itemView ) {
            super( itemView );
            tv_talento = (TextView) itemView.findViewById( R.id.tv_talento );
            tv_unidade = (TextView) itemView.findViewById( R.id.tv_unidade );
            tv_status = (TextView) itemView.findViewById( R.id.tv_status );
            btn_next_tarefas =(Button) itemView.findViewById( R.id.btn_next_tarefas );
        }
    }
}

