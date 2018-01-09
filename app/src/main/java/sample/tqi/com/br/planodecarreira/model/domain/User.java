package sample.tqi.com.br.planodecarreira.model.domain;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String photo;
    private Long companyId;
    private String companyName;
    private Boolean flagService;
    private String cpf;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getFlagService() {
        return flagService;
    }

    public void setFlagService(Boolean flagService) {
        this.flagService = flagService;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public class Style implements Serializable {
        private String colorPrimary;
        private String colorPrimaryDark;
        private String colorAccent;
        private String logo;

        public String getColorPrimary() {
            return colorPrimary;
        }

        public void setColorPrimary(String colorPrimary) {
            this.colorPrimary = colorPrimary;
        }

        public String getColorPrimaryDark() {
            return colorPrimaryDark;
        }

        public void setColorPrimaryDark(String colorPrimaryDark) {
            this.colorPrimaryDark = colorPrimaryDark;
        }

        public String getColorAccent() {
            return colorAccent;
        }

        public void setColorAccent(String colorAccent) {
            this.colorAccent = colorAccent;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}

