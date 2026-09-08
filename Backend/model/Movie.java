package model;

public class Movie {
    // Các thuộc tính cơ bản
    private int id;
    private String title;
    private String posterUrl;
    private int duration;
    private String description;

    // 3 thuộc tính mới nâng cấp
    private int status;
    private String ageRating;
    private boolean isHot;

    // Constructor rỗng
    public Movie() {
    }

    // Constructor 5 tham số (Đang được dùng trong MovieDAO)
    public Movie(int id, String title, String posterUrl, int duration, String description) {
        this.id = id;
        this.title = title;
        this.posterUrl = posterUrl;
        this.duration = duration;
        this.description = description;
    }

    // --- GETTER VÀ SETTER CHO TOÀN BỘ CÁC BIẾN ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getAgeRating() { return ageRating; }
    public void setAgeRating(String ageRating) { this.ageRating = ageRating; }

    public boolean isHot() { return isHot; }
    public void setHot(boolean isHot) { this.isHot = isHot; }
}