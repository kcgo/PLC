package sample.tqi.com.br.planodecarreira.f_perfil_acesso;

/**
 * Created by katia.goncalves on 20/12/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.model.domain.PerfilAcesso;

public  class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<PerfilAcesso> perfilAcessos;


    public RecyclerAdapter(Context context, List<PerfilAcesso>perfilAcessos){
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
//        holder.ib_perfil.setBackground( perfilAcessos.get( position ).get );
        holder.tv_perfil.setText( perfilAcessos.get( position ).getRoleName() );

    }

    @Override
    public int getItemCount() {
        return perfilAcessos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton ib_perfil;
        TextView tv_perfil;

        public ViewHolder( View itemView ) {
            super( itemView );
            ib_perfil = (ImageButton) itemView.findViewById(R.id.ib_perfil);
            tv_perfil = (TextView) itemView.findViewById( R.id.tv_perfil );
        }
    }
}
