define(function(){
    function get(url, onSuccess, onFailure){
        req('GET', url, null, onSuccess, onFailure);
    }
    function del(url, onSuccess, onFailure){
        req('DELETE', url, null, onSuccess, onFailure);
    }
    function put(url, data, onSuccess, onFailure){
        req('PUT', url, data, onSuccess, onFailure);
    }
    function post(url, data, onSuccess, onFailure){
        req('POST', url, data, onSuccess, onFailure);
    }

    function req(method, url, data, onSuccess, onFailure){
        var request = new XMLHttpRequest();
        request.onreadystatechange = function(){
            if(request.readyState === 4){
                request.onreadystatechange = null; // avoid memory leak
                if(request.status >= 200 && request.status <= 299) {
                    if (!request.responseType || request.responseType === "text") {
                        var contentType = request.getResponseHeader('content-type');
                        if(contentType && contentType.indexOf('application/json') !== -1)
                            onSuccess(JSON.parse(request.responseText));
                        else
                            onSuccess(request.responseText);
                    } else if (request.responseType === "document") {
                        onSuccess(request.responseXML);
                    } else {
                        onSuccess(request.response);
                    }
                } else {
                    onFailure(request.statusText);
                }
            }
        };
        request.open(method, url);
        if(data)
            request.send(data);
        else
            request.send();

    }
    return {
        get  : get,
        put  : put,
        post : post,
        del  : del
    };
});