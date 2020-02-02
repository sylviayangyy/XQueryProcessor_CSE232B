# REANDME

- The source codes are under the src/ directory.

- The XQueryProcessor.jar is the exported jar file. It can be executed as this:

  1. Enter the directory that contains the jar, for example

     `cd XQueryProcessor/`

  2. Execute a command like this:

     `java -jar XQueryProcessor.jar 'doc("j_caesar.xml")//(ACT,PERSONAE)/TITLE'`

     Reminder: the input query should be enclosed in single quotes, otherwise there will be a syntax error.

  3. Then there will be outputs in the terminal.
