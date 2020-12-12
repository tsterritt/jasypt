# jasypt

Examples of using Jasypt encryption in different contexts.

 - jasypt-basics: junit classes to demonstrate using Jasypt encryption utilities, including adding a custom provider (BouncyCastle). 
   Also has a Main app that encrypts plain text and prints out the encrypted value.
   
 - jasypt-camel-spring4: a simple Camel route and test clases to demonstrates how to configure the Camel properties component to use a Jasypt properties parser.
   Uses the custom BouncyCastle provider, uses Spring Java config. 
   
 - jasypt-spring4-java: demonstrates using Jasypt using Spring 4 Java configuration
 
 - jasypt-spring4-xml: demonstrates using Jasypt with Spring 4 XML configuration
