/* global QUnit */
QUnit.config.autostart = false;

sap.ui.getCore().attachInit(function () {
	"use strict";

	sap.ui.require([
		"isv/Principal/test/integration/AllJourneys"
	], function () {
		QUnit.start();
	});
});