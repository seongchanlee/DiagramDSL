# DiagramDSL
DSL that allows you to easily create diagrams (just class diagrams for now).

## Grammar

**PROGRAM** : : = [STATEMENT]*  
**STATEMENT** : : =  [CLASSDEC METHODDEC*]+  
**CLASSDEC** : : = [class | abstract class | interface]? “class” CLASSNAME [RELATION]?  
**RELATION** : : = [extends CLASSNAME | implements [CLASSNAME] [“,” CLASSNAME]*]   
**METHODDEC** : : = MODIFIER [KEYWORDS]? “method” METHODNAME [PARAMETER]*  
**MODIFIER** : : = [public | private | protected]?  
**KEYWORDS** : : = [STATIC]? [FINAL]?  
**PARAMETER** : : = [“param” PARAMETERNAME]*  
**CLASSNAME**, **METHODNAME**, **PARAMETERNAME** : : = [a-zA-Z]+

## TODO
* Add class variable support
* Add support for other type of diagrams (e.g., sequence, etc.)