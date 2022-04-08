const btn = document.getElementById("btn");
const image = document.getElementById("image");
const select = document.getElementById("breed-list");
const sub_list = document.querySelector(".sub_list");

async function getBreedList() {
  try {
    // Gọi API để lấy danh sách giống loài
    let res = await axios.get("https://dog.ceo/api/breeds/list/all");

    // Sau khi có data thì hiển thị kết quả trên giao diện
    console.log(res);
    renderBreed(res.data.message);
  } catch (error) {
    console.log(error.response.data.message);
  }
}

function renderBreed(breeds) {
  for (const keys in breeds) {
    let option = document.createElement("option");
    select.appendChild(option);
    option.innerText = keys;
  }
}

btn.addEventListener("click", async function () {
  try {
    // Gọi API để lấy danh sách giống loài
    let res1 = await axios.get(
      `https://dog.ceo/api/breed/${select.value}/list`
    );
    // Sau khi có data thì hiển thị kết quả trên giao diện
    console.log(res1);
    renderSubBreed(res1.data.message);
  } catch (error) {
    console.log(error.response.data.message);
  }
});

function renderSubBreed(subBreeds) {
let subkeys = [];
subkeys = subBreeds;
  const li = document.querySelectorAll(".li");
  if (li) {
    sub_list.innerHTML = "";
    image.src = "";
  }
  if (subBreeds.length == 0) {
    sub_list.innerHTML = "<li>Không có sub breed</li>";
    image.src = "";
  }

  for (let i = 0; i < subkeys.length; i++) {
    
    const li1 = document.createElement("li");
    sub_list.appendChild(li1);
    li1.className = "li";
    li1.innerHTML = `<a href='#'>${subkeys[i]}</a>`;
    li1.addEventListener("click", async function () {
      try {
        let res2 = await axios.get(`https://dog.ceo/api/breed/${select.value}/${subBreeds[i]}/images/random`)
        console.log(res2);
        image.src = res2.data.message;
      } catch (error) {
        console.log(error.response.data.message);
      }
    });
  }
  return subkeys;
}

getBreedList();
