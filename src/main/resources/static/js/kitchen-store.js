function showPassword(element) {
  //  const show_password = document.getElementsByClassName("show-password")[0];
  const password = element.previousElementSibling;
  // toggle the type attribute
  const type =
    password.getAttribute("type") === "password" ? "text" : "password";
  password.setAttribute("type", type);
  //  const password_icon = element.firstElementChild;

  //  if (type === "password") password_icon.src = /*[[@{/img/eye-slash.svg}]]*/;
  //  else password_icon.src = /*[[@{/img/eye.svg}]]*/;
}

function filterItems() {
  const menu = document.getElementById("menu");
  const search = document.getElementById("search").value.toUpperCase();
  const items = menu.getElementsByClassName("item");

  for (let i = 0; i < items.length; i++) {
    let item = items[i].getElementsByTagName("h4")[0].innerHTML.toUpperCase();
    if (item.includes(search)) {
      items[i].classList.add("d-flex");
      items[i].classList.remove("d-none");
    } else {
      items[i].classList.add("d-none");
      items[i].classList.remove("d-flex");
    }
  }
}

function closeResult() {
  const form_search = document.getElementsByClassName("form-search")[0];
  form_search.classList.add("d-none");
}

//document.getElementById("close-search").addEventListener("click", () => {
//    const form_search = document.getElementsByClassName("form-search")[0];
//    form_search.classList.add("d-none");
//});

function changePassword() {
  //    event.preventDefault();
  let passwordForm = document.forms.changePasswordForm;
  let formData = new FormData(passwordForm);
  const password = formData.get("password");
  const confirmPassword = formData.get("confirmPassword");
  if (password == confirmPassword) return true;
  else return false;
}

$(document).ready(function () {
  const url = window.location.href;

  const nav = document.querySelector("nav.navbar");

  if (url.includes("/dish/all") || url.includes("/dish/add") || url.includes("/dish/edit") || url.includes("/dish/all") || url.includes("/user/edit"))
    nav.classList.remove("vh-50");

  if (url.includes("?add-to-cart=true"))
    toast("Dish added to cart successfully", "bg-success");
  else if (url.includes("?dish-removed=true"))
    toast("Dish removed from cart successfully.", "bg-success");
  else if (url.includes("?dishName=error"))
    toast("Dish already exists in database", "bg-warning");
  else if (url.includes("?imageUrl=error"))
    toast("Error while processing Image URL", "bg-danger");
  else if (url.includes("?add-dish=true"))
    toast("Dish added to inventory Successful", "bg-success");
  else if (url.includes("?update-dish=true"))
    toast("Dish details are updated successfully", "bg-success");
  else if (url.includes("?delete-dish=true"))
    toast("Dish removed from inventory Successful", "bg-warning");
  else if (url.includes("?dish-name=false"))
    toast("Dish not available", "bg-warning");
  else if (url.includes("?user-exists=true"))
    toast("You must log-out first to perform the action", "bg-danger");
  else if (url.includes("?user-unauthorized=true"))
    toast("You are not authorized to perform the action", "bg-danger");
  else if (url.includes("?update-user=true"))
    toast("User details updated successfully", "bg-success");
  else if (url.includes("?emailIdExists=true"))
    toast("Email Id already exists in database", "bg-warning");
  else if (url.includes("?sign-up=true"))
    toast("You are successfully registered, try to login", "bg-success");
  else if (url.includes("?change-password=true"))
    toast("Password changed successfully", "bg-success");
  else if (url.includes("?password-not-matching=true"))
    toast("Passwords are not matching", "bg-warning");
  else if (url.includes("?emailNotMatching=true"))
    toast("Email ID doesn't exists", "bg-warning");
  else if (url.includes("?password-reset=true"))
    toast("Password is changed successfully", "bg-success");
  else if (url.includes("?login=true"))
    toast("You've logged-in successfully", "bg-success");
  else if (url.includes("?logout=true"))
    toast("You've logged-out successfully", "bg-success");
  else if (url.includes("?error"))
    toast("Bad credentials", "bg-warning");

  function toast(message, borderColor) {
    const options = {
      animation: true,
      autohide: true,
      delay: 5000,
    };
    const toast = document.getElementById("toast");

    toast.classList.remove("d-none");
    toast.classList.add(borderColor);
    const toast_body = toast.getElementsByClassName("toast-body")[0];
    toast_body.innerHTML = message;
    const bootstrap_toast = new bootstrap.Toast(toast, options);
    bootstrap_toast.show();
  }
});
