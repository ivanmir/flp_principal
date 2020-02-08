/*global QUnit*/

sap.ui.define([
	"isv/Principal/controller/DisplayPrincipal.controller"
], function (Controller) {
	"use strict";

	QUnit.module("DisplayPrincipal Controller");

	QUnit.test("I should test the DisplayPrincipal controller", function (assert) {
		var oAppController = new Controller();
		oAppController.onInit();
		assert.ok(oAppController);
	});

});