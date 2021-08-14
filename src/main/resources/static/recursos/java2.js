var botao = document.getElementById("teste");

botao.addEventListener("mousemove", function(){
  var elemento = document.getElementById("elemento");
    if(elemento.style.display === "none"){
      elemento.style.display = "inline-block";
    }else {
      elemento.style.display = "none";
    }
});