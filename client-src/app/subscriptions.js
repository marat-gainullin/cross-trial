/**
 * 
 * @author marat.gaynullin
 */
define('subscriptions', ['orm', 'forms', 'ui', 'id', 'resource', 'logger', 'invoke', 'loader', 'proxies'], function (Orm, Forms, Ui, Id, Resource, Logger, Invoke, whenLoaded, Proxies) {
    function module_constructor() {
        var self = this;
        this.start = whenLoaded('subscriptions.layout', function (aLayout) {
            var form = Forms.readForm(aLayout);
            var data = [
                {id: 1, amount: 20, description: "Guide park"},
                {id: 2, amount: 30, description: "Fifth aveneu"},
                {id: 3, amount: 40, description: "CIA story"},
                {id: 5, amount: 60, description: "Pizza hutt code of conduct"}
            ];
            var wrappedData = Proxies.wrapArray(data,
                    function(items){
                        form.modelGrid.changed(items);
                        form.modelGrid1.changed(items);
                    },
                    function(items){
                        form.modelGrid.added(items);
                        form.modelGrid1.added(items);
                    },
                    function(items){
                        form.modelGrid.removed(items);
                        form.modelGrid1.removed(items);
                    });
            form.modelGrid.data = wrappedData;
            form.modelGrid1.data = wrappedData;
            form.btnAdd.onActionPerformed = function () {
                form.modelGrid.data.splice(1, 0, {id: Id.generate(), amount: 50, description: "Pall Joe"});
            };
            form.btnDelete.onActionPerformed = function () {
                form.modelGrid.data.splice(1, 1);
            };
            form.btnEdit.onActionPerformed = function () {
                form.modelGrid.data[1].description += '*';
            };
            return form;
        });
    }
    return module_constructor;
});
