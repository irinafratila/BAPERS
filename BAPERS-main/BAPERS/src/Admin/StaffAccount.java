package Admin;

public class StaffAccount {
    private int staffId;
    String name,useName,password,address,role,phoneNumber;

    public StaffAccount(int staffId, String name, String useName, String password, String address, String role, String phoneNumber) {
        this.staffId = staffId;
        this.name = name;
        this.useName = useName;
        this.password = password;
        this.address = address;
        this.role = role;
        this.phoneNumber = phoneNumber;


    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}