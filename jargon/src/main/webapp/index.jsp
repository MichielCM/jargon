<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JARGON</title>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<script type="text/javascript">
$(document).ready(function() {
	$("#toggle").on("click", function() {
		if ($("#search-area, #options-area").css("display") === "none") {
			$("#search-area, #options-area").css("display", "block");
			$("body > header").css("background", "#b7e1fc");
		} else {
			$("#search-area, #options-area").css("display", "none");
			$("body > header").css("background", "transparent");
		}
	});
	
	$("#search-area button.ok").on("click", function() {
		var call = function(vNav, vPost, vUrl, vDataType, vData) {
			$.when(
				$.ajax({
					url: vUrl,
					method: (vPost ? "POST" : "GET"),
					dataType: vDataType,
					accepts: {
						"text": "text/plain; charset=UTF-8",
						"html": "text/html; charset=UTF-8",
						"xml": "text/xml; charset=UTF-8"
					},
					async: true,
					processData: true, //false multipart?
					contentType: false,
					data: vData
				})
			).done(function(data) {
				if (data !== "") {
					$("main > section.contents").empty().append(data);
					$("#toggle").click();
				} else {
					if (vNav) {
						if (vNav.nextAll("nav.option").children("input:checked").closest("nav.option").eq(0).length > 0) {
							vNav = vNav.nextAll("nav.option").children("input:checked").closest("nav.option").eq(0);
							call(vNav, false, vNav.children("input").attr("data-url"), "text", {
								"resources" : vNav.children("input").nextAll("fieldset").children("input:checked").map(function(i,o) {
									return $(o).attr("id").substring($(o).attr("id").indexOf("-") + 1);
								}).get().join(",")
							});
						} else {
							call(vNav, false, "download", "html");
						}
					} else {
						vNav = $("#options-area nav.option > input:checked").eq(0).closest("nav.option");
						call(vNav, false, vNav.children("input").attr("data-url"), "text", {
							"resources" : vNav.children("input").nextAll("fieldset").children("input:checked").map(function(i,o) {
								return $(o).attr("id").substring($(o).attr("id").indexOf("-") + 1);
							}).get().join(",")
						});
					}
					
					$("main > section.contents > progress").attr(
						"value", parseInt($("main > section.contents > progress").attr("value")) + 1
					)
				}
			});
		}
		
		$("main > section.contents").empty().append(
			$("<progress />").attr("max", $("#unabbreviation:checked,#spellcheck:checked,#summarization:checked,#map:checked").length + 3).attr("value","0")
		)
		
		call(null, false, "upload", "text",
			{ "input" : $("#search-area textarea.input").val() }
		);
	})
	
	$("#download-folia").on("click", function() {
		$.ajax({
			url: "download",
			method: "GET",
			dataType: "xml",
			accepts: {
				"text": "text/plain; charset=UTF-8",
				"html": "text/html; charset=UTF-8",
				"xml": "text/xml; charset=UTF-8"
			},
			async: true,
			processData: true,
			contentType: false
		})
	});
	
	$("#toggle").click();
});
</script>

</head>
<body>

<header>
	<nav id="logo-area">
		<section class="contents">
			<span>&equiv;</span>
			<span id="toggle">&#x21D5;</span>
			<span>JARGON</span>
		</section>
	</nav>
	
	<nav id="search-area">
		<section class="contents">
			<textarea class="input">diabetes mellitus in de familie. geen klachten.
			varices in de familie.
			geen grote problemen met stoelgang; wel blaren.
			familieleden maken zich zorgen
			hartstilstand in 99. sindsdien hart en vaatziekten.</textarea>
			<label class="file">
				<input type="file" accept=".txt, .csv" />Tekstbestand
			</label>
			<button class="ok">OK</button>
		</section>
	</nav>
	
	<nav id="options-area">
		<section class="contents">
			<nav class="option">
				<input type="checkbox" id="unabbreviation" data-url="unabbreviate" checked="checked" />
				<label for="unabbreviation">Schrijf afkortingen voluit</label>
				<fieldset id="unabbreviation">
					<input type="checkbox" id="unabbreviation-general" name="general" checked="checked" />
					<label for="unabbreviation-general">Algemeen Nederlands</label>
					<input type="checkbox" id="unabbreviation-medical" name="medical" />
					<label for="unabbreviation-medical">Medisch</label>
					<input type="file" id="unabbreviation-misc" accept=".csv" title="Afkortingen in TAB-gescheiden formaat, eventueel met REGEX" />
					<label for="unabbreviation-misc">Overig(e) bestand(en)</label>
				</fieldset>
			</nav>
			
			<nav class="option">
				<input type="checkbox" id="spellcheck" data-url="spellcheck" />
				<label for="spellcheck">Voer spellingscontrole uit</label>
				<fieldset id="spellcheck">
					<input type="checkbox" id="spellcheck-dutch" name="dutch" />
					<label for="spellcheck-dutch">Algemeen Nederlands</label>
					<input type="checkbox" id="spellcheck-meddra" name="meddra" />
					<label for="spellcheck-meddra">MedDRA</label>
					<input type="file" id="spellcheck-misc" accept=".txt, .csv" title="Woordenlijsten met correcte termen, 1 regel per term" />
					<label for="spellcheck-misc">Overig(e) bestand(en)</label>
				</fieldset>
			</nav>
			
			<nav class="option required">
				<input type="checkbox" id="clean" data-url="clean" checked="checked" />
				<label for="clean"></label>
			</nav>
			
			<nav class="option required">
				<input type="checkbox" id="frog" data-url="frog" checked="checked" />
				<label for="frog"></label>
			</nav>
			
			<nav class="option">
				<input type="checkbox" id="summarization" data-url="summarize" checked="checked" />
				<label for="summarization">Vat samen</label>
				<fieldset id="summarization">
					<input type="checkbox" id="summarization-keywords" name="keyword-extraction" checked="checked" />
					<label for="summarization-keywords">Kernwoorden</label>
					<input type="checkbox" id="summarization-polarity" name="polarity" checked="checked" />
					<label for="summarization-polarity">Polariteit</label>
					<input type="checkbox" id="summarization-frequency" name="frequency" />
					<label for="summarization-frequency">Frequentie</label>
					<input type="checkbox" id="summarization-occurrence" name="occurrence" />
					<label for="summarization-occurrence">Gebeurtenis</label>
					<input type="checkbox" id="summarization-time-of-day" name="time-of-day" />
					<label for="summarization-time-of-day">Tijd van de dag</label>
				</fieldset>
			</nav>
			
			<nav class="option">
				<input type="checkbox" id="map" data-url="map" checked="checked" />
				<label for="map">Koppel aan ontologie</label>
				<fieldset id="map">
					<input type="checkbox" id="map-meddra" name="meddra" checked="checked" />
					<label for="map-meddra">MedDRA</label>
					<input type="checkbox" id="map-icpc" name="icpc" />
					<label for="map-icpc">ICPC</label>
					<input type="checkbox" id="map-icd10" name="icd10" />
					<label for="map-icd10">ICD-10</label>
				</fieldset>
			</nav>
		</section>
	</nav>
</header>

<main>
	<section class="contents" /></section>
</main>

<aside>
	<fieldset>
		<legend>Download als:</legend>
		<button id="download-folia">FoLiA</button>
		<button id="download-csv">CSV</button>
	</fieldset>
</aside>

<footer>
	<section class="contents">
		JARGON is ontwikkeld door Michiel Meulendijk in het LUMC.
	</section>
</footer>

</body>
</html>