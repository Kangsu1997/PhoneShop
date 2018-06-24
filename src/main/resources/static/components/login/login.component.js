angular.module('myAdmin').controller('LoginController',LoginController);
LoginController.$inject=['adminService','$log','$state','$rootScope','$window','$scope'];
function LoginController(adminService, $log,$state,$scope) {
    var vm = this;
    vm.SendLogin = function () {
        adminService.postLogin(vm.login).then(function (response) {
            // $scope.user = response.data[1].authority;
            // $scope.image = response.data[2].authority;
            vm.loginSuccess(response);
        },function (response) {
            vm.loginError();
        });
    };
    vm.loginSuccess = function (success) {
        vm.admin="ROLE_Role(id=1, role=ADMIN)";
        if(vm.admin == success.data[0].authority ){

             $state.go("product");
        }else {
            $state.go("product");

        }
        console.log("ket qua  " + success.data[0].authority);

    };
    vm.loginError = function() {
        alert('user hoặc pass sai');
    };

    $log.log(vm);
    //login with facebook with google

/*
    $scope.gmail = {
        username: "",
        email: ""
    };
    $scope.facebook = {
        username: "",
        email: ""
    };
    $scope.onGoogleLogin = function () {
        var params = {
            'clientid': '721811914744-p96vnc7i1batpb2cq9h4odis8umjbc63.apps.googleusercontent.com',
            'cookiepolicy': 'single_host_origin',
            'callback': function (result) {
                if (result['status']['signed_in']) {
                    var request = gapi.client.plus.people.get(
                        {
                            'userId': 'me'
                        }
                    );
                    request.execute(function (resp) {
                        $scope.$apply(function () {
                            $scope.gmail.username = resp.displayName;
                            $scope.gmail.email = resp.emails[0].value;
                            $scope.g_image = resp.image.url;
                            $state.go("product");
                        });
                    });
                }

            },
            'approvalprompt': 'force',
            'scope': 'https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.profile.emails.read'
        };
        gapi.auth.signIn(params);

    };
    $scope.onFBLogin = function () {
        FB.login(function (response) {
            if (response.authResponse) {
                FB.api('/me', 'GET', {fields: 'email, first_name, name, id, picture'}, function (response) {
                    $scope.$apply(function () {
                        $scope.facebook.username = response.name;
                        $scope.facebook.email = response.email;
                        $scope.fb_image = response.picture.data.url;
                        $state.go("product");
                    });
                });
            } else {
                //error
            }
        }, {
            scope: 'email, user_likes',
            return_scopes: true
        });
    }*/



}