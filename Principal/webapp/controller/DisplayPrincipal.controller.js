sap.ui.define([
	"sap/m/Button",
	"sap/m/Link",
	"sap/m/Dialog",
	"sap/m/MessageBox",
	"sap/m/Text",
	"sap/ui/core/mvc/Controller"
], function (Button, Link, Dialog, MessageBox, Text, Controller) {
	"use strict";

	return Controller.extend("isv.Principal.controller.DisplayPrincipal", {
		onInit: function () {

		},
		onGetPrincipal: function (oEvent) {

			var oModel = new sap.ui.model.json.JSONModel();
			var data = oModel.loadData("/isvPrincipal-1.0.0/srv_api/getPrincipal");
			
			oModel.attachRequestCompleted(function(data) {
				var result = "Email:" + oModel.getData()[0].Email + " Scopes:" + oModel.getData()[0].GrantedScopes;
				console.log("========>>> DATA: "+result);
				MessageBox.show(result, null);
			});
		}		
	});
});