// Thêm 3 thẻ <li> có nội dung Item 8, Item 9, Item 10 vào cuối danh sách
const li7 = document.querySelector("#list li:last-child");
console.log(li7);

const li8 = document.createElement("li");
const li9 = document.createElement("li");
const li10 = document.createElement("li");
li8.innerText = "Item 8";
li9.innerText = "Item 9";
li10.innerText = "Item 10";

li7.insertAdjacentElement("afterend", li8);
li8.insertAdjacentElement("afterend", li9);
li9.insertAdjacentElement("afterend", li10);

// Sửa nội dung cho thẻ <li> 1 thành màu đỏ (color)
const li1 = document.querySelector("#list li:first-child");
li1.style.color = "red"
console.log(li1);

// Sửa background cho thẻ <li> 3 thành màu xanh (background-color)
const li3 = document.querySelector("#list li:nth-child(3)");
li3.style.backgroundColor = "blue"
console.log(li3);

// Remove thẻ <li> 4
const li4 = document.querySelector("#list li:nth-child(4)");
li4.parentElement.removeChild(li4)

// Thêm thẻ <li> mới thay thế cho thẻ <li> 4 bị xóa ở bước trước, thẻ <li> mới có nội dung bất kỳ
const liNew = document.createElement("li");
liNew.innerText = "Đây từng là thẻ li4";
li3.insertAdjacentElement("afterend", liNew);