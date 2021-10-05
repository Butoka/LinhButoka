
package ChuongTrinh;


public class NguoiDung {
    public String username;
    public String password;
    public String chucVu;
    public NguoiDung() {
    }

    public NguoiDung(String username, String password, String chucVu) {
        this.username = username;
        this.password = password;
        this.chucVu = chucVu;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
