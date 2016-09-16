var FoliaViewer = function(xml) {
	var xmlDoc = ($.isXMLDoc(xml) ? xml : $.parseXML(xml))
	console.log(xmlDoc);
	
	return {
		view: function() {
			var foliaView = $("<div />", { "class" : "foliaViewer" }).append(
				$.map($(xmlDoc).find("text > p"), function(p) {
					//paragraph
					return $("<div />", { "class" : "p" }).text(
						$(p).children("t").text()
					).append(
						$.map($(p).children("s"), function(s) {
							//sentence
							return $("<div />", { "class" : "s" }).text(
								$(s).children("t").text()
							).append(
								$.map($(s).children("w"), function(w) {
									//word
									return $("<div />", { "class" : "w", "id" : $(w).attr("id") }).append(
										$("<span />").text($(w).children("t").text())
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
				
				if ($.inArray($(w).attr("id"), depIDs) < 0) {
					foliaView.find(
						"[id='".concat($(w).attr("id")).concat("']")
					).children("span").eq(0).css(
						"padding-left", "5px"
					).attr(
						"data-dependency-class", "ROOT"
					);
					
					markDependants($(w).attr("id"));
				}
			});
			
			return foliaView;
		}
	}
}