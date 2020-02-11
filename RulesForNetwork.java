package rules;

public class RulesForNetwork {
	
	 protected String direction;
     protected String protocol;
     protected int port;
     protected long ipAddress;
     

     public RulesForNetwork(String direction, String protocol, String port, String ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = Integer.parseInt(port);
         this.ipAddress = Long.parseLong(ipAddress.replaceAll("\\.", "")); 
     }

     public RulesForNetwork(String direction, String protocol, String port, long ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = Integer.parseInt(port);
         this.ipAddress = ipAddress;
     }

     public RulesForNetwork(String direction, String protocol, int port, long ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = port;
         this.ipAddress = ipAddress;
     }

     public RulesForNetwork(String direction, String protocol, int port, String ipAddress) {
         this.direction = direction;
         this.protocol = protocol;
         this.port = port;
         this.ipAddress = Long.parseLong(ipAddress.replaceAll("\\.", "")); 
     }
     
     @Override
     public boolean equals(Object obj) {
         if (!(obj instanceof RulesForNetwork)){
        	 return false;
         }
         if(this == obj){
        	 return true;
         }
         RulesForNetwork networkRule = (RulesForNetwork) obj;
         return  direction.equalsIgnoreCase(networkRule.direction) && protocol.equalsIgnoreCase(networkRule.protocol)
        		 && port == networkRule.port && ipAddress == networkRule.ipAddress;
     }

     @Override
     public String toString() {
         return this.direction +  ", " + this.protocol + ", " + Integer.toString(this.port) + ", " + Long.toString(this.ipAddress);
     }


     public int hashCode() {
         long hash =  this.ipAddress + this.port + this.direction.hashCode() + this.protocol.hashCode(); 
         return Long.valueOf(hash).hashCode();
     }


}
