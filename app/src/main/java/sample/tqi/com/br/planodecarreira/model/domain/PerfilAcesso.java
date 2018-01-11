package sample.tqi.com.br.planodecarreira.model.domain;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.io.Serializable;

/**
 * Created by katia.goncalves on 10/01/2018.
 */

public class PerfilAcesso implements Serializable, Adapter {

    private String roleName;
    private String description;
    private String authority;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName( String roleName ) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority( String authority ) {
        this.authority = authority;
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
