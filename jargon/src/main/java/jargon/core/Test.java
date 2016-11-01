package jargon.core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import jargon.utils.CosineSimilarity;
import jargon.utils.servlet.Servlet;

/**
 * Servlet implementation class Test
 */
@SuppressWarnings("serial")
public class Test extends Servlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		Console.log("called");
		
		Console.log(super.parameterize().get("param").getContent());
		
		Console.log(CosineSimilarity.calculate("type 1 diabetes mellitus", "diabetes mellitus type 1"));
		Console.log(CosineSimilarity.calculate("type 2 diabletes mellitus", "diabetes mellitus type 1"));
		Console.log(CosineSimilarity.calculate("diabetes mellitus", "diabetes mellitus"));
		Console.log(CosineSimilarity.calculate("type 1 diabetes mellitus", "diabetes mellitus type 2"));
		Console.log(CosineSimilarity.calculate("type 1 diabetes mellitus", "type 2 diabetes mellitus"));
		Console.log(CosineSimilarity.calculate("varice", "varices"));
		
		Console.log(StringUtils.getJaroWinklerDistance("varice", "varices"));
		
		//super.reply("bla");
		//super.reply("bla", "XML");
		
		
	}
	
}
