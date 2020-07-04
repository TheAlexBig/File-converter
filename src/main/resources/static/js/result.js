window.onload  = function (){
    function download(filename, text) {
        const element = document.createElement('a');
        element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
        element.setAttribute('download', filename);

        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
    }

    const elements = document.querySelectorAll(".download");

    elements.forEach(function (e) {
        e.addEventListener("click", function(e){
            const element = e.target;
            const text = element.parentNode.querySelector('textarea').value;
            const type = element.parentNode.querySelector('span').textContent;
            let filename = "resultado";
            if(type === "jwt"){
                filename=filename+".txt";
            }
            else {
                filename=filename+"."+type;
            }
            download(filename, text);
        }, false);
    });
}


