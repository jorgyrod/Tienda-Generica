/**
 * 
 */
const cedula = document.getElementsByName("cedula");
const nombre = document.getElementsByName("nombre");
const correo = document.getElementsByName("email");
const usuario = document.getElementsByName("usuario");
const password = document.getElementsByName("password");

const boton = document.getElementsByName("Agregar");
const btnActulizar = document.getElementsByName("Actualizar");;
const btnConsultar = document.getElementsByName("Listar");
const btnEliminar = document.getElementsByName("Eliminar");


const resultado = document.querySelector(".div-parrafo");

//Validar campos Crear y Actualizar
//---------------------------------

function limipiar(){
    cedula[0].value = "";
    nombre[0].value = "";
    correo[0].value = "";
    usuario[0].value = "";
    password[0].value = "";
} 

boton[0].addEventListener("click",(e)=>{
    //Para evitar que se envie el formulario con campos vacios y sin recargarse
    e.preventDefault();
    let error = validaCamposRegistro();
    if(error[0]){
        resultado.innerHTML = error[1];
        resultado.classList.remove("parrafo-green");
        resultado.classList.add("parrafo-red");
    }else{
        resultado.innerHTML = "Solicitud enviada correctamente";
        resultado.classList.remove("parrafo-red");
        resultado.classList.add("parrafo-green");
    }
    limipiar();
});


btnActulizar[0].addEventListener("click",(e)=>{
    //Para evitar que se envie el formulario con campos vacios y sin recargarse
    e.preventDefault();
    let error = validaCamposRegistro();
    if(error[0]){
        resultado.innerHTML = error[1];
        resultado.classList.remove("parrafo-green");
        resultado.classList.add("parrafo-red")
    }else{
        resultado.innerHTML = "Solicitud enviada correctamente";
        resultado.classList.remove("parrafo-red");
        resultado.classList.add("parrafo-green");
    }
    limipiar();
});



const validaCamposRegistro = ()=>{
    let error = [];

    if(cedula[0].value.length < 3){
        error[0] = true;
        error[1] = "La cedula no puede ser vacia";
        return error;
    } else if(nombre[0].value.length < 5){
        error[0] = true;
        error[1] = "El nombre no puede contener menos de 5 caracteres";
        return error;
    } else if(correo[0].value.length < 5 || correo[0].value.indexOf("@") == -1 || correo[0].value.indexOf(".") == -1){
        error[0] = true;
        error[1] = "El mail es invalido";
        return error;
    } else if(usuario[0].value.length < 5){
        error[0] = true;
        error[1] = "El nombre de usuario debe ser mayor a 5 caracteres";
        return error;
    } else if(password[0].value.length < 5){
        error[0] = true;
        error[1] = "La contraseña debe ser mayor a 5 caracteres";
        return error;
    }
    error[0] = false;
    return error;
}


//Validar campos Consultar y Elimimnar
//---------------------------------


btnConsultar[0].addEventListener("click",(e)=>{
    //Para evitar que se envie el formulario con campos vacios y sin recargarse
    e.preventDefault();
    let error = validaCampos();
    if(error[0]){
        resultado.innerHTML = error[1];
        resultado.classList.remove("parrafo-green");
        resultado.classList.add("parrafo-red");
    }else{
        resultado.innerHTML = "Solicitud enviada correctamente";
        resultado.classList.remove("parrafo-red");
        resultado.classList.add("parrafo-green");
    }
    limipiar();
});

btnEliminar[0].addEventListener("click",(e)=>{
    //Para evitar que se envie el formulario con campos vacios y sin recargarse
    e.preventDefault();
    let error = validaCampos();
    if(error[0]){
        resultado.innerHTML = error[1];
        resultado.classList.remove("parrafo-green");
        resultado.classList.add("parrafo-red");
    }else{
        resultado.innerHTML = "Solicitud enviada correctamente";
        resultado.classList.remove("parrafo-red");
        resultado.classList.add("parrafo-green");
    }
    limipiar();
});


const validaCampos = ()=>{
    let error = [];

    if(cedula[0].value.length < 1){
        error[0] = true;
        error[1] = "La cedula no puede ser vacia";
        return error;
    } 
    error[0] = false;
    return error;
}

