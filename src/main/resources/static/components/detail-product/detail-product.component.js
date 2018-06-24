angular.module('myProduct').controller("ProductDetailController", ProductDetailController);

ProductDetailController.$inject = ['ProductService', '$log', '$stateParams','$state'];

function ProductDetailController(ProductService,$log,$stateParams,$state ) {
    var vm = this;
    ProductService.getById($stateParams.id).then(function (response) {
        vm.getProductId = response.data;
    }, function (response) {
        vm.error = response.data;
    });
    vm.count = 0;
    vm.myFunT = function () {
        vm.count++;
    };
    vm.myFunG = function () {
        if (vm.count === 0)
            vm.count = 0;
        else
            vm.count--;
    };

    vm.error = "";
    vm.filter = {
        search: $stateParams.search || '',
        page: $stateParams.page || 1,
        size: $stateParams.size || 15
    };

    vm.search = search;
    vm.pageChange = pageChange;

    getData(vm.filter);

    function search(){
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
        }, function error(errResp){
            vm.error = "Error loading data";
        });
    }
    $log.log(vm)

}