const base_url = "https://smartplantpot.me/";
const temperatureElement = document.getElementById("temperature");
const humidityElement = document.getElementById("humidity");
const imageElement = document.getElementById("image");
const timetemperature = document.getElementById("timetemperature");
const timehumidity = document.getElementById("timehumidity");
const timeimage = document.getElementById("timeimage");
let exampleSocket;
const geolocationOptions = {
  enableHighAccuracy: true,
  timeout: 5000,
  maximumAge: 0
};

if (navigator.geolocation) {
  navigator.geolocation.getCurrentPosition(geolocationSuccess, geolocationError, geolocationOptions);
}
function geolocationSuccess(position) {
  const latitude = position.coords.latitude;
  const longitude = position.coords.longitude;
  console.log(`Latitude: ${latitude}, Longitude: ${longitude}`);
}

function geolocationError(error) {
  console.error(`Error getting geolocation: ${error.message}`);
}


function setupWebSocket() {
  // WebSocket handling
  exampleSocket = new WebSocket("ws://smartplantpot.me/pushes", []);

  exampleSocket.onopen = (event) => {
    console.log("WebSocket opened:", event);
  };

  exampleSocket.onmessage = (event) => {
    console.log("WebSocket message received:", event.data);
    const msg = JSON.parse(event.data);
    const currentTime = new Date();
    timeStr = currentTime.toLocaleTimeString();
    if (msg.temperature){
      console.log(msg.temperature);
      console.log(msg.humidity);
      console.log(timeStr);
      if (msg.temperature!=1000 && msg.humidity!=120){
        temperatureElement.textContent = `${msg.temperature}°C`;
        humidityElement.textContent = `${msg.humidity}%`;
        timetemperature.textContent = `${timeStr} AM`;
        timehumidity.textContent = `${timeStr} AM`;
      }
    }
    if (msg.image){
      console.log(msg.image);
      imageElement.textContent = `${msg.image}`;
      timeimage.textContent=`${timeStr} AM`;
    }

  };
  exampleSocket.onerror = (event) => {
    console.error("WebSocket error:", event);
  };

  exampleSocket.onclose = (event) => {
    console.log("WebSocket closed:", event);
    // Reopen the WebSocket after a delay (e.g., 1 second)
    setTimeout(setupWebSocket, 1000);
  };
}

// Call the setupWebSocket function to initiate the WebSocket connection
setupWebSocket();



$(document).ready(function () {
  $.ajax({
    url: base_url + "api/profile/",
    type: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: Authorizationheader,
    },
    success: function (data) {
      var tbl = document.getElementById("tableuser");
      //tbl.setAttribute("id", "mytable");
      let thead = document.createElement("thead");
      tbl.append(thead);
      let row_1 = document.createElement("tr");
      let heading_0 = document.createElement("th");
      heading_0.innerHTML = "Index";
      let heading_1 = document.createElement("th");
      heading_1.innerHTML = "Mail";
      let heading_2 = document.createElement("th");
      heading_2.innerHTML = "Name";
      let heading_3 = document.createElement("th");
      heading_3.innerHTML = "Role";
      row_1.appendChild(heading_0);
      row_1.appendChild(heading_1);
      row_1.appendChild(heading_2);
      row_1.appendChild(heading_3);

      thead.appendChild(row_1);

      var tbdy = document.createElement("tbody");
      for (var i = 0; i < data.length; i++) {
        var tr = document.createElement("tr");
        var role;
        if (data[i].permissionLevel == 1) {
          role = "Surfer";
        } else {
          role = "Admin";
        }
        var block = [i + 1, data[i].mail, data[i].fullname, role];
        for (var j = 0; j < 4; j++) {
          var td = document.createElement("td");
          td.innerHTML = block[j];
          tr.appendChild(td);
        }
        tbdy.appendChild(tr);
      }
      tbl.appendChild(tbdy);
    },
  });
});
var accesstoken = localStorage.getItem("accesstoken");
var mail = localStorage.getItem("mail");
var Authorizationheader = "Bearer " + accesstoken;
console.log(accesstoken);

$.ajax({
  url: "base_url" + mail,
  type: "GET",
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: Authorizationheader,
  },
  success: function (data) {
    console.log("Load was performed.");
    console.log(data);
    //document.getElementById("displayname").innerHTML = data.fullname
  },
});

