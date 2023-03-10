angular.module('app').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/happy';

    $scope.showMyOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.MyOrders = response.data;
        });
    };

    $scope.showMyOrders();
});