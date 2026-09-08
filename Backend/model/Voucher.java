package model;

import java.util.Date;

public class Voucher {
    private int id;
    private String code;
    private String discountType; // 'FIXED' hoặc 'PERCENT'
    private int discountValue;   // Giá trị giảm (VD: 50000 hoặc 20 cho 20%)
    private int minOrderValue;   // Giá trị đơn tối thiểu
    private Date expirationDate;
    private int usageLimit;
    private int status;

    // Constructor không tham số
    public Voucher() {}

    // Getters và Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDiscountType() { return discountType; }
    public void setDiscountType(String discountType) { this.discountType = discountType; }

    public int getDiscountValue() { return discountValue; }
    public void setDiscountValue(int discountValue) { this.discountValue = discountValue; }

    public int getMinOrderValue() { return minOrderValue; }
    public void setMinOrderValue(int minOrderValue) { this.minOrderValue = minOrderValue; }

    public Date getExpirationDate() { return expirationDate; }
    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }

    public int getUsageLimit() { return usageLimit; }
    public void setUsageLimit(int usageLimit) { this.usageLimit = usageLimit; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}