angular.module('myProduct').controller('ProductShopController', ProductShopController);
ProductShopController.$inject = ['ProductService', '$log', '$stateParams', '$state'];
function ProductShopController(ProductService, $log, $stateParams, $state) {
    var vm = this;
    vm.error = "";
    vm.filter = {
        search: $stateParams.search || '',
        page: $stateParams.page || 1,
        size: $stateParams.size || 12,
        fistPrice: $stateParams.fistPrice || '',
        lastPrice: $stateParams.lastPrice || '',
    };
    vm.search = search;
    vm.pageChange = pageChange;
    getData(vm.filter);

    function search() {
        vm.filter.page = 1;
        getData(vm.filter);
    }

    function pageChange() {
        getData(vm.filter);
    }

    function getData(filter) {
        vm.products = [];
        $log.info(filter);
        $state.go($state.current, filter, {notify: false, reload: false, location: 'replace'});
        vm.error = "";
        ProductService.getAllProducts(filter).then(function (resp) {
            vm.products = resp;
            $log.info(vm.products);
            vm.filter.totalItems = resp.total;
        }, function error(errResp) {
            vm.error = "Error loading data";
        });
        $log.log(vm);
    }
}