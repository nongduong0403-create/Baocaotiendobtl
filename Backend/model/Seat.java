package model;

public class Seat {
    private int id;
    private int roomId;
    private String rowName;
    private int seatNumber;
    private String type;
    private String status; // THUỘC TÍNH MỚI: Trạng thái ghế

    public Seat(int id, int roomId, String rowName, int seatNumber, String type, String status) {
        this.id = id;
        this.roomId = roomId;
        this.rowName = rowName;
        this.seatNumber = seatNumber;
        this.type = type;
        this.status = status;
    }

    public int getId() { return id; }
    public int getRoomId() { return roomId; }
    public String getRowName() { return rowName; }
    public int getSeatNumber() { return seatNumber; }
    public String getType() { return type; }
    public String getStatus() { return status; } // HÀM GET MỚI
}