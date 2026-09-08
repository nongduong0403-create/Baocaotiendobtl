<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đặt vé thành công!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .ticket-wrap { background: #fff; border-radius: 15px; box-shadow: 0 15px 30px rgba(0,0,0,0.1); max-width: 450px; margin: 50px auto; position: relative; border-top: 8px solid #28a745; }
        .ticket-wrap::before, .ticket-wrap::after { content: ''; position: absolute; top: 65%; width: 30px; height: 30px; background: #f8f9fa; border-radius: 50%; }
        .ticket-wrap::before { left: -15px; box-shadow: inset -5px 0 10px rgba(0,0,0,0.05); }
        .ticket-wrap::after { right: -15px; box-shadow: inset 5px 0 10px rgba(0,0,0,0.05); }
        .ticket-divider { border-top: 2px dashed #dee2e6; margin: 30px 0; }
        .barcode { font-family: 'Libre Barcode 39', cursive; font-size: 50px; letter-spacing: 2px; }
    </style>
</head>
<body class="d-flex flex-column" style="min-height: 100vh;">

<jsp:include page="header.jsp" />

<div class="container flex-grow-1">
    <div class="ticket-wrap p-5 text-center">
        <div class="mb-4">
            <h1 class="text-success mb-2">✅</h1>
            <h4 class="fw-bold text-success">THANH TOÁN THÀNH CÔNG</h4>
            <p class="text-muted">Cảm ơn bạn đã sử dụng dịch vụ của CINEMA EAUT</p>
        </div>

        <div class="ticket-divider"></div>

        <div class="text-start">
            <div class="row mb-3">
                <div class="col-6">
                    <p class="text-secondary mb-1">Mã giao dịch</p>
                    <h6 class="fw-bold">TXN-8899</h6>
                </div>
                <div class="col-6 text-end">
                    <p class="text-secondary mb-1">Rạp chiếu</p>
                    <h6 class="fw-bold">RẠP SỐ ${roomId}</h6>
                </div>
            </div>

            <p class="text-secondary mb-1">Ghế của bạn</p>
            <div class="d-flex flex-wrap gap-2 mb-4">
                <c:forEach items="${bookedSeats}" var="s">
                        <span class="badge ${s.type == 'VIP' ? 'bg-danger' : 'bg-success'} fs-6 p-2">
                                ${s.rowName}${s.seatNumber}
                        </span>
                </c:forEach>
            </div>

            <hr>
            <div class="d-flex justify-content-between align-items-center">
                <span class="text-secondary fs-5">Tổng thanh toán:</span>
                <strong class="text-danger fs-3">
                    <fmt:formatNumber value="${totalAmount}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                </strong>
            </div>
        </div>

        <div class="ticket-divider"></div>

        <div class="mb-4 text-center">
            <div class="barcode text-muted">||| ||||| |||| || ||||| ||</div>
            <small class="text-muted">Đưa mã này cho nhân viên để nhận vé cứng</small>
        </div>

        <a href="home" class="btn btn-outline-primary w-100 fw-bold py-2">Về Trang Chủ</a>
    </div>
</div>

</body>
</html>