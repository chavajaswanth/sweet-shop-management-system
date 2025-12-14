const token = "PASTE_JWT_TOKEN_HERE";

function loadSweets() {
  fetch("/api/sweets", {
    headers: {
      "Authorization": "Bearer " + token
    }
  })
  .then(res => res.json())
  .then(data => {
    const list = document.getElementById("sweets");
    list.innerHTML = "";
    data.forEach(s => {
      const li = document.createElement("li");
      li.innerText = `${s.name} - ₹${s.price} (${s.quantity})`;
      list.appendChild(li);
    });
  })
  .catch(err => alert("Unauthorized or error"));
}
