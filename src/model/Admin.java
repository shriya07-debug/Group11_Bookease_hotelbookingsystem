package model;

public class Admin {
    private int id;
    private String fullName;
    private String email;
    private String phone;
    private String photoPath;

    public Admin(int id, String fullName, String email, String phone, String photoPath) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.photoPath = photoPath;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPhotoPath() { return photoPath; }
}
