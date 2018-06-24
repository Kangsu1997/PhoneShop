angular.module('myAdmin').controller('RegisterController', RegisterController);

RegisterController.$inject = ['$log', 'adminService', '$state', '$stateParams'];

function RegisterController($log, adminService, $state) {
    var vm = this;

    vm.create = function () {
        adminService.postRegister(vm.createRegister).then(function (response){
            $log.log(response);
            alert('dang ki thanh cong ');
            $state.go("product");
        }, function (response) {
            vm.error = response.data;
        });
    };
    $log.log(vm);

}