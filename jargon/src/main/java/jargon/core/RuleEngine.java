package jargon.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieSession;

public class RuleEngine {
	
	private KieServices knowledgeService;
	private KieFileSystem knowledgeFileSystem;
	private KieSession knowledgeSession;
	
	public enum ResourceType {
		DRL, XLS, CSV
	}
	
	/**
	 * Initiates rule engine.
	 */
	public RuleEngine() {
		this.init();
	}
	
	/**
	 * Initiates rule engine from Files containing decision rules. Plain text files containing MVEL decision
	 * rules are expected.
	 * @param type			Type of resource. Currently only DRL files are supported in this constructor.
	 * @param resources		Files containing decision rules.
	 */
	public RuleEngine(ResourceType type, File... resources) {
		this();
		
		for (File resource : resources) {
			switch (type) {
				case DRL:
					try {
						this.addRules(new String(
							Files.readAllBytes(resource.toPath())
						));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				default:
					break;
			}
		}
	}
	
	/**
	 * Initiates rule engine from Strings containing decision rules. If type is DRL plain String containing MVEL decision
	 * rules are expected. If type is CSV comma-separated strings in Drools' decision table format are expected.
	 * @param type			Type of resource. Currently only DRL and CSV are supported in this constructor.
	 * @param resources		Strings containing decision rules.
	 */
	public RuleEngine(ResourceType type, String... resources) {
		this();
		
		for (String resource : resources) {
			switch (type) {
				case CSV:
					this.addRules(getRulesFromSpreadSheet(resource));
				case DRL:
					this.addRules(resource);
				default:
					break;
			}
		}
	}
	
	/**
	 * Initiates rule engine from byte arrays containing decision rules. Excel files (.xls) in Drools' decision table format
	 * are expected.
	 * @param type			Type of resource. Currently only XLS is supported in this constructor.
	 * @param resources		Byte arrays containing decision rules.
	 */
	public RuleEngine(ResourceType type, Byte[]... resources) {
		this();
		for (Byte[] resource : resources) {
			switch (type) {
				case XLS:
					this.addRules(getRulesFromSpreadSheet(ArrayUtils.toPrimitive(resource)));
				default:
					break;
			}
		}
	}
	
	public RuleEngine(ResourceType type, byte[]... resources) {
		this();
		for (byte[] resource : resources) {
			switch (type) {
				case XLS:
					this.addRules(getRulesFromSpreadSheet(resource));
				default:
					break;
			}
		}
	}
	
	/**
	 * Sets value of global variable.
	 * @param id		id of global variable.
	 * @param global	object to which global variable will be set.
	 * @return
	 */
	public RuleEngine set(String id, Object global) {
		this.knowledgeSession.setGlobal(id, global);
		return this;
	}
	
	/**
	 * Adds object to knowledge session. Will be used to trigger rules.
	 * @param object	Object to add.
	 */
	public RuleEngine insert(Object object) {
		this.knowledgeSession.insert(object);
		return this;
	}
	
	/**
	 * Adds objects in array to knowledge session. Will be used to trigger rules.
	 * @param objects	Array of objects, which will be added one by one.
	 */
	public RuleEngine add(Object[] objects) {
		for (Object object : objects)
			this.insert(object);
		
		return this;
	}
	
	/**
	 * Retrieves objects in knowledge session, filtered by a string.
	 * @param klass		Class to which objects' class names will be matched.
	 * @return			Array of objects.
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] get(Class<T> klass) {
		Iterator<? extends Object> iFacts = this.knowledgeSession.getObjects().iterator();
		ArrayList<T> objects = new ArrayList<T>();
		
		while (iFacts.hasNext()) {
			Object oFact = iFacts.next();
			if (oFact.getClass().getName().equals(klass.getName()))
				objects.add((T)oFact);
		}
		
		return (objects.size() > 0 ? objects.toArray((T[])Array.newInstance(klass, objects.size())) : (T[])Array.newInstance(klass, 0));
	}
	
	/**
	 * Fires all loaded rules on objects in knowledge session.
	 */
	public RuleEngine execute() {
		this.knowledgeSession.fireAllRules();
		return this;
	}
	
	/**
	 * Disposes knowledge session.
	 */
	public void exit() {
		this.knowledgeSession.dispose();
	}
	
	private String getRulesFromSpreadSheet(File spreadSheet, InputType inputType) {
		try {
			return new SpreadsheetCompiler().compile(
				new FileInputStream(spreadSheet),
				inputType
			);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String getRulesFromSpreadSheet(byte[] spreadSheet) {
		try {
			File fTemp = File.createTempFile("drools",".xls");
			fTemp.deleteOnExit();
			FileUtils.writeByteArrayToFile(fTemp, spreadSheet);
			return this.getRulesFromSpreadSheet(fTemp, InputType.XLS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String getRulesFromSpreadSheet(String spreadSheet) {
		try {
			File fTemp = File.createTempFile("drools",".csv");
			fTemp.deleteOnExit();
			FileUtils.writeLines(fTemp,
				new ArrayList<String>(
					Arrays.asList(
						spreadSheet.split(System.getProperty("line.separator"))
					)
				)
			);
			return this.getRulesFromSpreadSheet(fTemp, InputType.CSV);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void init() {
		this.knowledgeService = KieServices.Factory.get();
		this.knowledgeFileSystem = this.knowledgeService.newKieFileSystem();		
	}
	
	private void addRules(String... rules) {
		for (String rule : rules) {
			System.out.println(rule);
			try {
				File fTemp = File.createTempFile("drools",".drl");
				fTemp.deleteOnExit();
				
				FileUtils.writeLines(fTemp,
					new ArrayList<String>(
						Arrays.asList(
							rule.split(System.getProperty("line.separator"))
						)
					)
				);
				
				this.knowledgeFileSystem.write(
					this.knowledgeService.getResources().newFileSystemResource(fTemp)
				);
				
				Results results = this.knowledgeService.newKieBuilder(this.knowledgeFileSystem).buildAll().getResults();
				
				if(results.hasMessages(Message.Level.ERROR)){
				     System.out.println(results.getMessages());
				}
				
				KieBaseConfiguration kieBaseConfiguration = this.knowledgeService.newKieBaseConfiguration();
				kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
				
				this.knowledgeSession = this.knowledgeService.newKieContainer(
					this.knowledgeService.getRepository().getDefaultReleaseId()
				).newKieBase(
					this.knowledgeService.newKieBaseConfiguration()
				).newKieSession();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
