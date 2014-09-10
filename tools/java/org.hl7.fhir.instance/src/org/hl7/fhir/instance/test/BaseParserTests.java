package org.hl7.fhir.instance.test;

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

import java.text.ParseException;

import org.hl7.fhir.instance.model.DateAndTime;

// probably should be DUnit

public class BaseParserTests {

	public static class XMLParserTests {

		public void test() {
			try {
	      System.out.println(DateAndTime.now().toString());
	      System.out.println(new DateAndTime("1951-06").toString());
	      System.out.println(new DateAndTime("1951-06-04").toString());
	      System.out.println(new DateAndTime("1951-06-01Z").toString());
	      System.out.println(new DateAndTime("1951-06-01-09:30").toString());
	      System.out.println(new DateAndTime("1951-06-01+09:30").toString());
	      System.out.println(new DateAndTime("2013-06-08T10:57:34+01:00").toString());
	      System.out.println(new DateAndTime("2013-06-08T10:57:34-01:00").toString());
	      System.out.println(new DateAndTime("2013-06-08T09:57:34.2112Z").toString());
	      System.out.println(new DateAndTime("2013-06-08T09:57:34.2112425234Z").toString());

	      System.out.println(DateAndTime.parseV3("195106").toString());
	      System.out.println(DateAndTime.parseV3("19510604").toString());
	      System.out.println(DateAndTime.parseV3("19510601Z").toString());
	      System.out.println(DateAndTime.parseV3("19510601+0930").toString());
	      System.out.println(DateAndTime.parseV3("19510601-0930").toString());
	      System.out.println(DateAndTime.parseV3("20130608105734+0100").toString());
	      System.out.println(DateAndTime.parseV3("20130608105734-0100").toString());
	      System.out.println(DateAndTime.parseV3("20130608095734.2112Z").toString());
} catch (ParseException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
      }
    }
  }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new XMLParserTests().test();

	}

}
