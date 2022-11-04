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
      .dropdown {
        width: 25%;
        margin-right: 10px;
      }

      .form-group {
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .header {
        font-weight: bold;
        font-size: larger;
      }
      .transactions {
        font-style: italic;
      }

      .downloadBtn {
        display: flex;
        justify-content: center;
        align-items: center;
        /* margin-left: 500px; */
        margin-top: 10px;
      }
      #downloadBtn {
        display: none;
      }
    </style>
    <script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <div id="myNav"></div>
    <div class="container">
      <h2 class="mb-5" style="text-align: center">My orders</h2>

      <!-- <div class="row">
       
        <div class="col-md-6 form-group offset-md-3">
          
          <button class="btn btn-success" onclick="loadTransactions()">
            Get Transactions
          </button>
        </div>
      </div> -->

     
      <hr />
      <div class="row">
        <div class="col-xs-12">
          <div id="inject"></div>
        </div>
      </div>
    </div>
    <script src="resources/js/bootstrap/bootstrap.bundle.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>
    <script>
      var userId = sessionStorage.getItem("userId");
      var accounts = [];
      (function () {
        $("#myNav").load("resources/nav.html");
        loadTransactions()
        
      })();

      function loadTransactions() {


       showTransactionsApiCall(userId);
      
      }
      var myData;

      async function showTransactionsApiCall(accountNumber) {
        let response = await fetch(
          `http://localhost:5550/ecommerce/order/1`,
          {
            method: "GET",
          }
        );
        var data = await response.json();
        myData = data;
        console.log(data);

        if (data.length == 0) {
          alert("No Transactions Found !!");
          window.open("showTransactions", "_self");
        } else {
          let html = `<div class="row header">
            <div class="col-md-2">Product</div>
            <div class="col-md-2">Quantity</div>
            <div class="col-md-2">Amount</div>
            <div class="col-md-3">Delivery Date</div>
            <div class="col-md-2">Order Date</div>
          </div><hr>`;
          document.getElementById("inject").innerHTML = html;
          data.forEach((item) => {
            const Product = item.image;
            const amount = item.amount;
            const deliverDate = item.deliveryDate;
            const orderDate= item.orderDate;
            const quantity = item.quantity;

            console.log(quantity);

            var html= document.createElement("div");
            html.classList.add("row");
            html.classList.add("transactions");

            var div1 =document.createElement("div");
            div1.classList.add("col-md-2");
            myImage= document.createElement("img");
            myImage.src = "resources/"+Product ;
            myImage.setAttribute("height", "120px");
            myImage.classList.add("images");
            div1.append(myImage);


            var div2 =document.createElement("div");
            div2.classList.add("col-md-2");
            div2.innerHTML=quantity;

            var div3=document.createElement("div");
            div3.classList.add("col-md-2");
            div3.innerText=amount;

              var div4=document.createElement("div");
              div4.classList.add("col-md-3");
              div4.classList.add("overflow-auto");

              div4.innerHTML=deliverDate;

              var div5=document.createElement("div");
              div5.classList.add("col-md-3");
              div5.classList.add("overflow-auto");

              div5.innerHTML=orderDate;


            var inject = document.getElementById("inject");
            html.appendChild(div1);
            html.appendChild(div2);
            html.appendChild(div3);
            html.appendChild(div4);
            html.appendChild(div5);
            inject.appendChild(html);






            


          });
        }
      }

      


      

        
    </script>
  </body>
</html>
