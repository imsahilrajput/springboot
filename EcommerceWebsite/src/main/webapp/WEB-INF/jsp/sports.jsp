<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Banking Application</title>
    <link rel="stylesheet" href="resources/styles/bootstrap.min.css" />

    <link rel="stylesheet" href="resources/styles/styles.css" />
    <style>
      table {
        border: none;
        border-collapse: collapse;
      }

      .dropdown-menu {
        width: 87%;
      }

      .dropdown-toggle {
        cursor: pointer;
      }

     
      /* .form-select{
        width: 100%;
        height: 72%;
      } */
      td,
      th {
        height: 60px;
      }
      .rule-set-name {
        text-align: left;
      }
      .lastUpdated{
        text-align: center;
      }
      .balance{
        margin-right: 0px;
      }
      .btn{
        box-shadow: none;
        height: 50px;
      }
      /* img{
        height: 40px;
      } */
      .images{
        height :100px;
      }
      .me-2 {
    height: 41px;
}
     
    </style>
    <script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
  </head>

  <body onload="myFunction1();">
    <div id="myNav"></div>
    <div class="container">

      <div class="row">
        
        <br>
        <br>
        <br>
        
      <h2 class="mb-5" style="text-align: center">Sports</h2>

     

      <div class="row mt-5" >
        <div class="col-md-12" id="table">
          <hr>
          <table class="table border-dark text-center" >
            <thead>
             
            </thead>
            <tbody id="inject"></tbody>
          </table>
        </div>
      </div>

    </div>

    <script src="resources/js/bootstrap/bootstrap.bundle.min.js"></script>

    <script>
     
      var userId;
      var iter=0;
      var branch={};
      var branches =[];
      async function getAllProductsApiCall() {
        let response = await fetch(
          "http://localhost:5550/ecommerce/products" ,
          {
            method: "GET",
          }
        );
        let data = await response.json();
        console.log(data);
                  data.forEach(createTable);

        // if(data.length>0){
        //   document.getElementById("table").style.display="block";
        //   document.getElementById("notification").style.display="none";

        //   data.forEach(createTable);
        // }else{
        //   document.getElementById("table").style.display="none";
        // }
        
      }


      function myFunction1() {
        $("#myNav").load("resources/nav.html");
        // userId = sessionStorage.getItem("userId");
        // if(userId == null){
        //   window.open("404.html","_self");
        // }
        // console.log(userId);
        getAllProductsApiCall();
        
        
      }

    


      function createTable(item) {
        var productId = item.productId;
        var productName=item.productName;
        let categoryName= item.categoryName;
        var price = item.price;
        var image= "resources/"+item.image;
        if(categoryName==="Sports"){
            addMore(productId, productName, price,image);

        }
      }
      function addMore(productId, productName, price ,image) {
        console.log(price)
        var table = document.getElementById("inject");
        var tableRow = document.createElement("tr");
        var td0 = document.createElement("th");
        td0.textContent = iter + 1;

        var td1 = document.createElement("td");
        td1.setAttribute("hidden", "");
        td1.setAttribute("id", "productId[" + productId + "]");
        // var td2 = document.createElement("td");
        // td2.setAttribute("id", "productName["+iter+"]");
        // td2.classList.add("rule-set-name");
        var td3 = document.createElement("td");
        td3.setAttribute("id", "productPrice["+iter+"]");
        td3.classList.add("rule-set-name");
        var viewButton=document.createElement("button");
        

        


        
        
        td3.classList.add("balance");
        
        td1.innerHTML = productId;
        // td2.innerHTML = productName;
        td3.innerHTML="Rs."+ price;
        // td3.innerHTML=balance
       
        var myImage = document.createElement("img");
        myImage.src = image;
        myImage.classList.add("images");

        var figCaption = document.createElement("figcaption");
        figCaption.innerText=productName;
        console.log(myImage)
        
        var td2 = document.createElement("td");
        td2.classList.add("rule-set-name");
        td2.setAttribute("id", "productName["+iter+"]");
        td2.setAttribute("id", "productImage["+iter+"]");
        td2.append(myImage);
        td2.append(figCaption);



        addToCartBtn=document.createElement("button");
        addToCartBtn.classList.add("btn","btn-success");
        addToCartBtn.setAttribute("id","id["+productId+"]");
        addToCartBtn.innerHTML = "Add to Cart";
        addToCartBtn.setAttribute("onclick", "view("+productId+")");
        var td4 = document.createElement("td");
        td4.setAttribute("id", "productid["+iter+"]");
        td4.classList.add("rule-set-name");
        td4.append(addToCartBtn);


        td5 = document.createElement("td");
        let input = document.createElement("input");
        input.classList.add("form-control");
        input.setAttribute("id","quantity["+productId+"]");
        input.setAttribute("type", "number");
        input.style.width="73px";
        input.placeholder="0";
        td5.append(input);
        
        
       
        tableRow.style.height="120px";
        tableRow.append(td0, td1, td2, td3,td5,td4);
        table.appendChild(tableRow);
        iter++;
      }

      function view(item){
        var  productId=item;
        var quantity = document.getElementById("quantity["+item+"]").value;
        var data = {
          "productId":productId,
          "quantity":quantity,
          "customerId":1
        }
        if(quantity>0){
          addToCartApiCall(data);

        }else{
          window.alert("Please Select Quantity");
        }
        window.open("sports","_self");
      
      }


      async function addToCartApiCall(data) {
        console.log(data);
        let response = await fetch('http://localhost:5550/ecommerce/add/cart', {
          headers: { 'Content-type': 'application/json' },
          method: 'POST',
          body: JSON.stringify(data),
        });

        let datas = await response.json();
        console.log(datas);

        
      }
      
     


    </script>
  </body>
</html>
