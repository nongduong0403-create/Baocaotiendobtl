<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập - Hệ thống Rạp Phim</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            color: #212529;
        }
        .auth-card {
            max-width: 420px;
            margin: 40px auto;
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.08);
            border: 1px solid #e9ecef;
        }
    </style>
</head>
<body class="d-flex flex-column" style="min-height: 100vh;">

<!-- NHÚNG HEADER -->
<jsp:include page="header.jsp" />

<!-- Căn giữa form đăng nhập -->
<div class="container flex-grow-1 d-flex align-items-center justify-content-center my-5">
    <div class="card auth-card p-4 w-100">
        <div class="text-center mb-4">
            <h3 class="fw-bold text-primary">🎬 CINEMA EAUT</h3>
            <p class="text-muted small">Vui lòng đăng nhập để tiếp tục</p>
        </div>

        <p class="text-danger text-center fw-bold small">${requestScope.error}</p>

        <form action="login" method="post">
            <div class="mb-3">
                <label class="form-label fw-medium small text-secondary">Tài khoản</label>
                <input type="text" class="form-control" name="txtUser" required placeholder="Nhập tên đăng nhập...">
            </div>
            <div class="mb-4">
                <label class="form-label fw-medium small text-secondary">Mật khẩu</label>
                <input type="password" class="form-control" name="txtPass" required placeholder="Nhập mật khẩu...">
            </div>
            <button type="submit" class="btn btn-warning w-100 fw-bold py-2 text-dark mb-3">ĐĂNG NHẬP</button>
        </form>

        <div class="text-center small">
            <span class="text-muted">Chưa có tài khoản?</span>
            <a href="register" class="text-decoration-none fw-bold text-primary">Đăng ký ngay</a>
        </div>
    </div>
</div>

<!-- NHÚNG FOOTER -->
<jsp:include page="footer.jsp" />

</body>
</html>