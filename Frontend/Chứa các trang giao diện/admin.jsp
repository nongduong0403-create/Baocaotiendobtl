<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang Quản Trị - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #121212; color: #ffffff; }
        .admin-card { background-color: #1e1e1e; border: 1px solid #333; border-radius: 15px; }
        .table-dark th { color: #ffc107; border-bottom: 2px solid #ffc107; }
        .table-dark td { border-color: #333; vertical-align: middle; }
        .form-control-dark { background-color: #2a2a2a; color: white; border: 1px solid #444; }
        .form-control-dark:focus { background-color: #333; color: white; border-color: #ffc107; box-shadow: 0 0 0 0.25rem rgba(255, 193, 7, 0.25); }
    </style>
</head>
<body class="d-flex flex-column" style="min-height: 100vh;">

<!-- NHÚNG HEADER -->
<jsp:include page="header.jsp" />

<div class="container flex-grow-1 mt-5 mb-5">
    <h2 class="fw-bold mb-4 border-start border-5 border-danger ps-3">⚙️ QUẢN TRỊ RẠP CHIẾU PHIM</h2>

    <!-- Khu vực Thêm Rạp -->
    <div class="admin-card shadow p-4 mb-5">
        <h5 class="text-warning mb-3">➕ Thêm Rạp Mới</h5>
        <form action="admin" method="post" class="d-flex gap-3">
            <input type="text" class="form-control form-control-dark w-50" name="roomName" required placeholder="Nhập tên rạp mới (VD: Rạp VIP 3)...">
            <button type="submit" class="btn btn-warning fw-bold px-4">Thêm Rạp</button>
        </form>
    </div>

    <!-- Khu vực Danh sách Rạp -->
    <div class="admin-card shadow p-4">
        <h5 class="text-warning mb-3">📋 Danh Sách Các Rạp Đang Hoạt Động</h5>
        <table class="table table-dark table-hover text-center align-middle bg-transparent">
            <thead>
            <tr>
                <th>Mã Rạp</th>
                <th>Tên Rạp</th>
                <th>Thao tác Admin</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${roomList}" var="r">
                <tr>
                    <td>${r.id}</td>
                    <td class="fw-bold text-light">${r.name}</td>
                    <td>
                        <a href="deleteRoom?id=${r.id}" onclick="return confirm('CẢNH BÁO: Bạn có chắc chắn muốn xóa rạp này và toàn bộ ghế bên trong không?');" class="btn btn-outline-danger btn-sm fw-bold">
                            ❌ Xóa rạp
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- NHÚNG FOOTER -->
<jsp:include page="footer.jsp" />

</body>
</html>