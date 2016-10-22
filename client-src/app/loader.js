define(['forms', 'resource', 'logger', 'invoke'], function (Forms, Resource, Logger, Invoke) {
    function loader(aResourceName, filterContent) {
        var started = false;
        var finished = false;
        var calls = [];
        var filteredContent = null;
        var loadError = null;

        return function(onSuccess, onFailure) {
            if (finished) {
                if (filteredContent) {
                    Invoke.later(function () {
                        onSuccess(filteredContent);
                    });
                } else {
                    Invoke.later(function () {
                        onFailure(loadError);
                    });
                }
            } else {
                calls.push({onSuccess: onSuccess, onFailure: onFailure});
                if (!started) {
                    started = true;
                    Resource.loadText(aResourceName, function (aContent) {
                        finished = true;
                        filteredContent = filterContent(aContent);
                        calls.forEach(function (aCall) {
                            Invoke.later(function () {
                                aCall.onSuccess(filteredContent);
                            });
                        });
                    }, function (e) {
                        finished = true;
                        loadError = e;
                        calls.forEach(function (aCall) {
                            Invoke.later(function () {
                                aCall.onFailure(e);
                            });
                        });
                        Logger.severe(e);
                    });
                }
            }
        };
    }
    return loader;
});
