const btnRandomName = document.getElementById("btn-random-name");
const btnRandomHex = document.getElementById("btn-random-hex");
const btnRandomRGB = document.getElementById("btn-random-rgb");

//Cách 1:
// btnRandomName.addEventListener("click" ,async function(){
//     try {
//         //Gọi api
//         let res = await axios.get("http://localhost:8080/random-color?type=1");
//         console.log(res);

//         //Thay đổi màu body
//         document.body.style.background = res.data;
//     } catch (error) {
//         alert(error.response.data.message);
//     }
// })

// btnRandomHex.addEventListener("click" ,async function(){
//     try {
//         //Gọi api
//         let res = await axios.get("http://localhost:8080/random-color?type=2");
//         console.log(res);

//         //Thay đổi màu body
//         document.body.style.background = res.data;
//     } catch (error) {
//         alert(error.response.data.message);
//     }
// })

// btnRandomRGB.addEventListener("click" ,async function(){
//     try {
//         //Gọi api
//         let res = await axios.get("http://localhost:8080/random-color?type=3");
//         console.log(res);

//         //Thay đổi màu body
//         document.body.style.background = res.data;
//     } catch (error) {
//         alert(error.response.data.message);
//     }
// })

//Cách 2:
const colorNameEl = document.getElementById("color-name");
const btns = document.querySelectorAll("button");
// for(let i = 0 ; i < btns.length ; i++){
//     btns[i].addEventListener("click" , async function(){
//         try {
//             let res = await axios.get(`http://localhost:8080/random-color?type=${i+1}`);
//             document.body.style.background = res.data;
//             colorNameEl.innerText = res.data;
//         } catch (error) {
//             alert(error.response.data.message);
//         }
//     })
// }

//Cách 3:
// btns.forEach((btn, index) => {
//   btn.addEventListener("click", async function () {
//     try {
//       let res = await axios.get(
//         `http://localhost:8080/random-color?type=${index + 1}`
//       );
//       document.body.style.background = res.data;
//       colorNameEl.innerText = res.data;
//     } catch (error) {
//       alert(error.response.data.message);
//     }
//   });
// });

//Cách 4:
btns.forEach((btn) => {
  btn.addEventListener("click", async function () {
    try {
      //Đọc type phần tử
      let type = btn.dataset.type;
      //Gọi api
      let res = await axios.get(
        `http://localhost:8080/random-color?type=${type}`
      );
      document.body.style.background = res.data;
      colorNameEl.innerText = res.data;
    } catch (error) {
      alert(error.response.data.message);
    }
  });
});
