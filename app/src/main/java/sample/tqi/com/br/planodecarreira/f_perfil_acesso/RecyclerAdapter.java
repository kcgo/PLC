package sample.tqi.com.br.planodecarreira.f_perfil_acesso;

/**
 * Created by katia.goncalves on 20/12/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sample.tqi.com.br.planodecarreira.R;

public  class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    @Override
    public RecyclerViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType ) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.activity_perfil_acesso,viewGroup );

        return new RecyclerViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder( RecyclerViewHolder holder, int position ) {


    }

    @Override
    public int getItemCount() {

        return 0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public RecyclerViewHolder( View itemView ) {
            super( itemView );
        }
    }
}
