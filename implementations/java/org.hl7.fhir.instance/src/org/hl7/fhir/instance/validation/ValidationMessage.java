package org.hl7.fhir.instance.validation;

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

import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.utils.ToolingExtensions;

public class ValidationMessage 
{
  public enum Source {
    ExampleValidator,
    ProfileValidator,
    ResourceValidator, 
    InstanceValidator,
    Schema,
    Schematron
  }
  
  private Source source;
  private String location;
  private String message;
  private String type;
  private IssueSeverity level;
  
  
  public ValidationMessage(Source source, String type, String path, String message, IssueSeverity level) {
    super();
    this.location = path;
    this.message = message;
    this.level = level;
    this.source = source;
    this.type = type;
  }
  
  public ValidationMessage() {
  }

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public IssueSeverity getLevel() {
    return level;
  }
  public void setLevel(IssueSeverity level) {
    this.level = level;
  }
  
  public Source getSource() {
    return source;
  }
  public void setSource(Source source) {
    this.source = source;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String summary() {
    return level.toString()+" @ "+location+": "+message +(source != null ? " (src = "+source+")" : "");
  }

  public OperationOutcomeIssueComponent asIssue(OperationOutcome op) throws Exception {
    OperationOutcomeIssueComponent issue = new OperationOutcome.OperationOutcomeIssueComponent();
    if (type != null) {
      issue.setType(new Coding());
      issue.getType().setSystemSimple("http://hl7.org/fhir/issue-type");
      issue.getType().setCodeSimple(type);
    }
    if (location != null) {
      StringType s = new StringType();
      s.setValue(location);
      issue.getLocation().add(s);
    }
    issue.setSeveritySimple(level);
    issue.setDetailsSimple(message);
    if (source != null) {
      issue.getExtensions().add(ToolingExtensions.makeIssueSource(source));      
    }
    return issue;
  }
  
}