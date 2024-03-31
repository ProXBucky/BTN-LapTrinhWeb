

var thongKeData = [
    { tenKyThi: "Nhập môn công nghệ phần mềm", soLuongNguoiDung: 10, tyLeHoanThanh: 70, diemTrungBinh: 8.5 },
    { tenKyThi: "Lập trình website", soLuongNguoiDung: 15, tyLeHoanThanh: 60, diemTrungBinh: 7.8 },
    { tenKyThi: "Lập trình hướng đối tượng", soLuongNguoiDung: 10, tyLeHoanThanh: 70, diemTrungBinh: 8.4 },
];


function hienThiThongKe() {
    var thongKeDiv = document.getElementById("thongKe");
    thongKeDiv.innerHTML = "<h4>Thống kê</h4>";

    thongKeData.forEach(function (thongKe) {
        thongKeDiv.innerHTML += "<h5>Kỳ thi: " + thongKe.tenKyThi + "</h5>";
        thongKeDiv.innerHTML += "<p>Số lượng người dùng: " + thongKe.soLuongNguoiDung + "</p>";
        thongKeDiv.innerHTML += "<p>Tỷ lệ hoàn thành: " + thongKe.tyLeHoanThanh + "%</p>";
        thongKeDiv.innerHTML += "<p>Điểm trung bình: " + thongKe.diemTrungBinh + "</p>";
    });
}

//hienThiThongKe();


