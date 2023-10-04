function validarSenha(){
    var senha1 = document.getElementById("senha1");
    var senha2 = document.getElementById("senha2");

    if (senha1.value !== senha2.value) {
        window.alert("As senhas estão diferentes");
        senha2.focus();
        senha2.style.borderColor = "#ae6464"
        return false;
    } else {
        window.alert("Tudo certo");
        
    }

 
}

let slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
}