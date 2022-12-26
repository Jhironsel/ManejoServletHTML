function validarFormulario(forma) {
    const usuario = forma.txtUsuario;
    if (usuario.value === '') {
        alert('Debe ingresar un usuario!');
        usuario.focus();
        return false;
    }

    const password = forma.txtPassword;
    if (password.value === '') {
        alert('Debe ingresar la contraseña!');
        password.focus();
        return false;
    }

    const tecnologias = forma.Tecnologia;
    var valorTec = false;

    for (var i = 0; i < tecnologias.length; i++) {
        if (tecnologias[i].checked) {
            valorTec = true;
            break;
        }
    }

    if (!valorTec) {
        alert('Marque una tecnologia seleccionable!');
        return false;
    }
    
    let ocupacion = forma.ocupacion;
    
    if(ocupacion.value === "0"){
        alert('Debe seleccionar una ocupacion!');
        return false;
    }
    
    
    return true;
}