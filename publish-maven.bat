@echo Have you updated the version in this batch file? (0.0.82.2929)
pause
REM ant -f  tools/java/org.hl7.fhir.tools.core/build.xml  -DartifactId=fhir-dstu1 -Dversion=0.0.82.2929-SNAPSHOT deploy -e

ant -f  tools/java/org.hl7.fhir.tools.core/build.xml  -DartifactId=fhir-dstu1 -Dversion=0.0.82.2929 stage -e

