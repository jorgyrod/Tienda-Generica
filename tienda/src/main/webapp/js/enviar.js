/**
 * 
 */

const form = document.getElementById("form-archivo");
const input = document.getElementById("input_archivo");
const parrafo = document.getElementById("parrafo");

function subirArchivo(file){
	const url = "http://localhost:5000";
	var formData = new FormData();
	formData.append("file",file);
	
	//Envio
	var http = new XMLHttpRequest();
	http.open("POST", url+"/productos/cargar");
	
	http.onload = function() {
        console.log(http.responseText);
        var response = JSON.parse(http.responseText);
        if(http.status == 200) {
			parrafo.innerText="Archivo subido correctamente!"
			parrafo.classList.add("parrafo-green");
        } else {
            parrafo.innerText="Error al subir el archivo!"
			parrafo.classList.remove("parrafo-green");
			parrafo.classList.add("parrafo-red");

        }
    }

	http.send(formData);
}

form.addEventListener('submit', function(event){
    var files = input.files;
    if(files.length === 0) {
         parrafo.innerText="Seleccione un Archivo";
		 parrafo.classList.remove("parrafo-green");
		 parrafo.classList.add("parrafo-red");
    }
    subirArchivo(files[0]);
    event.preventDefault();
}, true);
