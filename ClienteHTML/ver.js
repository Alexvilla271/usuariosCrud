/*function ini(){
    mensaje = '<?xml version="1.0" encoding="utf-8"?>' +
    '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">' +
    '<Body>' +
    '<BusquedaUsuarioRequest xmlns="http://tell.me/usuarios">' +
    '</BusquedaUsuarioRequest>' +
    '</Body>' +
    '</Envelope>';
}

function verUsuario() {
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
        //window.alert(valor);
    })
    .catch(err => console.log(err));
}

function resultado(rXml){
    var txt ='';
    var id,nombre,tipo;
    var parser = new DOMParser();
    var xmlDoc = parser.parseFromString(rXml, "text/xml");
    var res = xmlDoc.getElementsByTagName("ns2:producto");
    var resul = res.length;
    for(i=0; i<resul;i++){
      id = xmlDoc.getElementsByTagName("ns2:id")[i].childNodes[0].nodeValue;
      nombre = xmlDoc.getElementsByTagName("ns2:nombre")[i].childNodes[0].nodeValue;
      tipo = xmlDoc.getElementsByTagName("ns2:tipo")[i].childNodes[0].nodeValue;
      console.log("Id: "+id+ " nombre: "+nombre+ " tipo: "+tipo);
      txt+= "Id: "+id+ " nombre: "+nombre+ " tipo: "+tipo+ "<br>";
    }
    document.getElementById("demo").innerHTML = txt;
    return "Existen "+ resul +" usuarios registrados";
}*/

function ini(){
    mensaje= '<?xml version="1.0" encoding="utf-8"?>' +
    '<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">'+
    '<Body>'+
        '<BusquedaUsuarioRequest xmlns="http://tell.me/usuarios">'+
            '<id>'+document.getElementById('Id').value+'</id>'+
        '</BusquedaUsuarioRequest>'+
    '</Body>'+
'</Envelope>'

}


//Mostrar empleados
function verUsuario(){
    ini()
    axios.post('http://localhost:8080/ws/usuarios', mensaje,{
        headers:{
            'Content-Type' : 'text/xml'
        }
    })
    .then(function (response){
        var mainContainer = document.getElementById("myData");
        var nombre = document.createElement("h3");
        var tipo = document.createElement("h3");
        var response2=mostrarEmpResp(response.data);
        nombre.innerHTML = 'Nombre: ' + response2[0] + "<br>";
        tipo.innerHTML = 'Tipo: ' + response2[1]+"<br>";
        mainContainer.appendChild(nombre);
        mainContainer.appendChild(tipo);
        
        console.log(mostrarEmpResp(response.data));
        
    })
    .catch(err => console.log(err));
}