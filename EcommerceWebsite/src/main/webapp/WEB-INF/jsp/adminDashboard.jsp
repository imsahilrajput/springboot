<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Rules Engine</title>
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

      td,
      th {
        height: 60px;
      }
    </style>
    <script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
    <script src="resources/js/logout.js"></script>
  </head>

  <body>
    <div id="myNav"></div>
    <div class="container">
      <h2 class="mb-5" style="text-align: center">Admin Dashboard</h2>

      <div class="row mt-5">
        <div class="col-md-12">
          <table class="table border-dark text-center">
            <thead>
              <tr>
                <th>#</th>
                <th>Customer</th>
                
              </tr>
            </thead>
            <tbody id="inject"></tbody>
          </table>
        </div>
      </div>
    </div>

    <script src="resources/js/bootstrap/bootstrap.bundle.min.js"></script>

    <script>
      var arr;
      var select;
      var iter = 0;
      

      (function () {
        var role=sessionStorage.getItem("role");

        myFunction1();
      })();

      async function getUsersApiCall() {
        let response = await fetch("http://localhost:5550/ecommerce/customers", {
          method: "GET",
        });
        let data = await response.json();
        console.log(data);

        data.forEach(createTable);
        document.getElementById("info").style.display="none";


        var tenantlinksAll = document.getElementsByName("tenantLinks");
        for (var i = 0; i < tenantlinksAll.length; i++) {
          tenantlinksAll[i].style.display = "none";
        }

        document.getElementById("logo").setAttribute("href","adminDashboard");
      }

      function myFunction1() {
        sessionStorage.setItem("tenantId", null);
                    $("#myNav").load("resources/nav.html");
        getUsersApiCall();
      }

      function createTable(item) {
        
        var userId = item.customerId;
        var userName = item.customerName;
        console.log(userId);
        console.log(userName);
        console.log(item.role);
        if (item.role == "customer") {
          addMore(userId, userName);
        }
      }
      function addMore(userId, userName) {
        var table = document.getElementById("inject");
        var tableRow = document.createElement("tr");
        var td0 = document.createElement("th");
        td0.textContent = iter + 1;

        var td1 = document.createElement("td");
        td1.setAttribute("hidden", "");
        td1.setAttribute("id", `userId[${iter}]`);
        var td2 = document.createElement("td");
        td2.setAttribute("id", `name[${iter}]`);
        var td3 = document.createElement("td");

        td1.innerHTML = userId;
        td2.innerHTML = userName;
        
        tableRow.append(td0, td1, td2);
        table.appendChild(tableRow);
        iter++;


      }

      function view(item) {
        let userId = document.getElementById(`userId[${item}]`).innerText;
        let userName = document.getElementById(`name[${item}]`).innerText;
        sessionStorage.setItem("userId", userId);
        sessionStorage.setItem("userName", userName);
        window.open("dashboard.html", "_self");
      }
    </script>
  </body>
</html>
