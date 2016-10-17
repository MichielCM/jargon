var FoliaViewer = function(xml) {
	var xmlDoc = ($.isXMLDoc(xml) ? xml : $.parseXML(xml))
	console.log(xmlDoc);
	
	return {
		view: function() {
			var foliaView = $("<div />", { "class" : "foliaViewer" }).append(
				$.map($(xmlDoc).find("text > p"), function(p) {
					//paragraph
					return $("<div />", { "class" : "p" }).append(
						$("<span />", { "class" : "token" }).text(
							$(p).children("t").text()
						)
					).append(
						$.map($(p).children("s"), function(s) {
							//sentence
							return $("<div />", { "class" : "s" }).append(
								$("<span />").text("Dependency Tree")
							).append(
								$("<span />").text("Lemma")
							).append(
								$("<span />").text("Morphemes")
							).append(
								$("<span />").text("Part of Speech")
							).append(
								$("<span />").text("Sense")
							).append(
								$.map($(s).children("w"), function(w) {
									//word
									return $("<div />", { "class" : "w", "id" : $(w).attr("xml:id") }).append(
										$("<span />").text($(w).children("t").text()).addClass(
											($(w).find("sense[class='keyword-extraction'] > feat[class='keyword']").length > 0 ? "keyword" : null)
										).addClass(
											($(w).find("sense[class='polarity'] > feat[class='negative']").length > 0 ? "negative" : null)
										)
									).append(
										//lemma
										$("<span />").text(
											$(w).children("lemma").attr("class")
										)
									).append(
										//morphemes
										$("<span />").text(
											$.map($(w).children("morphology").children("morpheme").children("t"), function(t) {
												return "[".concat($(t).text()).concat("]");
											}).join("")
										)
									).append(
										//PoS
										$("<span />").text(
											$(w).children("pos").attr("head")
										).attr("title", $.map($(w).children("pos").children("feat"), function(feat) {
												if ($(feat))
													if ($(feat).attr("subset"))
														if ($(feat).attr("class"))
															return $(feat).attr("subset").concat(": ").concat($(feat).attr("class"));
										}).join("\n"))
									).append(
										//sense
										$("<span />", { "class" : "custom" }).text(
											//$.map($(s).find("dependencies > dependency > feat[subset='relation'][class='attribute']").closest("dependency").find("hd > wref"), function(wref) {
											$.map($(s).find("dependencies > dependency > feat[subset='relation'][class='attribute']").closest("dependency").find("feat[subset='inheritedBy']"), function(feat) {
												//if ($(wref).attr("id") === $(w).attr("xml:id")) {
												if ($(feat).attr("class") === $(w).attr("xml:id")) {
													return $(feat).closest("dependency").find("dep > wref").attr("t");
												}
											}).join(", ")
										)
										/*$("<span />", { "class" : "custom" }).text(
											$.map($(w).find("sense > feat"), function(feat) {
												return $(feat).attr("subset").concat(": ").concat($(feat).attr("class"));
											}).join(", ")
										)*/
									)
								})
							);
						})
					);
				})
			);
			
			//dependencies
			var depIDs = $.map($(xmlDoc).find("text > p > s > dependencies > dependency"), function(dependency) {
				return $(dependency).children("dep").children("wref").attr("id");
			});
			
			$.map($(xmlDoc).find("text > p > s > w"), function(w) {
				var markDependants = function(id) {
					$.map($(xmlDoc).find("text > p > s > dependencies > dependency > hd > wref[id='".concat(id).concat("']")), function(wref) {
						foliaView.find(
							"[id='".concat($(wref).closest("dependency").children("dep").children("wref").attr("id")).concat("']")
						).children("span").eq(0).css(
							"padding-left",
							parseInt(
								foliaView.find(
									"[id='".concat(id).concat("']")
								).children("span").eq(0).css("padding-left").replace(/\D+/, "")
							) + 15 + "px"
						).attr(
							"data-dependency-class", $(wref).closest("dependency").attr("class")
						);
						markDependants($(wref).closest("dependency").children("dep").children("wref").attr("id"));
					});
				}
				
				if ($.inArray($(w).attr("xml:id"), depIDs) < 0) {
					foliaView.find(
						"[id='".concat($(w).attr("xml:id")).concat("']")
					).children("span").eq(0).css(
						"padding-left", "5px"
					).attr(
						"data-dependency-class", "ROOT"
					);
					
					markDependants($(w).attr("xml:id"));
				}
			});
			
			return foliaView;
		}
	}
}