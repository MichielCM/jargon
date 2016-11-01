package jargon.core;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jargon.core.BasicServlet;
import jargon.core.Pipeline.Segments;
import jargon.model.Source;
import jargon.utils.JAXBUtils;
import jargon.utils.upload.File.FileType;
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
			.upload(request, true);
		
		/*for (Upload up : uploader.getAll()) {
			System.out.println(up.getType().toString());
			System.out.println(up.getName());
			
			if (up.getType() == UploadType.FILE) {
				System.out.println(((File)up).getMimeType());
				System.out.println(((File)up).isBinary());
			}
			
			System.out.println(up.getContent());
			System.out.println("---");
		}*/
		
		Pipeline pipeline = new Pipeline(
			new Source((uploader.get("text")).getContent().toString().trim())
		);
		
		if (uploader.get("unabbreviate") != null)
			pipeline.unabbreviate(Segments.FULL);
		
		if (uploader.get("spellcheck") != null)
			pipeline.spellcheck(Segments.FULL);
		
		pipeline.segmentize(Segments.SENTENCES);
		pipeline.clean(Segments.SENTENCES);
		
		if (uploader.get("frog") != null)
			pipeline.frog(Segments.SENTENCES);
		
		if (uploader.get("summarize") != null)
			pipeline.summarize();
		
		if (uploader.get("annotate") != null)
			if (uploader.get("annotate").getContent() != null)
				if (!((String)uploader.get("annotate").getContent()).isEmpty())
					pipeline.annotate((String)uploader.get("annotate").getContent());
		
		//Console.log(JAXBUtils.toXML(pipeline.getSource().folia[0]));
		
		super.reply(
			Arrays.asList(
				pipeline.getSource().folia
			).stream().map(
				(folia) -> JAXBUtils.toHTML(
					folia,
					new java.io.File(
						this.getClass().getClassLoader().getResource("folia.xsl").getFile()
					)
				)
			).collect(
				Collectors.toList()
			).toArray(
				new String[pipeline.getSource().folia.length]
			)
		);
		
		/*super.reply(
			Arrays.asList(
				pipeline.getSource().folia
			).stream().map(
				(folia) -> super.toXML(folia)
			).collect(
				Collectors.toList()
			).toArray(
				new String[pipeline.getSource().folia.length]
			), "application/json"
		);*/
	}

}
