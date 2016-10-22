/**
 * Do not edit this file manually, it will be overwritten by
 * Platypus Application Designer.
 */
require(['environment', 'logger'], function (F, Logger) {
    var global = this;
    F.cacheBust(true);
    //F.export(global);
    require('subscriptions', function (subscriptions) {
        var m = new subscriptions();
        m.start(function (form) {
            form.show();
        }, function (e) {
            Logger.severe(e);
        });
    }, function (e) {
        Logger.severe(e);
        if (global.document) {
            var messageParagraph = global.document.createElement('p');
            global.document.body.appendChild(messageParagraph);
            messageParagraph.innerHTML = 'An error occured while require(\'subscriptions\'). Error: ' + e;
            messageParagraph.style.margin = '10px';
            messageParagraph.style.fontFamily = 'Arial';
            messageParagraph.style.fontSize = '14pt';
        }
    });
});