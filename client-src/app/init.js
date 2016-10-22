require(['environment', 'logger', 'resource', 'forms', './xhr'], function (F, Logger, Resource, Forms, Xhr) {
    var global = this;
    F.cacheBust(true);
    Resource.loadText('subscriptions.layout', function (aLayout) {
        Xhr.get('repo/subscriptions', function (subscriptions) {
            Logger.info('subscriptions: ' + subscriptions);
        }, function (e) {
            Logger.severe(e);
        });
        var form = Forms.readForm(aLayout);
        form.show();
        form.btnClose.onActionPerformed = function () {
            form.close();
        };
    }, function (e) {
        Logger.severe(e);
    });
});