package VTNgoc;

import java.util.Objects;
/**
 *
 * @author Vu Tuan Ngoc
 */
public class Account {

    private String fullName;
    private String user;
    private String password;
    private String role;
    private int status;
    private String phone;

    public Account() {
        super();
    }

    public Account(String fullName, String user, String password, String role) {
        super();
        this.fullName = fullName;
        this.user = user;
        this.password = password;
        this.role = role;

    }

    public Account(String fullName, String user, String password, String role, String email) {
        super();
        this.fullName = fullName;
        this.user = user;
        this.password = password;
        this.role = role;

        this.phone = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.fullName);
        hash = 47 * hash + Objects.hashCode(this.user);
        hash = 47 * hash + Objects.hashCode(this.password);
        hash = 47 * hash + Objects.hashCode(this.role);
        hash = 47 * hash + this.status;
        hash = 47 * hash + Objects.hashCode(this.phone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return Objects.equals(this.phone, other.phone);
    }

    @Override
    public String toString() {
        return "Account{" + "fullName=" + fullName + ", user=" + user + ", password=" + password + ", role=" + role + ", status=" + status + ", email=" + phone + '}';
    }

}
