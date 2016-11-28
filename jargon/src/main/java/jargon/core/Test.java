package jargon.core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharSequenceNodeFactory;
import com.googlecode.concurrenttrees.solver.LCSubstringSolver;

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
		
		Object o = (141 * Math.pow(Math.min(40/0.9,1),-0.411) * Math.pow(Math.max(40/0.9,1),-1.209) * Math.pow(0.993,88) * 1.159);
		Console.log(o.getClass().getName());
		
		//Console.log(super.parameterize().get("param").getContent());
		
		/*Console.log(StringUtils.getLevenshteinDistance("varice", "varices"));
		Console.log(StringUtils.getJaroWinklerDistance("varice", "varices"));
		Console.log(CosineSimilarity.calculate("varice", "varices"));*/
		
		/*Console.log(CosineSimilarity.calculate("kakstoel", "koelkast"));
		Console.log(StringUtils.getJaroWinklerDistance("kakstoel", "koelkast"));*/
		
		/*Console.log(
			CosineSimilarity.calculate("diabetes mellitus type 1", "diabetes mellitus type 1", "\\W+"),
			CosineSimilarity.calculate("diabetes mellitus type 1", "diabetes mellitus type 1", "(?!^)"),
			StringUtils.getJaroWinklerDistance("diabetes mellitus type 1", "diabetes mellitus type 1")
		);
		
		Console.log(
			CosineSimilarity.calculate("diabetes mellitus type 2", "diabetes mellitus type 1", "\\W+"),
			CosineSimilarity.calculate("diabetes mellitus type 2", "diabetes mellitus type 1", "(?!^)"),
			StringUtils.getJaroWinklerDistance("diabetes mellitus type 2", "diabetes mellitus type 1")
		);
		
		Console.log(
			CosineSimilarity.calculate("type 1 diabetes mellitus", "diabetes mellitus type 1", "\\W+"),
			CosineSimilarity.calculate("type 1 diabetes mellitus", "diabetes mellitus type 1", "(?!^)"),
			StringUtils.getJaroWinklerDistance("type 1 diabetes mellitus", "diabetes mellitus type 1")
		);
		
		Console.log(
			CosineSimilarity.calculate("diabetes mellitus", "diabetes mellitus type 1", "\\W+"),
			CosineSimilarity.calculate("diabetes mellitus", "diabetes mellitus type 1", "(?!^)"),
			StringUtils.getJaroWinklerDistance("diabetes mellitus", "diabetes mellitus type 1")
		);
		
		LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
	    solver.add("diabetes mellitus type 1");
	    solver.add("type 1 diabetes mellitus");
	    Console.log(solver.getLongestCommonSubstring().toString());
	    
	    solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
	    solver.add("diabetes mellitus in de familie");
	    solver.add("diabetes mellitus");
	    Console.log(solver.getLongestCommonSubstring().toString());
	    
	    solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
	    solver.add("diabetes mellitus in de familie");
	    solver.add("diarree");
	    Console.log(solver.getLongestCommonSubstring().toString());*/
		
		/*Console.log(CosineSimilarity.calculate("diabetes mellitus type 1", "diabetes mellitus type 1"));
		Console.log(CosineSimilarity.calculate("diabetes mellitus", "diabetes mellitus type 1"));
		
		Console.log(StringUtils.getJaroWinklerDistance("type 1 diabetes mellitus", "diabetes mellitus type 1"));
		Console.log(StringUtils.getJaroWinklerDistance("diabetes mellitus type 1", "diabetes mellitus type 1"));
		Console.log(StringUtils.getJaroWinklerDistance("diabetes mellitus", "diabetes mellitus type 1"));*/
		
		/*Console.log(StringUtils.getLevenshteinDistance("al jaren diabetes mellitus", "diabetes mellitus"));
		Console.log(StringUtils.getLevenshteinDistance("jaren diabetes mellitus", "diabetes mellitus"));
		Console.log(StringUtils.getLevenshteinDistance("diabetes mellitus jaren", "diabetes mellitus"));
		Console.log(StringUtils.getLevenshteinDistance("diabetes mellitus jaren al", "diabetes mellitus"));*/
		
		//super.reply("bla");
		//super.reply("bla", "XML");
		
		
	}
	
}
