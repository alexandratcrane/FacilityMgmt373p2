package view;
import org.springframework.context.ApplicationContext;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import businessModel.Facility.*;
import businessModel.Maintenance.*;
import businessModel.Use.*;

public class FacilityManageClient {
    public static void main (String args[]) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/app-context.xml");
    	
    	//FacilityManager fmanager = new FacilityManager();
    	FacilityManager fmanager = (FacilityManager) context.getBean("FacilityManager");
    	FacilityFactory ff = (FacilityFactory) context.getBean("FacilityFactory");
    	
    	Facility fac1 = FacilityFactory.addNewFacility(1);
    	fac1.setId(1);
    	fmanager.addNewFacility(fac1);
		Facility fac2 = FacilityFactory.addNewFacilityDetail(2, "A storage facility");
		//fac2.setId(2);
		//fac2.setDescription("A storage facility");
		fmanager.addNewFacility(fac2);
		
		
		
		/* PRINTING OUT A CLIENT*/
		System.out.println("Here are the facilities we have available:");
		for(Facility f: fmanager.listFacilities()){
			System.out.println("Facility ID: " + f.getFacilityInformation() + " Description: "+ f.getDescription() + ", ");
			
		}
		
		System.out.println("_________________________________________________________________________________________________\n");
		//printing out inspections requested 
		
		Inspection i1 = (Inspection) context.getBean("Inspection1");
	
		fac1.getMaintManag().addNewInspection(i1);
		
		System.out.println("Inspection requested");
		System.out.println("Inspection ID: " + i1.getInspectionId());
		
		Inspection i2 = (Inspection) context.getBean("Inspection2");
		
		fac1.getMaintManag().addNewInspection(i2);
		System.out.println(" ");
		System.out.println("Inspection requested");
		System.out.println("Inspection ID: " + i2.getInspectionId());
		System.out.println(" ");
		
		//printing out problem requested 
		
		Problem p1 = (Problem) context.getBean("Problem1");
		fac1.getProbManag().addProblem(p1);
		System.out.println("Problem detected: " + p1.getProbDesc());
		
		//printing out request to fix 
		System.out.println("_________________________________________________________________________________________________\n");
		Requests r1 = (Requests) context.getBean("Request1");
		r1.scheduleMaintenance(1200, 1300);
		fac1.getMaintManag().makeFacilityMaintRequest(r1);
		System.out.println("Maintenance request created: ");
		System.out.println("Request ID: " + r1.getRequestId());
		System.out.println(r1.getSchedule());
		System.out.println("Downtime requested: " + r1.calcDownTimeForFacility() + " minutes");
		System.out.println(" ");
		System.out.println("Problems: ");
		fac1.getProbManag().listFacilityProblems();
		System.out.println(" ");

		System.out.println("Cost for facility " + fac1.getFacilityInformation() + ": $" + fac1.getProbManag().calcMaintenanceCostForFacility());
		
		System.out.println(" ");
		System.out.println("_________________________________________________________________________________________________\n");
		
		Use u1 = (Use) context.getBean("Use1");
		u1.UseSchedule(1400, 1500, "23451");
		UseManager umang = (UseManager) context.getBean("UseManager");
		umang.addNewUse(u1);
		umang.setCapacity(100);
		
		System.out.println(" ");
		System.out.println("Use facility for event: " + u1.getUseId());
		System.out.println("From " + u1.getSchedule());
		System.out.println("Capacity at: " + umang.requestAvailableCapacity() + " people");
		
		
		//I'm basing this off of the client from the Bookstore Lab we did before.
		
    	
    	
    }
}
