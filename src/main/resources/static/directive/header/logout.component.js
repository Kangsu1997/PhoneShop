angular.module('myAdmin').controller('LogoutController',LogoutController);
LogoutController.$inject=['adminService','$log','$state','$scope'];
function LogoutController(adminService, $log,$state,$scope) {
    var vm = this;
    $scope.SendLogout = function () {
        adminService.postLogout().then(function (response) {
            vm.d = response;
            $log.log(vm.d);
            vm.logoutSuccess();
        },function (response) {
            vm.error = response;
        })
    };
    vm.logoutSuccess = function () {
        alert('logout');

        $state.go("product");
    };


}