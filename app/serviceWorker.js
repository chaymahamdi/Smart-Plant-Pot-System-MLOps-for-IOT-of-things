const smartplantDev = "dev-smartplantpot-app";
const assets = [
  "/",
  "/index.html",
   "/dashboard.html",
  "/css/style.css",
  "assets/img/favicon.png",
  "assets/img/apple-touch-icon.png",
    "images/img1.jpg",
  "images/img2.jpg",
  "images/img3.jpg",
  "images/img4.jpg",
  "assets/vendor/bootstrap/css/bootstrap.min.css",
  "assets/vendor/bootstrap-icons/bootstrap-icons.css",
  "assets/vendor/boxicons/css/boxicons.min.css",
  "assets/vendor/quill/quill.snow.css",
  "assets/vendor/quill/quill.bubble.css",
  "assets/vendor/remixicon/remixicon.css",
  "assets/vendor/simple-datatables/style.css",
  "assets/css/style.css",
  "assets/vendor/apexcharts/apexcharts.min.js",
  "assets/vendor/bootstrap/js/bootstrap.bundle.min.js",
  "assets/vendor/chart.js/chart.umd.js",
  "assets/vendor/echarts/echarts.min.js",
  "assets/vendor/quill/quill.min.js",
  "assets/vendor/simple-datatables/simple-datatables.js",
  "assets/vendor/tinymce/tinymce.min.js",
  "assets/vendor/php-email-form/validate.js",
  "assets/vendor/jquery/jquery-3.2.1.min.js",
  "assets/js/main.js",
  "assets/js/app.js",
  "assets/js/index.js",
  "assets/js/dashboard.js"

];

self.addEventListener("install", installEvent => {
  installEvent.waitUntil(
    caches.open(smartplantDev).then(cache => {
      cache.addAll(assets);
    })
  );
});

self.addEventListener("fetch", fetchEvent => {
  fetchEvent.respondWith(
    caches.match(fetchEvent.request).then(res => {
      return res || fetch(fetchEvent.request);
    })
  );
});
