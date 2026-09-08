package model;

public class User {
    // Các thuộc tính cơ bản ban đầu
    private int id;
    private String username;
    private String password;
    private String role;

    // 3 thuộc tính mới nâng cấp
    private String fullName;
    private int points;
    private String membershipTier;

    // 1. CONSTRUCTOR RỖNG (Bắt buộc phải có để DAO gọi được dòng: User u = new User();)
    public User() {
    }

    // 2. CONSTRUCTOR CÓ THAM SỐ (Dành cho các hàm cũ nếu có dùng)
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // --- GETTER VÀ SETTER CHO TOÀN BỘ CÁC BIẾN ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getMembershipTier() { return membershipTier; }
    public void setMembershipTier(String membershipTier) { this.membershipTier = membershipTier; }
}