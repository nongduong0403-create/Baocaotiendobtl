package model;

public class Showtime {
    private int id;
    private int movieId;
    private int roomId;
    private String roomName; // Tên rạp (lấy từ bảng Room để hiển thị cho đẹp)
    private String showTime; // Giờ chiếu

    public Showtime() {}

    public Showtime(int id, int movieId, int roomId, String roomName, String showTime) {
        this.id = id;
        this.movieId = movieId;
        this.roomId = roomId;
        this.roomName = roomName;
        this.showTime = showTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    public String getShowTime() { return showTime; }
    public void setShowTime(String showTime) { this.showTime = showTime; }
}