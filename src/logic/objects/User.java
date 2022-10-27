package logic.objects;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.List;

public class User implements Comparable<User>, Serializable {

    private Integer id;
    private String login;
    private String email;
    private String fullName;
    private String password;
    private Timestamp lastPasswordChange;
    private UserStatus status;
    private UserPrivilege privilege;
    private List<Timestamp> lastLogins;

    public User(String Login, String Password) {
        login = Login;
        password = Password;
    }

    public User() {

    }

    public User(
            Integer id, String Login, String Email, String FullName,
            String Password, int LastPasswordChange,
            int Status, int Privilege, List<Timestamp> LastLogins) {

        status
                = (Status == 1) ? UserStatus.ENABLED : UserStatus.DISABLED;
        privilege
                = (Privilege == 1) ? UserPrivilege.ADMIN : UserPrivilege.USER;
    }

    public User(
            Integer id, String Login, String Email, String FullName,
            String Password, Timestamp LastPasswordChange,
            UserStatus Status, UserPrivilege Privilege, List<Timestamp> LastLogins) {
        this.id = id;
        this.login = Login;
        this.email = Email;
        this.fullName = FullName;
        this.password = Password;
        this.lastPasswordChange = LastPasswordChange;
        this.status = Status;
        this.privilege = Privilege;
        this.lastLogins = LastLogins;
    }

    // Getters.
    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getLastPasswordChange() {
        return lastPasswordChange;
    }

    public UserStatus getStatus() {
        return status;
    }

    public UserPrivilege getPrivilege() {
        return privilege;
    }

    public List<Timestamp> getLastLogins() {
        return lastLogins;
    }
   
    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastPasswordChange(Timestamp lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    public void setLastLogins(List<Timestamp> lastLogins) {
        this.lastLogins = lastLogins;
    }
   

    private String lastLoginString() {
        String ls = "";
        lastLogins
                .stream()
                .forEach(t -> ls.concat("\n" + t.toString()));
        return ls;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User usr = (User) obj;
        return id == usr.getId()
                && login.equals(usr.getLogin())
                && email.equals(usr.getEmail())
                && privilege.equals(usr.getPrivilege());
    }

    @Override
    public int compareTo(User usr) {
        return id - usr.getId();
    }

    @Override
    public String toString() {
        return "ID: " + id
                + "\nLogin: " + login
                + "\nEmail: " + email
                + "\nFull Name: " + fullName
                + "\nLast password change: " + lastPasswordChange.toString()
                + "\nStatus:" + status.toString()
                + "\nPrivilege: " + privilege
                + "\nLast login: " + lastLoginString();
    }
}
