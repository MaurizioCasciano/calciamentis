function specialSearch() {
			//categoria
			var cat = document.getElementById("dropdown").value;
			var key = document.getElementById("search-box").value;
			var mainSection = document.getElementById("main-section");

			$.ajax({
				type : "GET",
				data : {
					cat : cat,
					key : key
				},
				url : "CatalogPage",
				success : function(data) {

					mainSection.innerHTML = data
				}
			});
		}