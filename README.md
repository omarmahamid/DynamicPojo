# DynamicPojo
creating dynamic pojos, with compiling in runtime.


[DynamicPojo.pptx](https://github.com/omarmahamid/DynamicPojo/files/10348226/DynamicPojo.pptx)



do you want to create a dynamic pojo and compile it in runtime ? 

ok here is what you search for it.


example:

from this json : 

{
  "id" : "DAccount",
  "fields" : [
    {
      "name": "isOpen",
      "type": "java.lang.Boolean"
    },
    {
      "name": "isClosed",
      "type": "java.lang.Boolean"
    },
    {
      "name": "id",
      "type": "java.lang.String"
    }
  ]
}


we will got this Pojo

import java.util.Boolean;
import java.util.String;


public class DAccount {
    private Boolean isOpen;
    private Boolean isClosed;
    private Boolean id;

    public Boolean isOpen(){
        return this.isOpen;
    }

    public Boolean isCloesd(){
        return this.isClosed;
    }
    
    public Boolean id(){
        return this.id;
    }
}


for more information look at the presentation.
