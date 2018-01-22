package sample.tqi.com.br.planodecarreira.model.domain;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.io.Serializable;

/**
 * Created by katia.goncalves on 17/01/2018.
 */

public class ListaTalentos implements Serializable, Adapter {
    private long id;
    private String talento;

    public ListaTalentos( long id, String talento ) {
        this.setId( id );
        this.setTalento( talento );
    }

    public void setTalento( String talento ) {
        this.talento = talento;
    }

    public String getTalento() {
        return talento;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }



    @Override
    public void registerDataSetObserver( DataSetObserver observer ) {

    }

    @Override
    public void unregisterDataSetObserver( DataSetObserver observer ) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem( int position ) {
        return null;
    }

    @Override
    public long getItemId( int position ) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        return null;
    }

    @Override
    public int getItemViewType( int position ) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


}
