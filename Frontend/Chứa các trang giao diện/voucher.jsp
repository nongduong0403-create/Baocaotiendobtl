<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Voucher Của Tôi - Cinema EAUT</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        body { background-color: #f8f9fa; }
        .voucher-ticket {
            border-left: 5px dashed #0d6efd;
            background: #fff;
            transition: transform 0.2s;
        }
        .voucher-ticket:hover {
            transform: scale(1.02);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body class="d-flex flex-column" style="min-height: 100vh;">

<!-- HEADER -->
<jsp:include page="header.jsp" />

<div class="container flex-grow-1 my-5">
    <div class="d-flex align-items-center mb-4">
        <i class="bi bi-ticket-detailed fs-1 text-primary me-3"></i>
        <h2 class="fw-bold text-primary mb-0">Kho Voucher Của Tôi</h2>
    </div>

    <ul class="nav nav-pills mb-4" id="pills-tab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active rounded-pill px-4" data-bs-toggle="pill" data-bs-target="#valid">Còn hiệu lực</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link rounded-pill px-4" data-bs-toggle="pill" data-bs-target="#expired">Đã sử dụng / Hết hạn</button>
        </li>
    </ul>

    <div class="tab-content">
        <!-- Tab: Còn hiệu lực -->
        <div class="tab-pane fade show active" id="valid">
            <div class="row g-4">
                <!-- Voucher 1 -->
                <div class="col-md-6">
                    <div class="card voucher-ticket border-0 shadow-sm p-3">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h5 class="fw-bold text-danger mb-1">Giảm 50.000đ</h5>
                                <p class="text-muted small mb-2">Áp dụng cho đơn tối thiểu 120k</p>
                                <span class="badge bg-light text-dark border">Mã: <strong class="text-primary fs-6">EAUT50K</strong></span>
                            </div>
                            <div class="text-end">
                                <small class="text-muted d-block mb-2">HSD: 30/12/2026</small>
                                <a href="home" class="btn btn-outline-primary btn-sm rounded-pill px-3">Dùng ngay</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Tab: Đã hết hạn -->
        <div class="tab-pane fade" id="expired">
            <div class="alert alert-secondary text-center" role="alert">
                Bạn chưa có voucher nào đã sử dụng.
            </div>
        </div>
    </div>
</div>

<!-- FOOTER -->
<jsp:include page="footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>