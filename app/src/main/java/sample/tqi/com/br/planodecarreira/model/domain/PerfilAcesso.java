package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

/**
 * Created by katia.goncalves on 10/01/2018.
 */

public class PerfilAcesso implements Serializable {

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
}
