package model;

public class Room {
    private int id;
    private String name;

    // Constructor (Hàm khởi tạo)
    public Room(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Các hàm Getter để lấy dữ liệu
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Hàm này giúp in ra màn hình cho đẹp
    @Override
    public String toString() {
        return "Tên phòng: " + name;
    }
}