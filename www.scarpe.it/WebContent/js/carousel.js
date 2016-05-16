var oldImg;
var clicked;

function mouseEnter(){
  clicked = false;
}

function mouseOver(image){
  oldImg = document.getElementById('main-img').src;
  document.getElementById('main-img').src = image.src;
}

function mouseClick() {
  clicked = true;
}

function mouseOut(){
  if(!clicked){
    document.getElementById('main-img').src=oldImg;
  }
}
