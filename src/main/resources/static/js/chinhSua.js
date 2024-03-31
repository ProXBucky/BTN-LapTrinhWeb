//function themCauHoi() {
//    var danhSachCauHoi = document.getElementById('danhSachCauHoi');
//
//    var div = document.createElement('div');
//    div.innerHTML = `
//        <label for="cauHoi">Câu hỏi:</label><br>
//        <input type="text" name="cauHoi" required><br><br>
//        <label for="dapAn">Đáp án:</label><br>
//        <input type="text" name="dapAn" required><br><br>
//    `;
//
//    danhSachCauHoi.appendChild(div);
//}

function themCauHoi() {
    var danhSachCauHoi = document.getElementById('danhSachCauHoi');
	var index = 0 ;
    var div = document.createElement('div');
    div.classList.add('cau-hoi'); // Thêm một lớp CSS để dễ dàng xác định và tùy chỉnh
    div.innerHTML = `
         <div class="question">
                <label for="questionContent">Nội Dung Câu Hỏi:</label>
                <input type="text" class="questionContent" name="questions[${index}].questionContent"><br><br>
                <label>Câu Trả Lời 1: </label><input type="radio" class="correctAnswer" name="questions[${index}].correctAnswer" value="0"><br>
                <input type="text" class="answer" name="questions[${index}].answers[0].content">
            
                <label>Câu Trả Lời 2: </label><input type="radio" class="correctAnswer" name="questions[${index}].correctAnswer" value="1"><br>
                <input type="text" class="answer" name="questions[${index}].answers[1].content">
                
                <label>Câu Trả Lời 3: </label><input type="radio" class="correctAnswer" name="questions[${index}].correctAnswer" value="2"><br>
                <input type="text" class="answer" name="questions[${index}].answers[2].content">
                
                <label>Câu Trả Lời 4: </label><input type="radio" class="correctAnswer" name="questions[${index}].correctAnswer" value="3"><br><br>
                <input type="text" class="answer" name="questions[${index}].answers[3].content">
                
                <button type="button" onclick="xoaCauHoi(this)">Xóa</button><br><br>
         </div>
            
    `;

    danhSachCauHoi.appendChild(div);
    index++;
}

function xoaCauHoi(button) {
    var cauHoi = button.parentNode;
    cauHoi.parentNode.removeChild(cauHoi);
}



