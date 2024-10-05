package smart.plantpot.cot.entities;

public class ConnectedObject  {
    private String mail;

    private String fullname;

    private String password;

    private Long permissionLevel;


    public ConnectedObject() {
    }

    public ConnectedObject(String mail, String fullname, String password,Long permissionLevel) {
        this.mail=mail;
        this.fullname=fullname;
        this.password = password;
        this.permissionLevel=permissionLevel;
    }



    public String getmail() {
        return mail;
    }
    public String getfullname() {
        return fullname;
    }
    public String getpassword() {
        return password;
    }
    public Long getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Long permissionLevel) {
        this.permissionLevel = permissionLevel;
    }






}