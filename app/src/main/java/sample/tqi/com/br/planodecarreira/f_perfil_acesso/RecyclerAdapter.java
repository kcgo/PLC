package sample.tqi.com.br.planodecarreira.f_perfil_acesso;

/**
 * Created by katia.goncalves on 20/12/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_Tutor.HomeTutorActivity;
import sample.tqi.com.br.planodecarreira.f_home_talento.HomeTalentoActivity;
import sample.tqi.com.br.planodecarreira.model.domain.PerfilAcesso;

public  class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<PerfilAcesso> perfilAcessos;

    public RecyclerAdapter( Context context, ArrayList <PerfilAcesso> perfilAcessos){
        this.context = context;
        this.perfilAcessos = perfilAcessos;
       }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder= new ViewHolder( itemView );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position ) {
//       holder.ib_perfil.setBackground( perfilAcessos.get( position ).get );
        holder.tv_perfil.setText( perfilAcessos.get( position ).getDescription() );

        holder.cardView.setOnClickListener(view -> {
            if (perfilAcessos.get( position ).getDescription().equals( "Talento" )) {

                Intent intentInstitutional = new Intent(context, HomeTalentoActivity.class);
                intentInstitutional.putExtra( "lista", perfilAcessos );
                context.startActivity(intentInstitutional);
            } else {
                Intent intentNews = new Intent(context, HomeTutorActivity.class);
                intentNews.putExtra( "lista", perfilAcessos );
                context.startActivity(intentNews);
            }
        });
    }

    @Override
    public int getItemCount() {
        return perfilAcessos.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton ib_perfil;
        TextView tv_perfil;
        CardView cardView;

        public ViewHolder( View itemView ) {
            super( itemView );
            ib_perfil = (ImageButton) itemView.findViewById(R.id.ib_perfil);
            tv_perfil = (TextView) itemView.findViewById( R.id.tv_perfil );
            cardView = (CardView) itemView.findViewById( R.id.cv_perfil );
        }
    }
}
