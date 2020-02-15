window.onload=function () {
    debugger;
    var closable=window.localStorage.getItem("closable");
    if(closable){
        window.close();
        window.localStorage.removeItem("closable");
    }
}