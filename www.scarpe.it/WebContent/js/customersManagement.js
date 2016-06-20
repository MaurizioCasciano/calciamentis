$(document).ready(
		function() {
			var residenceAddress = $(".indirizzoResidenza");
			var deliveryAddress = $(".indirizzoSpedizione");
			var purchases = $(".acquisti");

			/*alert("residenceAddress" + residenceAddress + "size: "
					+ residenceAddress.length);*/

			$("#tabellaClienti tr.rigaDati").addClass("master");
			$("#tabellaClienti tr:not(.master)").hide();
			$("#tabellaClienti tr.purchaseRow").show();
			$("#tabellaClienti tr:first-child").show(); // riga intestazioni

			$("#tabellaClienti tr.master td.link1").click(
					function() {
						$(this).parents("tr.master").next("tr.dettagli1")
								.toggle();

						$(this).find(".indirizzoResidenza").toggleClass(
								"fa fa-plus-square");
						$(this).find(".indirizzoResidenza").toggleClass(
								"fa fa-minus-square");
					});

			$("#tabellaClienti tr.master td.link2").click(
					function() {
						$(this).parents("tr.master").next("tr.dettagli1").next(
								"tr.dettagli2").toggle();
						console.log($(this).parents("tr.master").siblings(
								"tr.dettagli2").first());// next("tr.dettagli2"));
						$(this).find(".indirizzoSpedizione").toggleClass(
								"fa fa-plus-square");
						$(this).find(".indirizzoSpedizione").toggleClass(
								"fa fa-minus-square");
					});

			$("#tabellaClienti tr.master td.link3").click(
					function() {
						$(this).parents("tr.master").next("tr.dettagli1").next(
								"tr.dettagli2").next("tr.dettagli3").toggle();

						$(this).find(".acquisti").toggleClass(
								"fa fa-plus-square");
						$(this).find(".acquisti").toggleClass(
								"fa fa-minus-square");
					});

			for (var i = 0; i < residenceAddress.length; i++) {
				// alert("For per residenceAddress: " + i);
				$(residenceAddress[i]).click(function() {
					/*
					 * alert("this: " + this); alert("parents: " +
					 * $(this).parents("tr")); alert("sibling: " +
					 * $(this).parents("tr").siblings(".customersDetail")); var
					 * detail =
					 * $(this).parents("tr").siblings(".customersDetail").first();
					 * alert("detail" + detail);
					 * detail.css({"display":"table-row"}); detail.class("fa
					 * fa-minus-square");
					 * $(residenceAddress[i]).html("Nascondi");
					 */

				}); // end click function
			}// end for

			for (var j = 0; j < deliveryAddress.length; j++) {
				$(deliveryAddress[j]).click(function() {

				});
			} // end for

			for (var z = 0; z < purchases.length; z++) {
				$(purchases[z]).click(function() {

				});
			} // end for
		});