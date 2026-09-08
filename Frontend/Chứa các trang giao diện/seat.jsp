<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chọn ghế - Rạp số ${roomId}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .cinema-hall { background-color: #ffffff; border: 1px solid #e9ecef; border-radius: 12px; padding: 40px 20px; overflow-x: auto; box-shadow: inset 0 0 20px rgba(0,0,0,0.02); }
        .cinema-screen { background: linear-gradient(to bottom, #e9ecef, #ffffff); height: 15px; border-radius: 50% / 100% 100% 0 0; border-top: 4px solid #ced4da; margin-bottom: 60px; position: relative; box-shadow: 0 -5px 15px rgba(0,0,0,0.03); }
        .cinema-screen::before { content: 'MÀN HÌNH CHÍNH'; position: absolute; top: -30px; width: 100%; text-align: center; color: #6c757d; font-size: 0.85rem; font-weight: bold; letter-spacing: 2px; }
        .seat-grid { display: grid; grid-template-columns: repeat(15, 1fr); gap: 6px; justify-content: center; min-width: 650px; }
        .seat-item { display: flex; align-items: center; justify-content: center; height: 32px; width: 100%; border-radius: 5px; font-weight: bold; font-size: 0.75rem; text-decoration: none; color: white; transition: all 0.2s; cursor: pointer; }
        .seat-item:hover { transform: scale(1.15); box-shadow: 0 4px 10px rgba(0,0,0,0.2); color: white; z-index: 10; }

        .seat-standard { background-color: #28a745; }
        .seat-vip { background-color: #dc3545; }
        .seat-booked { background-color: #343a40; color: #6c757d; pointer-events: none; box-shadow: inset 0 3px 5px rgba(0,0,0,0.3); }

        /* HIỆU ỨNG MỚI: Ghế đang được chọn (Màu cam nổi bật) */
        .seat-selected { background-color: #fd7e14 !important; color: white; transform: scale(1.15); box-shadow: 0 0 12px rgba(253,126,20,0.6); border: 2px solid white; z-index: 10; }

        .summary-card { position: sticky; top: 20px; }
        .step-nav { font-size: 0.9rem; font-weight: 500; color: #6c757d; }
        .step-nav .active { color: #0d6efd; font-weight: bold; }
        .legend-box { width: 22px; height: 22px; border-radius: 4px; display: inline-block; vertical-align: middle; }
    </style>
</head>
<body class="d-flex flex-column" style="min-height: 100vh;">

<jsp:include page="header.jsp" />

<div class="bg-white border-bottom py-3 mb-4 shadow-sm">
    <div class="container text-center step-nav">
        <span>Chọn phim / Rạp / Suất</span> &nbsp; > &nbsp;
        <span class="active">Chọn ghế</span> &nbsp; > &nbsp;
        <span>Thanh toán</span>
    </div>
</div>

<div class="container flex-grow-1 mb-5">
    <div class="row">
        <div class="col-lg-9 col-md-8 mb-4">
            <div class="card shadow-sm border-0 p-4">
                <h5 class="fw-bold text-center mb-4 text-primary">RẠP SỐ ${roomId}</h5>
                <div class="cinema-hall">
                    <div class="cinema-screen"></div>
                    <div class="seat-grid">
                        <c:forEach items="${seatList}" var="s">
                            <c:choose>
                                <c:when test="${s.status == 'AVAILABLE'}">
                                    <!-- Gọi hàm Javascript toggleSeat khi click -->
                                    <div onclick="toggleSeat(this, '${s.id}', '${s.rowName}${s.seatNumber}', '${s.type}')"
                                         class="seat-item ${s.type == 'VIP' ? 'seat-vip' : 'seat-standard'}"
                                         title="Ghế ${s.rowName}${s.seatNumber} - Loại: ${s.type}">
                                            ${s.rowName}${s.seatNumber}
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="seat-item seat-booked">${s.rowName}${s.seatNumber}</div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>

                <div class="d-flex justify-content-center gap-4 mt-4 flex-wrap">
                    <div class="d-flex align-items-center gap-2"><span class="legend-box seat-standard"></span> <span class="small fw-medium">Ghế Thường (65k)</span></div>
                    <div class="d-flex align-items-center gap-2"><span class="legend-box seat-vip"></span> <span class="small fw-medium">Ghế VIP (85k)</span></div>
                    <div class="d-flex align-items-center gap-2"><span class="legend-box" style="background-color:#fd7e14;"></span> <span class="small fw-medium">Đang chọn</span></div>
                    <div class="d-flex align-items-center gap-2"><span class="legend-box seat-booked"></span> <span class="small fw-medium">Đã Bán</span></div>
                </div>
            </div>
        </div>

        <!-- CỘT PHẢI: HÓA ĐƠN TỰ ĐỘNG -->
        <div class="col-lg-3 col-md-4">
            <div class="card summary-card shadow-sm border-0">
                <div class="card-body p-4">
                    <h5 class="fw-bold mb-3 border-bottom pb-2">🎫 Vé của bạn</h5>

                    <!-- Form để gửi danh sách ghế đã chọn về Server -->
                    <form action="bookTicket" method="POST">
                        <input type="hidden" name="roomId" value="${roomId}">
                        <!-- Chuỗi chứa ID các ghế được chọn, ngăn cách bằng dấu phẩy -->
                        <input type="hidden" name="seatIds" id="seatIdsInput" value="">

                        <div class="text-start mb-4">
                            <p class="mb-2 text-secondary">Rạp chiếu: <strong class="text-dark float-end">Rạp ${roomId}</strong></p>
                            <p class="mb-2 text-secondary">Ghế chọn: <strong class="text-primary float-end" id="selected-seats-display">Chưa chọn</strong></p>
                            <hr>
                            <p class="mb-2 text-secondary fs-5">Tổng tiền: <strong class="text-danger float-end fs-4" id="total-price-display">0 ₫</strong></p>
                        </div>

                        <button type="submit" id="btn-pay" class="btn btn-success w-100 fw-bold py-3 mb-2 disabled">💳 THANH TOÁN</button>
                        <a href="javascript:history.back()" class="btn btn-outline-secondary w-100 fw-bold">⬅ Quay lại</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

<!-- XỬ LÝ LOGIC CHỌN GHẾ BẰNG JAVASCRIPT -->
<script>
    let selectedSeats = [];
    let totalPrice = 0;
    const PRICE_VIP = 85000;
    const PRICE_STANDARD = 65000;

    function toggleSeat(element, seatId, seatName, seatType) {
        // Kiểm tra xem ghế đã được chọn chưa
        const index = selectedSeats.findIndex(s => s.id === seatId);
        const price = seatType === 'VIP' ? PRICE_VIP : PRICE_STANDARD;

        if (index > -1) {
            // Nếu đã chọn rồi -> Bỏ chọn
            selectedSeats.splice(index, 1);
            element.classList.remove('seat-selected');
            totalPrice -= price;
        } else {
            // Nếu chưa chọn -> Thêm vào danh sách
            selectedSeats.push({id: seatId, name: seatName});
            element.classList.add('seat-selected');
            totalPrice += price;
        }
        updateBill();
    }

    function updateBill() {
        // Cập nhật text hiển thị tên ghế (VD: A1, B2)
        const seatNames = selectedSeats.map(s => s.name).join(', ');
        document.getElementById('selected-seats-display').innerText = seatNames || 'Chưa chọn';

        // Cập nhật text hiển thị tiền (có định dạng VNĐ)
        document.getElementById('total-price-display').innerText = totalPrice.toLocaleString('vi-VN') + ' ₫';

        // Cập nhật giá trị vào thẻ input hidden để gửi về server
        document.getElementById('seatIdsInput').value = selectedSeats.map(s => s.id).join(',');

        // Bật/tắt nút Thanh toán
        const btnPay = document.getElementById('btn-pay');
        if (selectedSeats.length > 0) {
            btnPay.classList.remove('disabled');
        } else {
            btnPay.classList.add('disabled');
        }
    }
</script>
</body>
</html>