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
import java.util.Calendar;

import org.hl7.fhir.instance.model.DateAndTime;


public class DateAndTimeTests {

	public void testParsing() throws ParseException {
		assert("2013-02-02".equals(new DateAndTime("2013-02-02").toString()));
		assert("2013-02".equals(new DateAndTime("2013-02").toString()));
		assert("2013".equals(new DateAndTime("2013").toString()));
		assert("2013-02-02T20:13".equals(new DateAndTime("2013-02-02T20:13").toString()));
		assert("2013-02-02T20:13:03".equals(new DateAndTime("2013-02-02T20:13:03").toString()));
		assert("2013-02-02T20:13:03Z".equals(new DateAndTime("2013-02-02T20:13:03Z").toString()));
		assert("2013-02-02T20:13:03+05:00".equals(new DateAndTime("2013-02-02T20:13:03+05:00").toString()));
		assert("2013-02-02T20:13:03-05:00".equals(new DateAndTime("2013-02-02T20:13:03-05:00").toString()));
		assert("2013-02-02T20:13-05:00".equals(new DateAndTime("2013-02-02T20:13-05:00").toString()));
		assert("2013-02-02T20:13-00:00".equals(new DateAndTime("2013-02-02T20:13-00:00").toString()));
		assert("2013-02-02-05:00".equals(new DateAndTime("2013-02-02-05:00").toString()));
	}


//	@Test
//	public void testCalnendar() throws ParseException {
//		DateAndTime dat = new DateAndTime("2013-02-02T20:13+05:00");
//		Calendar date = dat.toCalendar();
//		DateAndTime dat2 = new DateAndTime(date);
//		assertEquals(dat.toString(), dat2.toString());
//	}
	
	public void testCalendar() throws ParseException, InterruptedException {
		DateAndTime dt = DateAndTime.now();
		Thread.sleep(1200);
		DateAndTime dt2 = DateAndTime.now();
//		assertNotEquals(dt.toString(), dt2.toString());
	}	
	
	public void testV3() throws ParseException {
		assert("2013-02-02".equals(DateAndTime.parseV3("20130202").toString()));
		assert("2013-02".equals(DateAndTime.parseV3("201302").toString()));
		assert("2013".equals(DateAndTime.parseV3("2013").toString()));
		assert("2013-02-02T20:13".equals(DateAndTime.parseV3("201302022013").toString()));
		assert("2013-02-02T20:13:03".equals(DateAndTime.parseV3("20130202201303").toString()));
		assert("2013-02-02T20:13:03Z".equals(DateAndTime.parseV3("20130202201303Z").toString()));
		assert("2013-02-02T20:13:03+05:00".equals(DateAndTime.parseV3("20130202201303+0500").toString()));
		assert("2013-02-02T20:13:03-05:00".equals(DateAndTime.parseV3("20130202201303-0500").toString()));
		assert("2013-02-02T20:13-05:00".equals(DateAndTime.parseV3("201302022013-0500").toString()));
		assert("2013-02-02T20:13-00:00".equals(DateAndTime.parseV3("201302022013-0000").toString()));
		assert("2013-02-02-05:00".equals(DateAndTime.parseV3("20130202-0500").toString()));
	}

	public void testBefore() throws ParseException {
		assert(!new DateAndTime("2013-02-02").before(new DateAndTime("2013-02-01")));
		assert(!new DateAndTime("2013-02-02").before(new DateAndTime("2013-02-02")));
		assert(new DateAndTime("2013-02-02").before(new DateAndTime("2013-02-03")));
		assert(!new DateAndTime("2013-02").before(new DateAndTime("2013-01")));
		assert(!new DateAndTime("2013-02").before(new DateAndTime("2012-01")));
		assert(!new DateAndTime("2013").before(new DateAndTime("2012")));
		assert(!new DateAndTime("2013-02-02T20:13").before(new DateAndTime("2013-02-02T20:12")));
		assert(!new DateAndTime("2013-02-02T20:13:03").before(new DateAndTime("2013-02-02T20:13:02")));
		assert(!new DateAndTime("2013-02-02T20:13:03").before(new DateAndTime("2013-02-02T20:13:03")));
		assert(!new DateAndTime("2013-02-02T20:13:03Z").before(new DateAndTime("2013-02-02T20:13:02Z")));
		assert(!new DateAndTime("2013-02-02T20:13:03Z").before(new DateAndTime("2013-02-01T20:13:05Z")));
		assert(!new DateAndTime("2013-02-02T20:13:03Z").before(new DateAndTime("2013-02-02T20:13:02+01:00")));
	}

	public void testAdd() throws Exception {
    // simple addition
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, 1, "2013-02-03T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, 1, "2013-03-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.YEAR, 1, "2014-02-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, 1, "2013-02-02T21:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, 1, "2013-02-02T20:14:15");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, 1, "2013-02-02T20:13:16");
		
		// boundary conditions
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, -1, "2013-02-01T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, -1, "2013-01-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.YEAR, -1, "2012-02-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, -1, "2013-02-02T19:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, -1, "2013-02-02T20:12:15");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, -1, "2013-02-02T20:13:14");

		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, 60, "2013-02-02T20:14:15");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, 45, "2013-02-02T20:14:00");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, 46, "2013-02-02T20:14:01");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, -15, "2013-02-02T20:13:00");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, -16, "2013-02-02T20:12:59");
		checkAdd("2013-02-02T20:13:15", Calendar.SECOND, -60, "2013-02-02T20:12:15");

		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, 60, "2013-02-02T21:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, 47, "2013-02-02T21:00:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, 48, "2013-02-02T21:01:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, -13, "2013-02-02T20:00:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, -16, "2013-02-02T19:57:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MINUTE, -60, "2013-02-02T19:13:15");

		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, 24, "2013-02-03T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, 4, "2013-02-03T00:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, 5, "2013-02-03T01:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, -20, "2013-02-02T00:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, -21, "2013-02-01T23:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.HOUR, -24, "2013-02-01T20:13:15");

		// testing days. This is complicated because we run into calendar issues
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, 28, "2013-03-02T20:13:15");
		checkAdd("2013-03-02T20:13:15", Calendar.DAY_OF_YEAR, 31, "2013-04-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, 26, "2013-02-28T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, -1, "2013-02-01T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, -2, "2013-01-31T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.DAY_OF_YEAR, -31, "2013-01-02T20:13:15");

		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, 12, "2014-02-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, 11, "2014-01-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, 10, "2013-12-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, -1, "2013-01-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, -2, "2012-12-02T20:13:15");
		checkAdd("2013-02-02T20:13:15", Calendar.MONTH, -12, "2012-02-02T20:13:15");

	}


	private void checkAdd(String base, int field, int value, String outcome) throws Exception {
		DateAndTime dt = new DateAndTime(base);
		dt.add(field, value);
		assert(outcome.equals(dt.toString()));
  }

	
}
