let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 30000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

// 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product

function addNewProduct(arr,name,price,brand,count){
    let newProduct = {
        name : name,
        price : price,
        brand : brand,
        count : count,
    }
    arr.push(newProduct);
    return arr;
}
console.log(addNewProduct(products,"Redmi Note 10",5000000,"Xiaomi",4));

// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng

function deleteBrand(arr, productBrand) {
    return arr.filter(product => product.brand.toLowerCase() !=  productBrand.toLowerCase())
}
console.log(deleteBrand(products, "Samsung"));

// 8. Sắp xếp giỏ hàng theo price tăng dần

function sortByPrice(arr){
    return arr.sort(function (a, b) {
        return a.price - b.price;
    })
}
console.log(sortByPrice(products));

// 9. Sắp xếp giỏ hàng theo count giảm dần

function sortByCount(arr) {
    return arr.sort(function (a, b) {
        return b.count - a.count;
    })
}
console.log(sortByCount(products));

// 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng
function randomProducts(arr) {
    let newArr = []
    while (newArr.length < 2){
        let randomNum = Math.floor(Math.random() * arr.length);
        if(newArr.includes(arr[randomNum]) == false) {
            newArr.push(arr[randomNum]);
        }
    }
   
    return newArr;
}
console.log(randomProducts(products));
