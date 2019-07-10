/**
 * @cjhme-admin v1.0.1
 * Copyright 2016 cjh. All Rights Reserved
 */

var cjhme = angular.module('cjhme', [
	'ui.bootstrap',
	'ui.router',
	'oc.lazyLoad',
	'ui.switch',
	'ui.select',
	'chart.js',
	'ngFileUpload',
	'ng.ueditor',
	'ui.cjhme'
]);

//主控制器
cjhme.controller('cjhme.indexCtrl', ['$scope', '$state', 'cjhmeModal', 'cjhme.cjhmeSvc', function($scope, $state, cjhmeModal, cjhmeSvc) {
	//设置标题
	$scope.$on('pageTitle', function(event, data) {
		$scope.pageTitle = data;
	});

	$scope.theme = "default-theme";
	$scope.menuObj = [];

	//初始化
	var init = function() {
		//初始化加载模态框
		cjhmeSvc.initLoadModal();

		//初始化主题

		//初始化菜单
		$scope.menuObj = cjhmeSvc.findUserMenus();

	}

	//切换左边菜单显示/隐藏
	$scope.closeWrapper = false;
	$scope.toggleLeft = function() {
		$scope.closeWrapper = !$scope.closeWrapper;
	}

	//跳转主页
	$scope.toMainPage = function() {
		$state.go('main');
	}

	//跳转页面
	$scope.toPage = function(node) {
		if(node.data && node.url) {
			$state.go(node.url, node.data);
		} else if(node.url) {
			$state.go(node.url);
		}
	}

	$scope.exitSys = function() {
		cjhmeModal.confirm({
			title: '请确认',
			content: '您确定退出系统吗？',
			ok: function() {
				//后台处理完后进行页面跳转
				window.location.href = "login.html";
			},
			cancel: function() {
				
			}
		});
	}

	//选择主题
	$scope.selectTheme = function(val) {
		$scope.theme = val;
	}

	init();

}]);

/**
 * 服务
 */
cjhme.factory('cjhme.cjhmeSvc', [function() {
	var uri = {

	};

	var svc = {};

	//初始化加载模态框
	svc.initLoadModal = function() {
		var loadModalHtml = "<div class='c-load-modal-bg'>";
		loadModalHtml += "<span  class='c-load-modal'><i class='fa fa-spinner fa-spin fa-5x'></i></span>";
		loadModalHtml += "</div>";
		$("body").append(loadModalHtml);
	}

	//显示加载模态框
	svc.showLoadModal = function() {
		$(".c-load-modal-bg").css("display", "block");
		$(".c-load-modal").css("display", "block");
	}

	//关闭加载模态框
	svc.closeLoadModal = function() {
		$(".c-load-modal-bg").css('display', 'none');
		$(".c-load-modal").css("display", "none");
	}

	//获取用户菜单
	svc.findUserMenus = function() {
		var menuObj = [{
			"id": "2",
			"parentId": "1",
			"name": "权限管理",
			"icon": 'fa-user',
			"isExpend":true,
			"children": [{
				"id": "13",
				"parentId": "1",
				"name": "用户管理",
				"icon": "fa-user",
				"children": [{
					"id": "13",
					"parentId": "1",
					"name": "用户添加",
					"icon": "fa-plus-circle",
					"url": "userAdd"
				}, {
					"id": "13",
					"parentId": "1",
					"name": "用户查询",
					"icon": "fa-search",
					"url": "userList"
				}]
			}, {
				"id": "13",
				"parentId": "1",
				"name": "图书管理",
				"icon": "fa-users",
				"children": [{
					"id": "13",
					"parentId": "1",
					"name": "图书添加",
					"icon": "fa-plus-circle",
					"url": "roleAdd"
				}, {
					"id": "13",
					"parentId": "1",
					"name": "图书查询",
					"icon": "fa-search",
					"url": "roleList"
				}]
			}, {
				"id": "13",
				"parentId": "1",
				"name": "订单管理",
				"icon": "fa-tree",
				"children": [{
					"id": "13",
					"parentId": "1",
					"name": "订单确认",
					"icon": "fa-plus-circle",
					"url": "menuAdd"
				}]
			}]
		}, {
			"id": "13",
			"parentId": "1",
			"name": "系统管理",
			"icon": "fa-cube",
			"children": [{
				"id": "13",
				"parentId": "1",
				"name": "系统日志",
				"icon": "fa-file",
				"url": "systemLog"
			}]
		}]
		return menuObj;
	}

	return svc;
}]);

/**
 * 页面自适应
 */
cjhme.directive('pageResize', function($window) {
	return function(scope, element, attr) {
		var w = $window;
		scope.$watch(function() {
			return {
				'h': w.innerHeight
			};
		}, function(newValue, oldValue) {
			scope.hStyle = {
				'height': newValue.h + 'px'
			}

			scope.cStyle = {
				'height': (newValue.h - 62) + 'px'
			}
		}, true);

		angular.element($window).bind('resize', function() {
			scope.$apply();
		})
	}
});

/**
 * 配置$provide
 */
cjhme.config(["$provide", "$compileProvider", "$controllerProvider", "$filterProvider",
	function($provide, $compileProvider, $controllerProvider, $filterProvider) {
		cjhme.controller = $controllerProvider.register;
		cjhme.directive = $compileProvider.directive;
		cjhme.filter = $filterProvider.register;
		cjhme.factory = $provide.factory;
		cjhme.service = $provide.service;
		cjhme.constant = $provide.constant;
	}
]);

/**
 * 配置$ocLazyLoadProvider
 */
cjhme.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
	$ocLazyLoadProvider.config({
		debug: true
	});
}]);

/**
 * 拦截器
 */
cjhme.config(['$httpProvider',
	function($httpProvider) {
		//增加跨域信任
		$httpProvider.defaults.withCredentials = true;
		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

		var httpCommonInterceptor = ['$q', '$cacheFactory', '$timeout', '$rootScope', '$log', 'cjhme.cjhmeSvc',
			function($q, $cacheFactory, $timeout, $rootScope, $log, cjhmeSvc) {

				//判断是否后台请求方法
				var isSvcReq = function(config) {
					if(config.url && /((^.*\.jhtml$)|(^.*\.jhtml\?))/.test(config.url)) {
						return true;
					} else {
						return false;
					}
				}

				return {
					'request': function(config) {
						if(isSvcReq(config)) {
							//转圈圈
							cjhmeSvc.showLoadModal();
						}
						return config;
					},
					'response': function(response) {
						if(isSvcReq(response.config)) {
							//关圈圈
							cjhmeSvc.closeLoadModal();
						}

						return response;
					},

					'responseError': function(rejection) {
						console.info("httpLoadingInterceptor responseError");
						//关圈圈
						cjhmeSvc.closeLoadModal();
						return $q.reject(rejection);
					}
				};
			}
		];

		//http请求公共拦截器
		$httpProvider.interceptors.push(httpCommonInterceptor);
	}

]);