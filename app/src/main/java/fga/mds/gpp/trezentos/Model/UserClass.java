package fga.mds.gpp.trezentos.Model;


import fga.mds.gpp.trezentos.Exception.UserException;

public class UserClass {

    private String className;
    private String institution;
    private float cutOff;
    private Integer idUserAccount;
    private String password;
    private float addition;

    public UserClass(){


    }

    public UserClass(String className, String institution, float cutOff, String password,
                     Integer idUserAccount, float addition) throws UserException{

        this.className = className;
        this.institution = institution;
        this.cutOff = cutOff;
        this.password = password;
        this.idUserAccount = idUserAccount;
        this.addition = addition;

    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getAddition() {
        return addition;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setIdUserAccount(Integer idUserAccount) {
        this.idUserAccount = idUserAccount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddition(float addition) {
        this.addition = addition;
    }

    public void setCutOff(float cutOff) {
        this.cutOff = cutOff;
    }

    public String getClassName() {
        return className;
    }

    public Integer getIdUserAccount() {
        return idUserAccount;
    }

    public String getPassword() {
        return password;
    }

    public String getInstitution() {
        return institution;
    }

    public float getCutOff() {
        return cutOff;
    }
}