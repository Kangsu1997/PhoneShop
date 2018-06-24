// function onLoadFunction() {
//     gapi.client.setApiKey('AIzaSyA3-VKPlMzOesqVb7pvXa6pmbTz1nTJ2Fs');
//     gapi.client.load('plus', 'v1', function () {
//     });
// }
//
// window.fbAsyncInit = function () {
//     FB.init({
//         appId: '658447091212471',
//         autoLogAppEvents: true,
//         xfbml: true,
//         version: 'v2.11',
//         status: true
//     });
//
//     FB.getLoginStatus(function (response) {
//         if (response.status === 'connected') {
//             // we are connected
//
//             console.log(response.authResponse);
//         }
//         else if (response.status === 'not_authorized') {
//             // not auth
//         }
//         else {
//             // we are not logged in to facebook
//         }
//     });
//
// };
//
// (function () {
//     var p = document.createElement('script');
//     p.type = 'text/javascript';
//     p.async = true;
//     p.src = 'https://apis.google.com/js/client.js?onload=onLoadFunction';
//     var s = document.getElementsByTagName('script')[0];
//     s.parentNode.insertBefore(p, s);
// })();
// (function (d, s, id) {
//     var js, fjs = d.getElementsByTagName(s)[0];
//     if (d.getElementById(id)) {
//         return;
//     }
//     js = d.createElement(s);
//     js.id = id;
//     js.src = "https://connect.facebook.net/en_US/sdk.js";
//     fjs.parentNode.insertBefore(js, fjs);
// }(document, 'script', 'facebook-jssdk'));