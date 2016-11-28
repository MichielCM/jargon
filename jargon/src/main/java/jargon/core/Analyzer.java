package jargon.core;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jargon.core.Pipeline.Segments;
import jargon.model.Source;
import jargon.utils.JAXBUtils;
import jargon.utils.servlet.Servlet;

@WebServlet(urlPatterns = {"/upload","/unabbreviate","/spellcheck","/clean","/frog","/summarize","/map","/download"})
public class Analyzer extends Servlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		Pipeline pipeline = ((Pipeline)request.getSession().getAttribute("pipeline"));
		
		switch(request.getRequestURI()) {
			case "/jargon/upload":
				request.getSession().setAttribute("pipeline", new Pipeline(new Source(super.parameterize().get("input").getContent().toString())));
				break;
			case "/jargon/unabbreviate":
				pipeline.unabbreviate(Segments.FULL);
				break;
			case "/jargon/spellcheck":
				pipeline.spellcheck(Segments.FULL, super.parameterize().get("resources").getContent().toString().split(","));
				break;
			case "/jargon/clean":
				pipeline.segmentize(Segments.LINES).clean(Segments.LINES);
				break;
			case "/jargon/frog":
				pipeline.frog(Segments.LINES);
				break;
			case "/jargon/summarize":
				pipeline.summarize();
				break;
			case "/jargon/map":
				Arrays.asList(
					super.parameterize().get("resources").getContent().toString().split(",")
				).stream().forEach(
					(resource) -> pipeline.map(resource)
				);
				break;
			case "/jargon/download":
				super.reply(
					pipeline.getSource().folia,
					new File(
						this.getClass().getClassLoader().getResource("jargon-folia.xsl").getFile()
					)
				);
		}
	}
}