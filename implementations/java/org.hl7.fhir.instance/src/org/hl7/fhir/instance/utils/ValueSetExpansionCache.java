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

import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.model.ValueSet;

public class ValueSetExpansionCache implements ValueSetExpanderFactory {

  public class CacheAwareExpander implements ValueSetExpander {

	  @Override
	  public ValueSet expand(ValueSet source) throws Exception {
	  	if (expansions.containsKey(source.getIdentifierSimple()))
	  		return expansions.get(source.getIdentifierSimple());
	  	ValueSetExpander vse = new ValueSetExpanderSimple(valuesets, codesystems, ValueSetExpansionCache.this, locator);
	  	ValueSet vs = vse.expand(source);
	  	expansions.put(source.getIdentifierSimple(), vs);
	  	return vs;
	  }
  }

	private Map<String, ValueSet> valuesets;
	private Map<String, ValueSet> codesystems;
	private Map<String, ValueSet> expansions = new HashMap<String, ValueSet>();
	private ConceptLocator locator;
	
	public ValueSetExpansionCache(Map<String, ValueSet> valuesets, Map<String, ValueSet> codesystems, ConceptLocator locator) {
    super();
    this.valuesets = valuesets;
    this.codesystems = codesystems;
    this.locator = locator;
  }
  
	@Override
	public ValueSetExpander getExpander() {
		return new CacheAwareExpander();
		// return new ValueSetExpanderSimple(valuesets, codesystems);
	}

}
