package org.hl7.fhir.instance.test;

import java.io.FileInputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.instance.client.FeedFormat;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.utils.ConceptLocator;
import org.hl7.fhir.instance.validation.ExtensionLocatorService;
import org.hl7.fhir.instance.validation.InstanceValidator;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.w3c.dom.Document;

public class ConnectathonProfileTest implements ExtensionLocatorService, ConceptLocator {

	private static final String BASE = "C:\\work\\org.hl7.fhir.dstu";
	private Profile profile;

	public static void main(String[] args) throws Exception {
		ConnectathonProfileTest self = new ConnectathonProfileTest ();
		self.execute();
	}

	private void execute() throws Exception {
	  InstanceValidator val = new InstanceValidator(BASE+"\\build\\publish\\validation.zip", this, this);
	  
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    factory.setValidating(false);

    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new FileInputStream(BASE+"\\build\\tests\\profiles\\lipid-example-connectathon-7.xml"));

	  profile = (Profile) new XmlParser().parse(new FileInputStream(BASE+"\\build\\tests\\profiles\\lipid-profile-connectathon-7.xml"));
	  List<ValidationMessage> output = val.validateInstance(doc.getDocumentElement(), profile, "http://hl7.org/fhir/Profile/example-lipid-profile");
  	System.out.println();
  	System.out.println("--------------------------");
	  for (ValidationMessage m : output)
	  	System.out.println(m.getMessage());
  }

	@Override
  public ValueSetDefineConceptComponent locate(String system, String code) {
		throw new Error("Not handled yet");
  }

	@Override
  public ValidationResult validate(String system, String code, String display) {
		throw new Error("Not handled yet");
  }

	@Override
  public boolean verifiesSystem(String system) {
		return false; 
  }

	@Override
  public List<ValueSetExpansionContainsComponent> expand(ConceptSetComponent inc) throws Exception {
		throw new Exception("Unable to expand in "+inc.getSystemSimple());
  }

	@Override
  public ExtensionLocationResponse locateExtension(String uri) {
		if (uri.startsWith("http://hl7.org/fhir/Profile/example-lipid-profile")) {
			String code = uri.substring(50);
			for (ProfileExtensionDefnComponent t : profile.getExtensionDefn()) {
				if (t.getCodeSimple().equals(code))
					return new ExtensionLocationResponse(Status.Located, t, null);
			}
		}
		
		System.out.println("Request to locate extension "+uri);
		return new ExtensionLocationResponse(Status.Unknown, null, null);
 }

}
