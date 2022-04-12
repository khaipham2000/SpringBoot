const btnGet = document.getElementById("btn-get");
const btnPost = document.getElementById("btn-post");
const btnReset = document.getElementById("btn-reset");
const height = document.getElementById("height");
const weight = document.getElementById("weight");
const showResust = document.getElementById("result");
const showStatus = document.getElementById("status");

btnGet.addEventListener("click", async function () {
  try {
    //gọi API
    let h = (Number)(height.value)
    let w = (Number)(weight.value)

    let res = await axios.get(`http://localhost:8080/bmi?height=${h}&weight=${w}`)
    console.log(res)

    showResust.value = res.data;
    console.log(res.data);
    bmiStatus(Number(res.data));

  } catch (error) {
    alert(error.response.data.message);
  }
})

btnPost.addEventListener("click", async function () {
    try {
      //gọi API
      let h = Number(height.value);
      let w = Number(weight.value);
      let res1 = await axios({
        method: 'post',
        url: 'http://localhost:8080/bmi',
        data: { height: h,
          weight: w, }
      });
      console.log(res1.data);

      showResust.value = Number(res1.data);
      bmiStatus(Number(res1.data));

    } catch (error) {
      alert(error.response.data.message);
    }
  });

btnReset.addEventListener("click",function(){
    height.value = "";
    weight.value = "";
    showResust.value ="";
    showStatus.value ="";
  })

function bmiStatus(data){
    if (data < 18.5) {
      return showStatus.value ="Underweight";
    }
    if (data >= 18.5 && data <= 24.9) {
      return showStatus.value ="Normal";
    }
    if (data >= 25 && data <= 29.9) {
      return showStatus.value ="Overweight";
    }  
    if (data >= 30) {
      return showMes.value ="Obese";
    }
  }