<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang Chủ - Đặt vé xem phim</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        body { background-color: #f8f9fa; font-family: sans-serif; }
        .summary-card { position: sticky; top: 20px; }
        .step-nav { font-size: 0.9rem; font-weight: 500; color: #6c757d; }
        .step-nav .active { color: #0d6efd; font-weight: bold; }

        /* Cấu hình Nhãn dán và Thẻ phim y hệt mẫu */
        .movie-card-wrapper { position: relative; overflow: hidden; border-radius: 8px; transition: transform 0.3s; }
        .movie-card-wrapper:hover { transform: scale(1.03); box-shadow: 0 10px 20px rgba(0,0,0,0.2); }
        .movie-poster { height: 340px; object-fit: cover; border-radius: 8px; width: 100%; }

        .badge-age { position: absolute; top: 10px; left: 10px; background-color: #0d6efd; color: white; padding: 2px 8px; border-radius: 4px; font-weight: bold; font-size: 0.8rem; z-index: 10; border: 1px solid white;}

        /* Nhãn HOT nằm chéo góc (Ribbon) */
        .badge-hot { position: absolute; top: 18px; right: -30px; background-color: #ff4757; color: white; padding: 4px 35px; font-weight: bold; font-size: 0.8rem; transform: rotate(45deg); z-index: 10; text-align: center; box-shadow: 0 2px 4px rgba(0,0,0,0.2);}

        /* Chỉnh style Tab y hệt Beta Cinemas */
        .nav-tabs .nav-link { color: #6c757d; font-weight: bold; font-size: 1.1rem; border: none; padding: 10px 20px; }
        .nav-tabs .nav-link.active { color: #0d6efd; border-bottom: 3px solid #0d6efd !important; background: transparent; }
    </style>
</head>
<body class="d-flex flex-column" style="min-height: 100vh;">
<!-- HEADER -->
<jsp:include page="header.jsp" />

<!-- THANH TIẾN TRÌNH -->
<div class="bg-white border-bottom py-3 mb-3 shadow-sm">
    <div class="container text-center step-nav">
        <span class="active">Chọn phim / Rạp / Suất</span> &nbsp; > &nbsp;
        <span>Chọn ghế</span> &nbsp; > &nbsp;
        <span>Thanh toán</span>
    </div>
</div>

<div class="container flex-grow-1 mb-5">
    <div class="row">

        <!-- CỘT TRÁI: BANNER VÀ DANH SÁCH PHIM -->
        <div class="col-md-8">

            <!-- 1. BANNER TRƯỢT CÓ INDICATORS (Dấu chấm) -->
            <div id="promoBanner" class="carousel slide mb-4 shadow-sm rounded-3 overflow-hidden" data-bs-ride="carousel" data-bs-interval="2000">
                <!-- Thanh dấu chấm (Đã thêm thành 3 nút) -->
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#promoBanner" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#promoBanner" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#promoBanner" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>

                <div class="carousel-inner">
                    <!-- Slide 1 -->
                    <div class="carousel-item active">
                        <img src="images/banner1-4k.jpg" class="d-block w-100" style="height: 500px; object-fit: cover; object-position: top;" alt="Banner Khuyến Mãi 1">
                    </div>

                    <!-- Slide 2 -->
                    <div class="carousel-item">
                        <!-- Nhớ kiểm tra lại đuôi file ở đây xem là .jpg hay .png nhé -->
                        <img src="images/banner21-4k.jpg" class="d-block w-100" style="height: 500px; object-fit: cover; object-position: top;" alt="Banner Khuyến Mãi 2">
                    </div>

                    <!-- Slide 3 (Mới thêm) -->
                    <div class="carousel-item">
                        <img src="images/banner31-4k.jpg" class="d-block w-100" style="height: 500px; object-fit: cover; object-position: top;" alt="Banner Khuyến Mãi 3">
                    </div>
                </div>

                <button class="carousel-control-prev" type="button" data-bs-target="#promoBanner" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#promoBanner" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                </button>
            </div>

            <!-- 2. HỆ THỐNG 3 TABS -->
            <ul class="nav nav-tabs justify-content-center mb-4 border-bottom" id="movieTabs" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="coming-soon-tab" data-bs-toggle="tab" data-bs-target="#comingSoon" type="button" role="tab">SẮP CHIẾU</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="now-showing-tab" data-bs-toggle="tab" data-bs-target="#nowShowing" type="button" role="tab">ĐANG CHIẾU</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="early-screening-tab" data-bs-toggle="tab" data-bs-target="#earlyScreening" type="button" role="tab">SUẤT CHIẾU SỚM</button>
                </li>
            </ul>

            <!-- 3. NỘI DUNG TABS PHIM -->
            <div class="tab-content">

                <!-- TAB: ĐANG CHIẾU (status == 1) -->
                <div class="tab-pane fade show active" id="nowShowing" role="tabpanel">
                    <div class="row g-4">
                        <c:forEach items="${movieList}" var="m">
                            <c:if test="${m.status == 1}">
                                <div class="col-md-4 col-6">
                                    <a href="showtimes?movieId=${m.id}" class="text-decoration-none text-dark">
                                        <div class="movie-card-wrapper mb-2 shadow-sm">
                                            <span class="badge-age">${m.ageRating}</span>
                                            <c:if test="${m.hot}"><span class="badge-hot">HOT</span></c:if>
                                            <img src="${m.posterUrl}" class="img-fluid movie-poster" alt="${m.title}">
                                        </div>
                                        <div class="text-center">
                                            <h6 class="fw-bold fs-6 mb-1 text-truncate">${m.title}</h6>
                                            <small class="text-muted">${m.duration} phút</small>
                                        </div>
                                    </a>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <!-- TAB: SẮP CHIẾU (status == 2) -->
                <div class="tab-pane fade" id="comingSoon" role="tabpanel">
                    <div class="row g-4">
                        <c:forEach items="${movieList}" var="m">
                            <c:if test="${m.status == 2}">
                                <div class="col-md-4 col-6 opacity-75">
                                    <a href="#" class="text-decoration-none text-dark" onclick="alert('Phim sắp ra mắt!'); return false;">
                                        <div class="movie-card-wrapper mb-2 shadow-sm">
                                            <span class="badge-age">${m.ageRating}</span>
                                            <c:if test="${m.hot}"><span class="badge-hot bg-warning text-dark">SẮP HOT</span></c:if>
                                            <img src="${m.posterUrl}" class="img-fluid movie-poster" alt="${m.title}">
                                        </div>
                                        <div class="text-center">
                                            <h6 class="fw-bold fs-6 mb-1 text-truncate">${m.title}</h6>
                                            <small class="text-primary fw-bold">Coming Soon</small>
                                        </div>
                                    </a>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <!-- TAB: SUẤT CHIẾU SỚM (status == 3) -->
                <div class="tab-pane fade" id="earlyScreening" role="tabpanel">
                    <div class="row g-4">
                        <c:forEach items="${movieList}" var="m">
                            <c:if test="${m.status == 3}">
                                <div class="col-md-4 col-6">
                                    <a href="showtimes?movieId=${m.id}" class="text-decoration-none text-dark">
                                        <div class="movie-card-wrapper mb-2 shadow border border-warning">
                                            <span class="badge-age">${m.ageRating}</span>
                                            <span class="badge-hot bg-success">SPECIAL</span>
                                            <img src="${m.posterUrl}" class="img-fluid movie-poster" alt="${m.title}">
                                        </div>
                                        <div class="text-center">
                                            <h6 class="fw-bold fs-6 mb-1 text-truncate">${m.title}</h6>
                                            <small class="text-success fw-bold">Suất chiếu đặc biệt</small>
                                        </div>
                                    </a>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>

        <!-- CỘT PHẢI: HÓA ĐƠN & TÍCH HỢP VOUCHER -->
        <div class="col-md-4">
            <div class="card summary-card shadow-sm border-0">
                <div class="card-body p-4">
                    <div class="text-center mb-3">
                        <i class="bi bi-ticket-perforated fs-1 text-danger"></i>
                        <h5 class="fw-bold mt-2">Thông tin đặt vé</h5>
                    </div>
                    <hr>

                    <!-- Ô NHẬP MÃ VOUCHER -->
                    <div class="input-group mb-2">
                        <input type="text" id="voucherCode" class="form-control" placeholder="Nhập mã khuyến mãi" style="text-transform: uppercase;">
                        <button class="btn btn-outline-primary fw-bold" type="button" id="btnApplyVoucher">Áp dụng</button>
                    </div>
                    <small id="voucherMessage" class="d-block mb-3 fw-bold text-success" style="font-size: 0.8rem;"></small>

                    <!-- KHU VỰC HIỂN THỊ TIỀN -->
                    <div class="d-flex justify-content-between mb-2">
                        <span class="text-muted">Tạm tính</span>
                        <span id="subTotal" class="fw-bold">0 ₫</span>
                    </div>
                    <div class="d-flex justify-content-between mb-2 text-danger">
                        <span>Giảm giá Voucher</span>
                        <span id="discountAmount" class="fw-bold">- 0 ₫</span>
                    </div>

                    <hr>
                    <div class="d-flex justify-content-between mb-4">
                        <span class="fw-bold fs-5">Tổng cộng</span>
                        <span id="finalTotal" class="fw-bold fs-5 text-danger">0 ₫</span>
                    </div>

                    <div class="d-flex gap-2">
                        <a href="#" class="btn btn-outline-secondary w-50 fw-bold">Quay lại</a>
                        <button class="btn btn-warning text-dark w-50 fw-bold">Tiếp tục</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.getElementById('btnApplyVoucher').addEventListener('click', function () {
        let code = document.getElementById('voucherCode').value;
        let subTotal = 120000; // Tạm thời để cứng số tiền mẫu, sau này cậu truyền biến động từ ghế ngồi vào đây
        let msgElem = document.getElementById('voucherMessage');

        let formData = new URLSearchParams();
        formData.append('code', code);
        formData.append('subTotal', subTotal);

        fetch('apply-voucher', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                if (data.status === 1) {
                    msgElem.className = "d-block mb-3 fw-bold text-success";
                    msgElem.innerText = data.message;
                    document.getElementById('discountAmount').innerText = "- " + data.discountAmount.toLocaleString() + " ₫";
                    document.getElementById('finalTotal').innerText = data.finalTotal.toLocaleString() + " ₫";
                } else {
                    msgElem.className = "d-block mb-3 fw-bold text-danger";
                    msgElem.innerText = data.message;
                }
            })
            .catch(error => {
                console.error('Lỗi:', error);
                msgElem.className = "d-block mb-3 fw-bold text-danger";
                msgElem.innerText = "Có lỗi xảy ra, vui lòng thử lại!";
            });
    });
</script>
</body>
</html>