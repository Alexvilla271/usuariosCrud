var mensaje = '';

function ini(){
    mensaje = '<?xml version="1.0" encoding="utf-8"?>' +
    '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">' +
    '<Body>' +
    '<ModificarUsuarioRequest xmlns="http://tell.me/usuarios">' +
    '<id>'+ document.getElementById('inputId').value +'</id>' +
    '<nombre>'+ document.getElementById('inputNombre').value +'</nombre>' +
    '<tipo>'+ document.getElementById('inputTipo').value +'</tipo>' +
    '</ModificarUsuarioRequest>' +
    '</Body>' +
    '</Envelope>';
}

function updateUsuario() {
    ini();
    axios.post('http://localhost:8080/ws/usuarios', mensaje,{
        headers:{
            'Content-Type' : 'text/xml'
        }
    })
    .then(function(response){
        //console.log(response.data);
        var valor = resultado(response.data);
        console.log(valor);
        alert(valor);
    })
    .catch(err => console.log(err));
}

function resultado(rXml){
    var parser = new DOMParser();
    var xmlDoc = parser.parseFromString(rXml, "text/xml");
    var resul = xmlDoc.getElementsByTagName("ns2:respuesta")[0].childNodes[0].nodeValue;
    return resul;
}