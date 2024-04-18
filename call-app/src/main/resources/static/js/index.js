function loadAndDisplayUsers() {
  const connectedUser = localStorage.getItem("connectedUser");
  if (!connectedUser) {
    window.location = "login.html";
    return;
  }

  const userListElement = document.getElementById("userList");

  userListElement.innerHTML = "Loading...";

  fetch("http://localhost:8080/api/v1/users")
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      console.log(data);
      displayUsers(data, userListElement);
    });
}

function displayUsers(userList, userListElement) {
  userListElement.innerHTML = "";

  userList.forEach((user) => {
    const listItem = document.createElement("li");
    listItem.innerHTML = `
                <div>
                    <i class="fa fa-user-circle"></i>
                    ${user.username} <i class="user-email">(${user.email})</i>
                </div>
                <i class="fa fa-lightbulb-o ${
                  user.status === "online" ? "online" : "offline"
                }"></i>
            `;
    userListElement.appendChild(listItem);
  });
}

function handleLogout() {
  fetch("http://localhost:8080/api/v1/users/logout", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: localStorage.getItem("connectedUser"),
  })
    .then((response) => {
      return response;
    })
    .then((data) => {
      localStorage.removeItem("connectedUser");
      window.location.href = "login.html";
    });
}

function handleNewMeeting() {
  const connectedUser = JSON.parse(localStorage.getItem("connectedUser"));
  window.open(`videocall.html?username=${connectedUser.username}`, "_blank");
}

function handleJoinMeeting() {
  const roomId = document.getElementById("meetingName").value;
  const connectedUser = JSON.parse(localStorage.getItem("connectedUser"));

  const url = `videocall.html?roomID=${roomId}&username=${connectedUser.username}`;

  window.open(url, "_blank");
}

const joinMeetingBtn = document.getElementById("joinMeetingBtn");
joinMeetingBtn.addEventListener("click", handleJoinMeeting);

const newMeetingBtn = document.getElementById("newMeetingBtn");
newMeetingBtn.addEventListener("click", handleNewMeeting);

const logoutBtn = document.getElementById("logoutBtn");
logoutBtn.addEventListener("click", handleLogout);

window.addEventListener("load", loadAndDisplayUsers);
