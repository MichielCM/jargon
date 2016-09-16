package jargon.core;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jargon.core.BasicServlet;
import jargon.core.Pipeline.Segments;
import jargon.model.Source;
import jargon.model.folia.Feature;
import jargon.model.folia.Folia;
import jargon.model.folia.Sense;
import jargon.utils.upload.BinaryFile;
import jargon.utils.upload.File;
import jargon.utils.upload.File.FileType;
import jargon.utils.upload.TextFile;
import jargon.utils.upload.Upload;
import jargon.utils.upload.Upload.UploadType;
import jargon.utils.upload.Uploader;

/**
 * Servlet implementation class Processor
 */
@WebServlet("/process/natural/language")
public class Processor extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Processor() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		Uploader uploader = new Uploader()
			.registerMimeTypes(FileType.TEXT, "application/json", "application/csv", "application/xml")
			.upload(request);
		
		for (Upload up : uploader.getAll()) {
			System.out.println(up.getType().toString());
			System.out.println(up.getName());
			
			if (up.getType() == UploadType.FILE) {
				System.out.println(((File)up).getMimeType());
				System.out.println(((File)up).isBinary());
			}
			
			System.out.println(up.getContent());
			System.out.println("---");
		}
		
		Pipeline pipeline = new Pipeline(
			new Source((uploader.get("text")).getContent().toString())
		);
		
		if (uploader.get("unabbreviate") != null)
			pipeline.unabbreviate(Segments.FULL);
		
		if (uploader.get("spellcheck") != null)
			pipeline.spellcheck(Segments.FULL);
		
		pipeline.segmentize(Segments.SENTENCES);
		
		if (uploader.get("frog") != null)
			pipeline.frog(Segments.SENTENCES);
		
		super.replyInXML(pipeline.getSource().folia);
		
		/*System.out.println(uploader.get("foliafile").getContent());*/
		
		/*Folia folia = (Folia) new XmlMapper().readValue((String)uploader.get("foliafile").getContent(), Folia.class);
		
		new RuleEngine(
			ResourceType.XLS, 
			Arrays.asList(
				uploader.getAll("rulesfile")).stream().map(
					(file) -> ArrayUtils.toObject(((BinaryFile)file).getContent())
				).collect(Collectors.toList()
			).toArray(new Byte[uploader.getAll("rulesfile").length][])
		).set(
			"meddraMatcher", new FuzzyMatcher(
				new CSVResource(new java.io.File("D:\\Michiel\\Sync\\ELAN\\nlp\\meddra_llt.csv"), System.getProperty("line.separator"), ",")
			)
		).add(
			folia.getWords()
		).add(
			folia.getDependencies()
		).execute();
		
		super.replyInXML(folia);*/
		
		/*System.out.println(new FuzzyMatcher().matches("general", "generalized", ALGORITHM.JAROWINKLER, 0.9));
		
		FileResource fr = new FileResource(new java.io.File("C:\\Users\\mcmeulendijk\\Desktop\\llt.txt"));
		System.out.println(fr.read().length);
		System.out.println(fr.read()[0]);
				
		FuzzyMatcher fzr = new FuzzyMatcher(
			fr
		);
		
		FuzzyMatch[] fzs = fzr.match("buikkramp", ALGORITHM.JAROWINKLER, 0.9);
		
		for (FuzzyMatch fz : fzs) {
			System.out.println(fz.getOriginal());
			System.out.println(fz.getComparator());
			System.out.println(fz.getSimilarity());
		}
		
		System.out.println(fzr.matchBest("buikkramp", ALGORITHM.JAROWINKLER, 0.9).getComparator());*/
		
		//XmlMapper mapper = new XmlMapper();
		//dq.nlp.model.OpenCredentials openCredentials = mapper.readValue((String)uploader.get("testtext").getContent(), dq.nlp.model.OpenCredentials.class);
		//System.out.println(openCredentials.credentials[0].user);
		
		
		/*System.out.println(
		new Pipeline(
			new Source((String)uploader.get("text").getContent())
		).tokenize().length);*/
	}

}
