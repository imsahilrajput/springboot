<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Flipkart</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="resources/styles/styles.css" />
    <style>
      input {
        all: unset;
        height: 35px;
      }
      a {
        pointer-events: none;
      }
      #myForm {
        padding-top: 250px;
      }
      a {
        pointer-events: all;
        text-decoration: none;
      }
      .registration-link {
        padding-left: 128px;

        padding-top: 20px;
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

      <div class="row">
        <div class="col-md-4 offset-md-4 login-form" id="myForm">
          <form>
            <div class="form-group mb-3">
              <label for="username" class="form-label">Username</label>

              <input
                type="text"
                class="form-control"
                id="username"
                data-username
              />
              <label for="password" class="form-label">Password</label>
              <input
                type="password"
                class="form-control"
                id="password"
                minlength="6"
                data-password
              />
            </div>
            <div class="col-md-2 offset-md-5">
              <button type="button" class="btn btn-success" onclick="login()">
                Login
              </button>
            </div>
            <div class="col-md-12 registration-link">
              <a href="register">Create a new Account</a>
            </div>
          </form>
        </div>
      </div>
    </div>

    <script src="resources/js/bootstrap/bootstrap.bundle.min.js"></script>

    <script>
      $(function () {
        $('#myNav').load('resources/nav.html')
      })

      function login() {
        const username = document.querySelector('[data-username]').value
        const password = document.querySelector('[data-password]').value
        const credentials = {
          userName: username,
          password: password,
        }
        console.log(credentials);

        // console.log(credentials)
        loginApiCall(credentials)
      }

      async function loginApiCall(data) {
        // console.log(data);
        let response = await fetch('http://localhost:5550/ecommerce/login', {
          headers: { 'Content-type': 'application/json' },
          method: 'POST',
          body: JSON.stringify(data),
        })

        let datas = await response.json()
        console.log(datas)

        if (datas.message == 'Success' && datas.role == 'customer') {
          sessionStorage.setItem('userId', datas.userId)
          sessionStorage.setItem('role', datas.role)
          window.open('dashboard', '_self')
        } else if (datas.message == 'Success' && datas.role == 'admin') {
          sessionStorage.setItem('userId', datas.userId)
          sessionStorage.setItem('role', datas.role)
          window.open('adminDashboard', '_self')
        } else if (datas.message == 'Invalid Credentials') {
          window.alert('Invalid Credentials')
        }
      }
    </script>
  </body>
</html>
