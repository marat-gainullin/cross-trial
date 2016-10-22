define(function () {
    function wrap(item, onChange) {
        var proxy = new Proxy(item, {
            set: function (original, prop, value) {
                original[prop] = value;
                onChange(proxy);
            }});
        return proxy;
    }
    function wrapArray(target, onChange, onAdded, onRemoved) {
        var proxies = [];
        function wrap(item) {
            var proxy = new Proxy(item, {
                set: function (original, prop, value) {
                    original[prop] = value;
                    onChange(proxy);
                }});
            return proxy;
        }
        function wrapA(anArrayLike) {
            var wrapped = [];
            for (var w = 0; w < anArrayLike.length; w++) {
                wrapped.push(wrap(anArrayLike[w]));
            }
            return wrapped;
        }
        target.forEach(function (item) {
            proxies.push(wrap(item));
        });
        return new Proxy(proxies, {
            get: function (original, prop) {
                if (prop === 'push') {
                    return function () {
                        var wrapped = wrapA(arguments);
                        var res = Array.prototype.push.apply(proxies, wrapped);
                        if (wrapped.length > 0)
                            onAdded(wrapped);
                        return res;
                    };
                } else if (prop === 'pop') {
                    return function () {
                        if (proxies.length > 0) {
                            var res = Array.prototype.pop.apply(proxies, arguments);
                            onRemoved([res]);
                            return res;
                        }// otherwise returned value is the array's length.
                    };
                } else if (prop === 'shift') {
                    return function () {
                        if (proxies.length > 0) {
                            var res = Array.prototype.shift.apply(proxies, arguments);
                            onRemoved([res]);
                            return res;
                        }// otherwise returned value is the array's length.
                    };
                } else if (prop === 'unshift') {
                    return function () {
                        var wrapped = wrapA(arguments);
                        var res = Array.prototype.unshift.apply(proxies, wrapped);
                        if (wrapped.length > 0)
                            onAdded(wrapped);
                        return res;
                    };
                } else if (prop === 'splice') {
                    return function () {
                        var wrapped = [];
                        if (arguments.length > 0)
                            wrapped.push(arguments[0]);
                        if (arguments.length > 1)
                            wrapped.push(arguments[1]);
                        for (var a = 2; a < arguments.length; a++) {
                            var wrappedItem = wrap(arguments[a]);
                            wrapped.push(wrappedItem);
                        }
                        var removed = Array.prototype.splice.apply(proxies, wrapped);
                        if (removed && removed.length > 0)
                            onRemoved(removed);
                        if (wrapped.length > 2)
                            onAdded(wrapped.slice(2, wrapped.length));
                        return removed;
                    };
                } else {
                    return original[prop];
                }
            }
        });
    }
    return {wrapArray: wrapArray, wrap: wrap};
});
