package org.hl7.fhir.instance.utils;

/*
Copyright (c) 2011+, HL7, Inc
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

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.Utilities;


public class ToolingExtensions {

  public static final String EXT_COMMENT = "http://hl7.org/fhir/Profile/tools-extensions#comment";
  public static final String EXT_DISPLAY = "http://hl7.org/fhir/Profile/tools-extensions#display";
  public static final String EXT_DEFINITION = "http://hl7.org/fhir/Profile/tools-extensions#definition";
  public static final String EXT_DEPRECATED = "http://hl7.org/fhir/Profile/tools-extensions#deprecated";
  public static final String EXT_ISSUE_SOURCE = "http://hl7.org/fhir/Profile/tools-extensions#issue-source";
  public static final String EXT_SUBSUMES = "http://hl7.org/fhir/Profile/tools-extensions#subsumes";

  public static Extension makeIssueSource(Source source) {
    Extension ex = new Extension();
    // todo: write this up and get it published with the pack (and handle the redirect?)
    ex.setUrlSimple(ToolingExtensions.EXT_ISSUE_SOURCE);
    CodeType c = new CodeType();
    c.setValue(source.toString());
    ex.setValue(c);
    return ex;
  }

  public static void addComment(CodeType nc, String comment) throws Exception {
    if (!Utilities.noString(comment))
      nc.getExtensions().add(Factory.newExtension(EXT_COMMENT, Factory.newString(comment), true));   
  }

  public static void markDeprecated(Element nc) throws Exception {
    nc.getExtensions().add(Factory.newExtension(EXT_DEPRECATED, Factory.newBoolean(true), true));   
  }

  public static void addSubsumes(ValueSetDefineConceptComponent nc, String code) throws Exception {
    nc.getModifierExtensions().add(Factory.newExtension(EXT_SUBSUMES, Factory.newCode(code), true));   
  }

  public static void addDefinition(CodeType nc, String definition) throws Exception {
    if (!Utilities.noString(definition))
      nc.getExtensions().add(Factory.newExtension(EXT_DEFINITION, Factory.newString(definition), true));   
  }

  public static String readStringExtension(Element c, String uri) {
    Extension ex = c.getExtension(uri);
    if (ex == null)
      return null;
    if (!(ex.getValue() instanceof StringType))
      return null;
    return ((StringType) ex.getValue()).getValue();
  }

  public static boolean findStringExtension(Element c, String uri) {
    Extension ex = c.getExtension(uri);
    if (ex == null)
      return false;
    if (!(ex.getValue() instanceof StringType))
      return false;
    return !Utilities.noString(((StringType) ex.getValue()).getValue());
  }

  public static String readBooleanExtension(Element c, String uri) {
    Extension ex = c.getExtension(uri);
    if (ex == null)
      return null;
    if (!(ex.getValue() instanceof BooleanType))
      return null;
    return java.lang.Boolean.toString(((BooleanType) ex.getValue()).getValue());
  }

  public static boolean findBooleanExtension(Element c, String uri) {
    Extension ex = c.getExtension(uri);
    if (ex == null)
      return false;
    if (!(ex.getValue() instanceof BooleanType))
      return false;
    return true;
  }

  public static String getComment(ValueSetDefineConceptComponent c) {
    return readStringExtension(c, EXT_COMMENT);    
  }

  public static String getDeprecated(ValueSetDefineConceptComponent c) {
    return readBooleanExtension(c, EXT_DEPRECATED);    
  }

  public static boolean hasComment(ValueSetDefineConceptComponent c) {
    return findStringExtension(c, EXT_COMMENT);    
  }

  public static boolean hasDeprecated(ValueSetDefineConceptComponent c) {
    return findBooleanExtension(c, EXT_DEPRECATED);    
  }

  public static List<CodeType> getSubsumes(ValueSetDefineConceptComponent c) {
    List<CodeType> res = new ArrayList<CodeType>();

    for (Extension e : c.getExtensions()) {
      if (EXT_SUBSUMES.equals(e.getUrlSimple()))
        res.add((CodeType) e.getValue());
    }
    return res;
  }

}
