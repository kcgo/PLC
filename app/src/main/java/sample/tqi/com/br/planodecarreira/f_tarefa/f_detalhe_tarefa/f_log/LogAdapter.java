package sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_log;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sample.tqi.com.br.planodecarreira.R;
import sample.tqi.com.br.planodecarreira.f_home_talento.HomeTalentoAdapter;
import sample.tqi.com.br.planodecarreira.f_tarefa.f_detalhe_tarefa.f_comentario.ComentarioAdapter;
import sample.tqi.com.br.planodecarreira.model.domain.Log;
import sample.tqi.com.br.planodecarreira.model.domain.Modulo;

import static sample.tqi.com.br.planodecarreira.util.UIUtil.getDataConvert;

/**
 * Created by alexandre.azevedo on 18/01/2018.
 */

public class LogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<Log> logs;

    public LogAdapter(Context c, List<Log> logs) {
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
        this.logs = logs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vItem = mLayoutInflater.inflate(R.layout.item_log, parent, false);
        LogAdapter.ViewHolderItem mvhvItem = new LogAdapter.ViewHolderItem(vItem);

        return mvhvItem;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log log = logs.get(position);
        LogAdapter.ViewHolderItem vhItem = (LogAdapter.ViewHolderItem) holder;

        vhItem.txtStatus.setText(log.getStatus());
        vhItem.txtData.setText(log.getData_alteracao());
//        vhItem.txtData.setText(getDataConvert(log.getData_alteracao()));
        vhItem.txtObs.setText(log.getObservacao());
        vhItem.txtObs.setVisibility(log.getObservacao() == null ? View.GONE: View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return logs != null ? logs.size() : 0;
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        public TextView txtStatus;
        public TextView txtData;
        public TextView txtObs;

        public ViewHolderItem(View itemView) {
            super(itemView);
            txtStatus = (TextView) itemView.findViewById(R.id.txt_status_log);
            txtData = (TextView) itemView.findViewById(R.id.txt_data_log);
            txtObs = (TextView) itemView.findViewById(R.id.txt_obs_log);
        }
    }
}
