package rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Firewall {
	
	Set<RulesForNetwork> networkruleforrange = new HashSet<RulesForNetwork>();
	public Firewall(String file)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line;
			while((line = br.readLine()) !=null)
			{
				String[] inputrule = line.split(",");
				
				if (inputrule[2].contains("-")) { 
					String [] portRanges = inputrule[2].split("-");
					int portMinimum = Integer.parseInt(portRanges[0]);
					int portMaximum = Integer.parseInt(portRanges[1]);
					int portRange = portMaximum - portMinimum;

					
					if (inputrule[3].contains("-")) {
						String [] ipAddressRanges = inputrule[3].split("-");
						long ipAddressMin = Long.parseLong(ipAddressRanges[0].replaceAll("\\.", ""));
						long ipAddressMax = Long.parseLong(ipAddressRanges[1].replaceAll("\\.", ""));
						long ipRange = ipAddressMax - ipAddressMin;

						
						for (int i = 0; i <= portRange; i++) {
							for (int j = 0; j <= ipRange; j++) {
								RulesForNetwork currRule = new RulesForNetwork(inputrule[0], inputrule[1], portMinimum + i, ipAddressMin + j);
								networkruleforrange.add(currRule);
							}
						}

						
						for (int i = 0; i <= portRange; i++) {
							for (int j = 0; j <= ipRange; j++) {
								RulesForNetwork currRule = new RulesForNetwork(inputrule[0], inputrule[1], portMinimum + i, ipAddressMin + j);
								networkruleforrange.add(currRule);
							}
						}
					}else{

						
						for (int i = 0; i <= portRange; i++) {
							RulesForNetwork currRule = new RulesForNetwork(inputrule[0],inputrule[1], portMinimum + i, inputrule[3]);
							networkruleforrange.add(currRule);
						}
					}
				}  

				else 

				{ 
					
					if (inputrule[3].contains("-")) {
						String [] ipAddressRanges = inputrule[3].split("-");
						long ipAddressMin = Long.parseLong(ipAddressRanges[0].replaceAll("\\.", ""));
						long ipAddressMax = Long.parseLong(ipAddressRanges[1].replaceAll("\\.", ""));
						long ipRange = ipAddressMax - ipAddressMin;

						
						for (int i = 0; i <= ipRange; i++) {
							RulesForNetwork currRule = new RulesForNetwork(inputrule[0],inputrule[1],inputrule[2], ipAddressMin + i);
							networkruleforrange.add(currRule);
						}
					}
					else 
						
					{ 
						RulesForNetwork currRule = new RulesForNetwork(inputrule[0],inputrule[1],inputrule[2],inputrule[3]);
						networkruleforrange.add(currRule);
					}

				}

				
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(file + "Not Found");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	public boolean acceptPacket(String direction, String protocol, int port, String ipAddress) {
		RulesForNetwork rule = new RulesForNetwork(direction, protocol, port, ipAddress);
		if (networkruleforrange.contains(rule)) {
			return true;
		}
		else {
			return false;
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Firewall file = new Firewall("/Users/akshaydalavai/Documents/fw.csv");
		boolean t1 = file.acceptPacket("inbound", "udp",43,"12.53.6.25");
		boolean t2 = file.acceptPacket("inbound", "tcp",673,"123.45.56.83");
		boolean t3 = file.acceptPacket("outbound","tcp",10234,"192.168.10.11");
		boolean t4 = file.acceptPacket("inbound", "udp", 24, "52.12.48.92");
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		System.out.println(t4);
	}

}
