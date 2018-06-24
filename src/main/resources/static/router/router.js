angular.module('myApp').config(['$stateProvider', '$urlRouterProvider',
    function config($stateProvider,$urlRouterProvider ) {
        $urlRouterProvider.otherwise('/login');
        $stateProvider
            .state('product', {
                url: '/product',
                reloadOnSearch: false,
                views: {
                    "viewHeader": {
                        template: "<my-header></my-header>"
                    },
                    "viewB1": {
                        templateUrl: "components/shop/shop-product.template.html",
                        controller: 'ProductShopController',
                        controllerAs: '$ctrl'
                    },
                    "viewFooter": {
                        template: "<my-footer></my-footer>"
                    }
                }
            })
            // admin
            .state('register', {
                url: '/register',
                reloadOnSearch: false,
                views: {
                    "viewB": {
                        templateUrl: 'components/register.template.html',
                        controller: RegisterController,
                        controllerAs: '$ctrl'
                    }
                }

            })
            // .state('admin', {
            //     url: '/admin?page&size&search',
            //     reloadOnSearch: false,
            //     views: {
            //         "viewHeader": {
            //             template: "<header-admin></header-admin>"
            //         },
            //         "viewB": {
            //             templateUrl: 'components/admin/admin-users.template.html',
            //             controller: AdminController,
            //             controllerAs: '$ctrl'
            //         }
            //     }
            // })
            // .state('edit', {
            //     url: '/edit/:id',
            //     reloadOnSearch: false,
            //     views: {
            //         "viewHeader": {
            //             template: "<header-admin></header-admin>"
            //         },
            //         "viewB": {
            //             templateUrl: 'components/admin/edit-register.template.html',
            //             controller: EditController,
            //             controllerAs: '$ctrl'
            //         }
            //     }
            // })
            // begin login
            .state('login', {
                url: '/login',
                views: {
                    "viewB1": {
                        templateUrl: 'components/login/login.template.html',
                        controller: LoginController,
                        controllerAs: '$ctrl'
                    }
                }
            })


            .state('detail', {
                url: '/product/:id',
                reloadOnSearch: false,
                views: {
                    "viewB1": {
                        templateUrl: 'components/detail-product/detail-product.template.html',
                        controller: ProductDetailController,
                        controllerAs: '$ctrl'
                            }

                        }

                    })
            }
    ]);