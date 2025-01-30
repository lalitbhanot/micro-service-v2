# Refactoring Microservices for k8s
I removed the eureka server and added the spring profiles properties file in client folder. 
when I made the call to the client  code from 
- customer 
- fraud 
- notification 

then "path not found" and "host not found" error were coming.

This was because all these modules had the dependency of client module 
in pom.xml which was reflecting to  the old jar created. 

Solution:
- Manually removed the dependency from all three modules.
- Added the same dependency again
  <dependency>
  <groupId>com.lalit</groupId>
  <artifactId>clients</artifactId>
  <version>0.0.1-snapshot</version>
  <scope>compile</scope>
  </dependency>
- Cleaned and built the modules again.
- Ran the code on local.

This solved the problem.

for running on local : SPRING_PROFILES_ACTIVE=default