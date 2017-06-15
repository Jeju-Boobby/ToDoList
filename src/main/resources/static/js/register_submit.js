/**
 * Created by Boobby on 17-6-14.
 */
function submit() {
  var id = document.getElementById("id").value;

  if (id == "") {
    alert("ID를 입력하세요.");
    return;
  }

  var password = document.getElementById("password").value;

  if (password == "") {
    alert("Password를 입력하세요.");
    return;
  }

  var name = document.getElementById("name").value;

  if (name == "") {
    alert("이름을 입력하세요.");
    return;
  }

  var job = document.getElementById("job").value;

  if (job == "") {
    alert("직업을 입력하세요.");
    return;
  }

  document.getElementById("form").submit();
}