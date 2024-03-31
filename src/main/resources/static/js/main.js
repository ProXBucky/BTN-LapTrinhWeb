//
//
//
//const data = [
//    {
//        tenMon: "Nhập môn công nghệ phần mềm",
//        kiThi: "Luyện tập",
//        ngayThi: "Tự do"
//    },
//    {
//        tenMon: "Phân tích thiết kế hệ thống thông tin",
//        kiThi: "Giữa kỳ",
//        ngayThi: "12h00 20/3/2024"
//    },
//    {
//        tenMon: "Lập trình website",
//        kiThi: "Luyện tập",
//        ngayThi: "Tự do"
//    },
//    {
//        tenMon: "Lịch sử Đảng",
//        kiThi: "Cuối kỳ",
//        ngayThi: "8h00 15/5/2024"
//    },
//    {
//        tenMon: "Lập trình hướng đối tượng",
//        kiThi: "Luyện tập",
//        ngayThi: "Tự do"
//    },
//    {
//        tenMon: "An toàn hệ thống thông tin",
//        kiThi: "Giữa kỳ",
//        ngayThi: "12h00 20/3/2024"
//    },
//    {
//        tenMon: "Lập trình C++",
//        kiThi: "Luyện tập",
//        ngayThi: "Tự do"
//    },
//    {
//        tenMon: "Triết học Mác Lênin",
//        kiThi: "Cuối kỳ",
//        ngayThi: "8h00 15/5/2024"
//    },
//
//];
//
//const searchInput = document.querySelector("#myInput");
//const searchBtn = document.querySelector(".action-button");
//const examList = document.querySelector(".custom-table");
//
//function renderExam(data) {
//    examList.innerHTML += `
//      <tr>
//        <td>${data.tenMon}</td>
//        <td>${data.kiThi}</td>
//        <td>${data.ngayThi}</td>
//        <td><button class="action-button" onclick="renderNewHTML('/exam')">Làm bài</button></td>
//      </tr>
//    `;
//}
//
//function handleSearchBtn() {
//    searchBtn.onclick = () => {
//        examList.innerHTML = `
//        <tr>
//          <th>Tên môn</th>
//          <th>Kì thi</th>
//          <th>Ngày thi</th>
//          <th>Hành động</th>
//        </tr> 
//      `;
//
//        const input = searchInput.value;
//        if (input.length !== 0) {
//            data.forEach((data) => {
//                if (data.ngayThi.includes(input) || data.tenMon.includes(input)) {
//                    renderExam(data);
//                }
//            });
//        } else {
//            data.forEach(renderExam);
//        }
//    };
//}
//
//function start() {
//    examList.innerHTML = `
//      <tr>
//      <th>Tên môn</th>
//      <th>Kì thi</th>
//      <th>Ngày thi</th>
//      <th>Hành động</th>
//      </tr> 
//    `;
//    data.forEach(renderExam);
//
//    handleSearchBtn();
//}
//
//start();