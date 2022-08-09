
// navigation bar displaying and hiding elements
let listHeads = document.querySelectorAll('.listHead')

for (head of listHeads) {
  head.addEventListener("click", handler);
}

function handler(e) {
  let listItems = this.nextElementSibling;
  if(listItems.style.display == "block") {
    listItems.style.display = "none";
  }
  else{
    listItems.style.display = "block";
  }
}



