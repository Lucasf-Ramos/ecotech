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