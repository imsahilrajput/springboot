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
      .category {
        display: grid;
        place-content: center;
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
        <div class="col-md-3 category">
            <a href="mobile"><img src="resources/mobile.png" alt="" height="100px" />
                <figcaption style="text-align: center;">Mobile</figcaption></a>
          
        </div>
        <div class="col-md-3 category">
            <a href="sports"><img src="resources/sports.png" alt="" height="100px" />
                <figcaption style="text-align: center;">Sports</figcaption></a>
          
        </div>
        <div class="col-md-3 category">
            <a href="clothes"><img src="resources/clothes.png" alt="" height="100px" />
                <figcaption style="text-align: center;">Clothes</figcaption></a>
          
        </div>
        <div class="col-md-3 category">
            <a href="laptop"> <img src="resources/laptop.png" alt="" height="100px" />
                <figcaption style="text-align: center;">Electronics</figcaption></a>
         
        </div>
      </div>

      <div
        id="carouselExampleInterval"
        class="carousel carousel-dark slide"
        data-bs-ride="carousel"
        style="height: 445px; width: 100%; float: right;"
      >
        <div class="carousel-inner" style="height: 550px; margin-top: 10px;">
          <div class="carousel-item active" data-bs-interval="2000">
            <img
              src="resources/slider1.png"
              style="height: 350px;"
              class="d-block w-100"
              alt="First"
            />
          </div>
          <div class="carousel-item" data-bs-interval="2000">
            <img
              src="resources/slider2.png"
              style="height: 350px;"
              class="d-block w-100"
              alt="Second"
            />
          </div>
        </div>
        <button
          class="carousel-control-prev"
          type="button"
          data-bs-target="#carouselExampleInterval"
          data-bs-slide="prev"
        >
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button
          class="carousel-control-next"
          type="button"
          data-bs-target="#carouselExampleInterval"
          data-bs-slide="next"
        >
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>
   

    <div>
      <img src="resources/footer.png" alt="" width="100%" />
    </div>
    <script src="resources/js/bootstrap/bootstrap.bundle.min.js"></script>

    <script>
      $(function () {
        $('#myNav').load('resources/nav.html')
      })
    </script>
  </body>
</html>
