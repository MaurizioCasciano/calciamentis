function loadXMLDoc(xmlpath, id) {
	var xmlhttp;

	if (window.XMLHttpRequest) {
		// code for modern browsers
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.onreadystatechange = function() {
		//alert("ReadyState: " + xmlhttp.readyState + " Status: " + xmlhttp.status);

		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if (id === undefined) {
				// alert("Building catalog page");
				buildCatalogPage(xmlhttp);
				//alert("DONE");
			} else {
				//alert("Building detail page");
				buildDetailPage(xmlhttp, id);
				//alert("DONE");
			}
		}
	};

	xmlhttp.open("GET", xmlpath, true);
	xmlhttp.send();
}

/**
 * Costruisce la pagina HTML del catalogo dei prodotti tramite il contenuto presente nel file XML.
 * @param xml Il file XML da cui estrarre le informazioni.
 */
function buildCatalogPage(xml) {
	var xmlDoc = xml.responseXML;
	var shoes = xmlDoc.getElementsByTagName("shoe");
	var mainSection = document.getElementById("main-section");

	for (var i = 0; i < shoes.length; i++) {
		var brand = shoes[i].getElementsByTagName("brand")[0].childNodes[0].nodeValue;
		var model = shoes[i].getElementsByTagName("model")[0].childNodes[0].nodeValue;
		var price = shoes[i].getElementsByTagName("price")[0].childNodes[0].nodeValue;
		var myimage = shoes[i].getElementsByTagName("image")[0].childNodes[0].nodeValue;
		var alternative = shoes[i].getElementsByTagName("alt")[0].childNodes[0].nodeValue;
		var description = shoes[i].getElementsByTagName("description")[0].childNodes[0].nodeValue;
		var target = shoes[i].getElementsByTagName("target")[0].childNodes[0].nodeValue;

		var article = document.createElement("ARTICLE");
		var img = document.createElement("IMG");
		img.setAttribute("src", myimage);
		img.setAttribute("alt", alternative);
		article.appendChild(img);

		var myanchor = document.createElement("A");
		myanchor.setAttribute("href", target);
		var title = document.createElement("H3");
		var text = document.createTextNode(brand + " " + model);
		title.appendChild(text);
		myanchor.appendChild(title);
		article.appendChild(myanchor);

		var paragraph = document.createElement("P");
		var text2 = document.createTextNode(description);
		paragraph.appendChild(text2);

		article.appendChild(paragraph);
		mainSection.appendChild(article);
	}
}

function buildDetailPage(xml, id) {
	//alert("Building START");
	//alert("ID: " + id);
	
	var xmlDoc = xml.responseXML;
	var myShoe = xmlDoc.getElementById(id);
	//alert("MY_SHOW: " + myShoe); /*getElementById returns always null in IE and in Eclipse Web Browser*/
	
	var brand = myShoe.getElementsByTagName("brand")[0].childNodes[0].nodeValue;
	var model = myShoe.getElementsByTagName("model")[0].childNodes[0].nodeValue;
	var price = myShoe.getElementsByTagName("price")[0].childNodes[0].nodeValue;
	var images = myShoe.getElementsByTagName("image");
	var alternative = myShoe.getElementsByTagName("alt")[0].childNodes[0].nodeValue;
	var description = myShoe.getElementsByTagName("description")[0].childNodes[0].nodeValue;
	var target = myShoe.getElementsByTagName("target")[0].childNodes[0].nodeValue;
	
	var mainSection = document.getElementById("main-section");
	var title = document.createElement("H2");
	var text = document.createTextNode(brand + " " + model);
	title.appendChild(text);
	mainSection.appendChild(title);

	var imageViewer = document.createElement("DIV");
	imageViewer.setAttribute("id", "image-viewer");

	var mainImage = document.createElement("IMG");
	mainImage.setAttribute("id", "main-img");
	mainImage.setAttribute("alt", alternative);
	mainImage.setAttribute("src", images[0].childNodes[0].nodeValue);

	var thumbnails = document.createElement("DIV");
	thumbnails.setAttribute("id", "thumbnails");

	for (var i = 0; i < images.length; i++) {
		var currentImage = document.createElement("IMG");
		currentImage.setAttribute("alt", alternative);
		currentImage.setAttribute("src", images[i].childNodes[0].nodeValue);
		currentImage.setAttribute("onclick", "mouseClick()");
		currentImage.setAttribute("onmouseenter", "mouseEnter()");
		currentImage.setAttribute("onmouseover", "mouseOver(this)");
		currentImage.setAttribute("onmouseout", "mouseOut()");
		thumbnails.appendChild(currentImage);
	}

	imageViewer.appendChild(mainImage);
	imageViewer.appendChild(thumbnails);
	mainSection.appendChild(imageViewer);
	
	/*WORKING UNTIL HERE*/
	
	var details = myShoe.getElementsByTagName("detail");
	//alert("Details.length: " + details.length);

	for (var j = 0; j < details.length; j++) {
		//alert("J value: " + j);
		
		var currentDetail = details[j];
		//alert("CURRENT_DETAIL: " + currentDetail);
		
		var currentHeader = currentDetail.getElementsByTagName("header")[0];
		//alert("CURRENT_HEADER: " + currentHeader.childNodes[0].nodeValue);
		
		var currentParagraph = currentDetail.getElementsByTagName("paragraph")[0];
		//alert("CURRENT_PARAGRAPH: " + currentParagraph.childNodes[0].nodeValue);
		
		var section = document.createElement("SECTION");
		var header = document.createElement("H3");
		header.innerHTML = currentHeader.childNodes[0].nodeValue;
		section.appendChild(header);

		var paragraph = document.createElement("P");
		paragraph.innerHTML = currentParagraph.childNodes[0].nodeValue;
		section.appendChild(paragraph);

		mainSection.appendChild(section);
	}

	var buyDiv = document.createElement("DIV");
	buyDiv.setAttribute("id", "buy-div");

	var anchor = document.createElement("A");
	anchor.setAttribute("id", "acquista");
	anchor.setAttribute("href", "carrello.jsp");
	anchor.innerHTML = "Acquista";

	buyDiv.appendChild(anchor);
	mainSection.appendChild(buyDiv);
	
	//alert("Building END");
}