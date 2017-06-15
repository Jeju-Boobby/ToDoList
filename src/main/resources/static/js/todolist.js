/**
 * Created by Boobby on 17-6-14.
 */
window.onload = function () {
  var today = new Date();
  var yyyy = today.getFullYear();
  var date = today.getDate();
  var mm = today.getMonth() + 1;

  if (date < 10) {
    date = "0" + date;
  }

  if (mm < 10) {
    mm = "0" + mm;
  }

  today = yyyy + "-" + mm + "-" + date;
  document.getElementById("date").setAttribute("min", today);
}

function submit() {
  var date = document.getElementById("date").value;
  var time = document.getElementById("time").value;
  var change = date + " " + time;

  document.getElementById("planTime").value = change;

  document.getElementById("todo").submit();
}