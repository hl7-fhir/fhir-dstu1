package org.hl7.fhir.instance.model;

import java.net.URISyntaxException;
import java.text.ParseException;

import org.hl7.fhir.instance.model.Contact.ContactSystem;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;

/*
Copyright (c) 2011-2014, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/



public class Factory {

  public static IdType newId(String value) {
    if (value == null)
      return null;
    IdType res = new IdType();
    res.setValue(value);
    return res;
	}

  public static StringType newString(String value) {
    if (value == null)
      return null;
    StringType res = new StringType();
    res.setValue(value);
    return res;
  }

  public static UriType newUri(String value) throws URISyntaxException {
    if (value == null)
      return null;
    UriType res = new UriType();
    res.setValue(value);
    return res;
  }

  public static DateTimeType newDateTime(String value) throws ParseException {
    if (value == null)
      return null;
    DateTimeType res = new DateTimeType();
    res.setValue(new DateAndTime(value));
    return res;
  }

  public static DateType newDate(String value) throws ParseException {
    if (value == null)
      return null;
    DateType res = new DateType();
    res.setValue(new DateAndTime(value));
    return res;
  }

  public static CodeType newCode(String value) {
    if (value == null)
      return null;
    CodeType res = new CodeType();
    res.setValue(value);
    return res;
  }

  public static IntegerType newInteger(int value) {
    IntegerType res = new IntegerType();
    res.setValue(value);
    return res;
  }
  
  public static IntegerType newInteger(java.lang.Integer value) {
    if (value == null)
      return null;
    IntegerType res = new IntegerType();
    res.setValue(value);
    return res;
  }
  
  public static BooleanType newBoolean(boolean value) {
    BooleanType res = new BooleanType();
    res.setValue(value);
    return res;
  }
  
  public static Contact newContact(ContactSystem system, String value) {
	Contact res = new Contact();
	res.setSystemSimple(system);
	res.setValue(newString(value));
	return res;
  }

	public static Extension newExtension(String uri, Type value, boolean evenIfNull) throws Exception {
		if (!evenIfNull && value == null)
			return null;
		Extension e = new Extension();
		e.setUrlSimple(uri);
		e.setValue(value);
	  return e;
  }

	public static CodeableConcept newCodeableConcept(String code, String system, String display) throws Exception {
		CodeableConcept cc = new CodeableConcept();
		Coding c = new Coding();
		c.setCodeSimple(code);
		c.setSystemSimple(system);
		c.setDisplaySimple(display);
		cc.getCoding().add(c);
	  return cc;
  }

	public static ResourceReference makeResourceReference(String url) throws Exception {
	  ResourceReference rr = new ResourceReference();
	  rr.setReferenceSimple(url);
	  return rr;
  }
  
  public static DateTimeType nowDateTime() {
    DateTimeType dt = new DateTimeType();
    dt.setValue(DateAndTime.now());
    return dt;
  }

 public static Narrative newNarrative(NarrativeStatus status, String html) throws Exception {
    Narrative n = new Narrative();
    n.setStatusSimple(status);
    n.setDiv(new XhtmlParser().parseFragment("<div>"+Utilities.escapeXml(html)+"</div>"));
    return n;
 }

 public InstantType nowInstant() {
	 InstantType instant = new InstantType();
	 instant.setValue(DateAndTime.now());
	 return instant;
 }
 
}
