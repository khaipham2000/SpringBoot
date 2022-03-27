// bài 1
function factorial(x) {
  if (x === 0) {
    return 1;
  } else {
    return x * factorial(x - 1);
  }
}
console.log(factorial(3));

// bài 2
function reverseStr(a) {
  let newStr = "";
  for (i = a.length - 1; i >= 0; i--) {
    newStr += a[i];
  }
  return newStr;
}
console.log(reverseStr("hello"));

// bài 3
function translate(a) {
  switch (a) {
    case "VN":
      console.log("Xin chào");
      break;
    case "EN":
      console.log("Hello");
      break;
    case "RU":
      console.log("Привет!");
      break;
    case "KR":
      console.log("안녕");
      break;
    case "FR":
      console.log("Bonjour");
      break;
    default:
      console.log("Not available");
      break;
  }
}
console.log(translate("RU"));

// bài 4
function mainStr(a) {
  if (a.length < 16) {
    return false;
  }else {
    let sliceStr = a.slice(0, 10)+"...";
    return sliceStr
  }
}
console.log(mainStr("xinchaocacbandenvoiTechmaster"));


