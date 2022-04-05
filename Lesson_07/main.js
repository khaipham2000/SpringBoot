let colors = ["#3498db", "#9b59b6", "#e74c3c", "#2c3e50", "#d35400"];
const totalBoxes = document.getElementById("score");
const moreBoxes = document.querySelector("button");
const wrap = document.querySelector(".wrap");
const box = document.getElementsByClassName("box");

function createBoxes() {
  for (let i = 0; i < colors.length; i++) {
    let fiveBoxes = document.createElement("div");
    fiveBoxes.className = "box";
    fiveBoxes.style.backgroundColor = colors[i];
    wrap.appendChild(fiveBoxes);
    totalBoxes.innerText = `Total box: ${box.length}`;
  }
  removeBox();
}
createBoxes();

function removeBox() {
  for (let i = 0; i < box.length; i++) {
    box[i].addEventListener("click", function () {
      this.remove();
      totalBoxes.innerText = `Total box: ${box.length}`;
    });
  }
}

moreBoxes.addEventListener("click", function () {
  createBoxes();
  totalBoxes.innerText = `Total box: ${box.length}`;
});
