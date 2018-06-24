angular
    .module('myProduct')
    .factory('ProductService', ProductService);
ProductService.$inject = ['$http'];
function ProductService($http) {
    var vm = this;
    vm.dataProduct = '/api/products/';
    vm.dataCategory = '/api/categories/';
    return {
        // begin product index
        getAllProducts: getAllProducts,
        getById: getById
    };
    <!-- trang index-->
    function getAllProducts(filter) {
        var _SEARCH_URL = '/api/products?page=' + (filter.page - 1) + '&size=' + filter.size;

        if (filter.search && filter.search.length) {
            _SEARCH_URL = _SEARCH_URL + '&name=' + filter.search;
        }

        return $http.get(_SEARCH_URL).then(function (resp) {
            return resp.data;
        });
    }
    function getById(id) {
        return $http.get(vm.dataProduct + id);
    }
    function getCategory() {
        return $http.get(vm.dataCategory);
    }
}