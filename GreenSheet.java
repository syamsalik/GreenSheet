package greenSheet;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.apache.pdfbox.pdmodel.interactive.form.PDVariableText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class GreenSheet {
	
    public static String[] dt_Dates = {
    		"2024-01-01", "2024-02-19", "2024-03-29", "2024-05-20",
    		"2024-07-01", "2024-09-02", "2024-10-14", "2024-12-25", "2024-12-26",
    		"2025-01-01", "2025-02-17", "2025-04-18", "2025-05-19", "2025-07-01",
    		"2025-09-01", "2025-10-13", "2025-12-25", "2025-12-26", "2026-01-01",
    		"2026-02-16", "2026-04-03", "2026-05-18", "2026-07-01", "2026-09-07",
    		"2026-10-12", "2026-12-25", "2026-12-26", "2027-01-01", "2027-02-15",
    		"2027-03-26", "2027-05-24", "2027-07-01", "2027-09-06", "2027-10-11", 
    		"2027-12-25", "2027-12-26"};
	
    public static void main(String[] args) {
    	
    	 
    	
    	
        System.out.println("Test 1 == Passed [Program Loaded Successfully]");
        
        UKGmyKronosSurfer surfer = new UKGmyKronosSurfer();
        
        //surfer.GetUKG();
        ScheduleAccess("/Users/syamsalik/Developer/UGK/Schedule.txt");

    }
    
    public static void GreenSheetGenerator(
    		// Name of Flagman
    		String name,
    		String dayOfWeek,
    		String dayNumber,
    		String date,
    		String month,
    		String year,
    		String StartTime,
    		String EndTime,
    		double hoursWorked,
    		String Subdivision,
    		String projectNumber,
    		String ProjectName,
    		String WorkingLimits,
    		String StartLimit,
    		String EndLimit,
    		String zone,
    		String protection,
    		String[][] contractor 
    		) {
    	
    	
        System.out.println("Test 2 == Passed [Green Sheet Generator Loaded Successfully]");
        
    	String day = dayOfWeek;
    	


    	String totalSt;
    	String totalOt;
    	String totalDt;

        try (PDDocument document = PDDocument.load(new File("/Users/syamsalik/Developer/Blank greensheet - ALL-Unfilled V3.pdf"))) {
            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
            

            PDResources resources = acroForm.getDefaultResources();
            if (resources == null) {
                resources = new PDResources();
                acroForm.setDefaultResources(resources);
            }
            //‡‡

            PDFont font = PDType1Font.HELVETICA;



        	//printFieldNames(document);
            
            if (acroForm != null) {
            	acroForm.setNeedAppearances(true);
            	
                
              
                if (acroForm.getDefaultResources() == null) {
                    acroForm.setDefaultResources(new PDResources());
                }

                String fontName = acroForm.getDefaultResources().add(font).getName();
                
             
                String defaultAppearance = "/" + fontName + " 12 Tf 0 g";
                
                
                PDTextField field_Name = (PDTextField) acroForm.getField("NAME");
                field_Name.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                field_Name.setValue(name);
                
                PDTextField field_FLAGPERSON_NAME = (PDTextField) acroForm.getField("FLAGPERSON NAME");
                field_FLAGPERSON_NAME.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                field_FLAGPERSON_NAME.setValue(name);
                
                PDTextField field_MONTH = (PDTextField) acroForm.getField("MONTH");
                field_MONTH.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                field_MONTH.setValue(month);
                
                PDTextField field_YEAR = (PDTextField) acroForm.getField("YEAR");
                field_YEAR.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                field_YEAR.setValue(year);
                
                PDTextField field_Subdivision = (PDTextField) acroForm.getField("SUBDIVISION");
                field_Subdivision.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                field_Subdivision.setValue(Subdivision);
                
                PDTextField field_PROJECT_NUMBER = (PDTextField) acroForm.getField("PROJECT NUMBER");
                field_PROJECT_NUMBER.setDefaultAppearance(defaultAppearance);
                field_PROJECT_NUMBER.setValue(projectNumber);
                
                PDTextField field_PROJECT_NAME = (PDTextField) acroForm.getField("PROJECT TITLE");
                field_PROJECT_NAME.setDefaultAppearance(defaultAppearance);
                field_PROJECT_NAME.setValue(ProjectName);
                
                PDTextField field_WORKING_LIMITS = (PDTextField) acroForm.getField("WORK MILAGE 1");
                field_WORKING_LIMITS.setDefaultAppearance(defaultAppearance);
                field_WORKING_LIMITS.setValue(WorkingLimits);
                
                PDTextField field_START_LIMITS = (PDTextField) acroForm.getField("LIMIT");
                field_START_LIMITS.setDefaultAppearance(defaultAppearance);
                field_START_LIMITS.setValue(StartLimit);
                
                PDTextField field_END_LIMITS = (PDTextField) acroForm.getField("TO LIMIT");
                field_END_LIMITS.setDefaultAppearance(defaultAppearance);
                field_END_LIMITS.setValue(EndLimit);
                
                if (zone.contains("A")) {
                PDTextField field_ZONE_A = (PDTextField) acroForm.getField("ZONE A");
                field_ZONE_A.setDefaultAppearance(defaultAppearance);
                field_ZONE_A.setValue("X");
                
                } else if (zone.contains("B")) {
                PDTextField field_ZONE_B = (PDTextField) acroForm.getField("ZONE B");
                field_ZONE_B.setDefaultAppearance(defaultAppearance);
                field_ZONE_B.setValue("X");		
                }
                
                if (protection.equalsIgnoreCase("R842")) {
                    PDCheckBox R842checkBox = (PDCheckBox) acroForm.getField("R842");
                    if (R842checkBox != null) { R842checkBox.check(); }
                } else if (protection.equalsIgnoreCase("TOP")) {
                    PDCheckBox CN_TOP_checkBox = (PDCheckBox) acroForm.getField("TOP");
                    if (CN_TOP_checkBox != null) { CN_TOP_checkBox.check(); }
                }
                
                
                if (contractor.length > 0 && contractor[0].length > 1) {
                	String contractorName = contractor[0][0]; 
                    PDTextField field_REPRESENTATIVE_NAME = (PDTextField) acroForm.getField("REPRESENTATIVE NAME");
                    field_REPRESENTATIVE_NAME.setDefaultAppearance(defaultAppearance);
                    field_REPRESENTATIVE_NAME.setValue(contractorName); 
                }

                
                if (contractor.length > 0 && contractor[0].length > 1) {
                    String phoneNumber = contractor[0][1]; 
                    PDTextField field_PHONE_NUMBER = (PDTextField) acroForm.getField("PHONE NUMBER");
                    field_PHONE_NUMBER.setDefaultAppearance(defaultAppearance);
                    field_PHONE_NUMBER.setValue(phoneNumber); 
                }
                

                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                if(dayOfWeek.equalsIgnoreCase("Mon")) {
                	
                
                    String st_Time = "";
                    String ot_Time = "";
                    
                     
                    
              
                    if (hoursWorked > 10) {
                        st_Time = "10";
                       
                        double overtime = hoursWorked - 10;
                       
                        ot_Time = String.valueOf(overtime);
                    } else {
                        st_Time = String.valueOf(hoursWorked);
                        ot_Time = "0";
                    }
                    
                    
                	String stMon = st_Time;
                	String otMon = ot_Time;
                	String dtMon = "0";
                	
                	
                	Set<String> holidaySet = new HashSet<>();
                 
                    for(String holiday : dt_Dates) {
                        holidaySet.add(holiday);
                    }

                
                    if(holidaySet.contains(date)) {
                    	dtMon = String.valueOf(hoursWorked);
                    	
                    	stMon = "0";
                    	otMon = "0";
                    	
                        totalSt = "0";
                    	totalOt = "0";
                    	totalDt = String.valueOf(hoursWorked);
                    } 
                    
                    totalSt = stMon;
                	totalOt = otMon;
                	totalDt = dtMon;
                	
                	String startTimeMon = StartTime;
                	String endTimeMon = EndTime;

                    
                    PDTextField fieldMon = (PDTextField) acroForm.getField("DATE-MON");
                    fieldMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    fieldMon.setValue(dayNumber);
                    
                    PDTextField field_ST_Mon = (PDTextField) acroForm.getField("ST - MON");
                    field_ST_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_ST_Mon.setValue(stMon);
                    
                    PDTextField field_OT_Mon = (PDTextField) acroForm.getField("OT - MON");
                    field_OT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_OT_Mon.setValue(otMon);
                    
                    PDTextField field_DT_Mon = (PDTextField) acroForm.getField("DT - MON");
                    field_DT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_DT_Mon.setValue(dtMon);
                    
                    PDTextField field_StartTimeMon = (PDTextField) acroForm.getField("START TIME - MON");
                    field_StartTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_StartTimeMon.setValue(startTimeMon);
                    
                    PDTextField field_EndTimeMon = (PDTextField) acroForm.getField("END TIME - MON");
                    field_EndTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_EndTimeMon.setValue(endTimeMon);
                    
                    PDTextField field_Total_ST = (PDTextField) acroForm.getField("ST");
                    field_Total_ST.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_ST.setValue(totalSt);
                    
                    PDTextField field_Total_OT = (PDTextField) acroForm.getField("OT");
                    field_Total_OT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_OT.setValue(totalOt);
                    
                    PDTextField field_Total_DT = (PDTextField) acroForm.getField("DT");
                    field_Total_DT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_DT.setValue(totalDt);
                    
                    if(!ProjectName.equalsIgnoreCase("")){
                    document.save("/Users/syamsalik/Developer/GREEN SHEET/ " + name + " - " + date + ".pdf");
                    }
                } 
                
                
                if(dayOfWeek.equalsIgnoreCase("Tue")) {
                	
                    
                    String st_Time = "";
                    String ot_Time = "";
                    
                    
                    if (hoursWorked > 10) {
                        st_Time = "10";
                        
                        double overtime = hoursWorked - 10;
                        
                        ot_Time = String.valueOf(overtime);
                    } else {
                        st_Time = String.valueOf(hoursWorked);
                        ot_Time = "0";
                    }
                    
                    
                	String stMon = st_Time;
                	String otMon = ot_Time;
                	String dtMon = "0";
                	
                	
                	Set<String> holidaySet = new HashSet<>();
                    
                    for(String holiday : dt_Dates) {
                        holidaySet.add(holiday);
                    }

                    
                    if(holidaySet.contains(date)) {
                    	dtMon = String.valueOf(hoursWorked);
                    	
                    	stMon = "0";
                    	otMon = "0";
                    	
                        totalSt = "0";
                    	totalOt = "0";
                    	totalDt = String.valueOf(hoursWorked);
                    } 
                    
                    totalSt = stMon;
                	totalOt = otMon;
                	totalDt = dtMon;
                	
                	String startTimeMon = StartTime;
                	String endTimeMon = EndTime;

                    PDTextField fieldMon = (PDTextField) acroForm.getField("DATE-TUE");
                    fieldMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    fieldMon.setValue(dayNumber);
                    
                    PDTextField field_ST_Mon = (PDTextField) acroForm.getField("ST - TUE");
                    field_ST_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_ST_Mon.setValue(stMon);
                    
                    PDTextField field_OT_Mon = (PDTextField) acroForm.getField("OT - TUE");
                    field_OT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_OT_Mon.setValue(otMon);
                    
                    PDTextField field_DT_Mon = (PDTextField) acroForm.getField("DT - TUE");
                    field_DT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_DT_Mon.setValue(dtMon);
                    
                    PDTextField field_StartTimeMon = (PDTextField) acroForm.getField("START TIME - TUE");
                    field_StartTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_StartTimeMon.setValue(startTimeMon);
                    
                    PDTextField field_EndTimeMon = (PDTextField) acroForm.getField("END TIME - TUE");
                    field_EndTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_EndTimeMon.setValue(endTimeMon);
                    
                    PDTextField field_Total_ST = (PDTextField) acroForm.getField("ST");
                    field_Total_ST.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_ST.setValue(totalSt);
                    
                    PDTextField field_Total_OT = (PDTextField) acroForm.getField("OT");
                    field_Total_OT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_OT.setValue(totalOt);
                    
                    PDTextField field_Total_DT = (PDTextField) acroForm.getField("DT");
                    field_Total_DT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_DT.setValue(totalDt);
                    
                    if(!ProjectName.equalsIgnoreCase("")){
                    document.save("/Users/syamsalik/Developer/GREEN SHEET/ " + name + " - " + date + ".pdf");
                    }
                    
                } 
                
                	
                if(dayOfWeek.equalsIgnoreCase("Wed")) {
                	
                    
                    String st_Time = "";
                    String ot_Time = "";
                    
                     
                    
                    
                    if (hoursWorked > 10) {
                        st_Time = "10";
                        
                        double overtime = hoursWorked - 10;
                      
                        ot_Time = String.valueOf(overtime);
                    } else {
                        st_Time = String.valueOf(hoursWorked);
                        ot_Time = "0";
                    }
                    
                    
                	String stMon = st_Time;
                	String otMon = ot_Time;
                	String dtMon = "0";
                	
                	
                	Set<String> holidaySet = new HashSet<>();
                    
                    for(String holiday : dt_Dates) {
                        holidaySet.add(holiday);
                    }

                   
                    if(holidaySet.contains(date)) {
                    	dtMon = String.valueOf(hoursWorked);
                    	
                    	stMon = "0";
                    	otMon = "0";
                    	
                        totalSt = "0";
                    	totalOt = "0";
                    	totalDt = String.valueOf(hoursWorked);
                    } 
                    
                    totalSt = stMon;
                	totalOt = otMon;
                	totalDt = dtMon;
                	
                	String startTimeMon = StartTime;
                	String endTimeMon = EndTime;

                    PDTextField fieldMon = (PDTextField) acroForm.getField("DATE-WED");
                    fieldMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    fieldMon.setValue(dayNumber);
                    
                    PDTextField field_ST_Mon = (PDTextField) acroForm.getField("ST - WED");
                    field_ST_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_ST_Mon.setValue(stMon);
                    
                    PDTextField field_OT_Mon = (PDTextField) acroForm.getField("OT - WED");
                    field_OT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_OT_Mon.setValue(otMon);
                    
                    PDTextField field_DT_Mon = (PDTextField) acroForm.getField("DT - WED");
                    field_DT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_DT_Mon.setValue(dtMon);
                    
                    PDTextField field_StartTimeMon = (PDTextField) acroForm.getField("START TIME - WED");
                    field_StartTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_StartTimeMon.setValue(startTimeMon);
                    
                    PDTextField field_EndTimeMon = (PDTextField) acroForm.getField("END TIME - WED");
                    field_EndTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_EndTimeMon.setValue(endTimeMon);
                    
                    PDTextField field_Total_ST = (PDTextField) acroForm.getField("ST");
                    field_Total_ST.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_ST.setValue(totalSt);
                    
                    PDTextField field_Total_OT = (PDTextField) acroForm.getField("OT");
                    field_Total_OT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_OT.setValue(totalOt);
                    
                    PDTextField field_Total_DT = (PDTextField) acroForm.getField("DT");
                    field_Total_DT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_DT.setValue(totalDt);
                    
                    if(!ProjectName.equalsIgnoreCase("")){
                    document.save("/Users/syamsalik/Developer/GREEN SHEET/ " + name + " - " + date + ".pdf");
                    }
                    
                } 
                
                if(dayOfWeek.equalsIgnoreCase("Thu")) {
                	
                   
                    String st_Time = "";
                    String ot_Time = "";
                    
                     
                    
                 
                    if (hoursWorked > 10) {
                        st_Time = "10";
                       
                        double overtime = hoursWorked - 10;
                       
                        ot_Time = String.valueOf(overtime);
                    } else {
                        st_Time = String.valueOf(hoursWorked);
                        ot_Time = "0";
                    }
                    
                    
                	String stMon = st_Time;
                	String otMon = ot_Time;
                	String dtMon = "0";
                	
                	
                	Set<String> holidaySet = new HashSet<>();
                 
                    for(String holiday : dt_Dates) {
                        holidaySet.add(holiday);
                    }

                  
                    if(holidaySet.contains(date)) {
                    	dtMon = String.valueOf(hoursWorked);
                    	
                    	stMon = "0";
                    	otMon = "0";
                    	
                        totalSt = "0";
                    	totalOt = "0";
                    	totalDt = String.valueOf(hoursWorked);
                    } 
                    
                    totalSt = stMon;
                	totalOt = otMon;
                	totalDt = dtMon;
                	
                	String startTimeMon = StartTime;
                	String endTimeMon = EndTime;

                    PDTextField fieldMon = (PDTextField) acroForm.getField("DATE-THU");
                    fieldMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    fieldMon.setValue(dayNumber);
                    
                    PDTextField field_ST_Mon = (PDTextField) acroForm.getField("ST - THU");
                    field_ST_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_ST_Mon.setValue(stMon);
                    
                    PDTextField field_OT_Mon = (PDTextField) acroForm.getField("OT - THU");
                    field_OT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_OT_Mon.setValue(otMon);
                    
                    PDTextField field_DT_Mon = (PDTextField) acroForm.getField("DT - THU");
                    field_DT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_DT_Mon.setValue(dtMon);
                    
                    PDTextField field_StartTimeMon = (PDTextField) acroForm.getField("START TIME - THU");
                    field_StartTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_StartTimeMon.setValue(startTimeMon);
                    
                    PDTextField field_EndTimeMon = (PDTextField) acroForm.getField("END TIME - THU");
                    field_EndTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_EndTimeMon.setValue(endTimeMon);
                    
                    PDTextField field_Total_ST = (PDTextField) acroForm.getField("ST");
                    field_Total_ST.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_ST.setValue(totalSt);
                    
                    PDTextField field_Total_OT = (PDTextField) acroForm.getField("OT");
                    field_Total_OT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_OT.setValue(totalOt);
                    
                    PDTextField field_Total_DT = (PDTextField) acroForm.getField("DT");
                    field_Total_DT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_DT.setValue(totalDt);
                    
                    if(!ProjectName.equalsIgnoreCase("")){
                    document.save("/Users/syamsalik/Developer/GREEN SHEET/ " + name + " - " + date + ".pdf");
                    }
                    
                } 
                
                
                if(dayOfWeek.equalsIgnoreCase("Fri")) {
                	
                    
                    String st_Time = "";
                    String ot_Time = "";
                    
                     
                    
                  
                    if (hoursWorked > 10) {
                        st_Time = "10";
                        
                        double overtime = hoursWorked - 10;
                       
                        ot_Time = String.valueOf(overtime);
                    } else {
                        st_Time = String.valueOf(hoursWorked);
                        ot_Time = "0";
                    }
                    
                    
                	String stMon = st_Time;
                	String otMon = ot_Time;
                	String dtMon = "0";
                	
                	
                	Set<String> holidaySet = new HashSet<>();
                   
                    for(String holiday : dt_Dates) {
                        holidaySet.add(holiday);
                    }

                 
                    if(holidaySet.contains(date)) {
                    	dtMon = String.valueOf(hoursWorked);
                    	
                    	stMon = "0";
                    	otMon = "0";
                    	
                        totalSt = "0";
                    	totalOt = "0";
                    	totalDt = String.valueOf(hoursWorked);
                    } 
                    
                    totalSt = stMon;
                	totalOt = otMon;
                	totalDt = dtMon;
                	
                	String startTimeMon = StartTime;
                	String endTimeMon = EndTime;

                    PDTextField fieldMon = (PDTextField) acroForm.getField("DATE-FRI");
                    fieldMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    fieldMon.setValue(dayNumber);
                    
                    PDTextField field_ST_Mon = (PDTextField) acroForm.getField("ST - FRI");
                    field_ST_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_ST_Mon.setValue(stMon);
                    
                    PDTextField field_OT_Mon = (PDTextField) acroForm.getField("OT - FRI");
                    field_OT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_OT_Mon.setValue(otMon);
                    
                    PDTextField field_DT_Mon = (PDTextField) acroForm.getField("DT - FRI");
                    field_DT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_DT_Mon.setValue(dtMon);
                    
                    PDTextField field_StartTimeMon = (PDTextField) acroForm.getField("START TIME - FRI");
                    field_StartTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_StartTimeMon.setValue(startTimeMon);
                    
                    PDTextField field_EndTimeMon = (PDTextField) acroForm.getField("END TIME - FRI");
                    field_EndTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_EndTimeMon.setValue(endTimeMon);
                    
                    PDTextField field_Total_ST = (PDTextField) acroForm.getField("ST");
                    field_Total_ST.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_ST.setValue(totalSt);
                    
                    PDTextField field_Total_OT = (PDTextField) acroForm.getField("OT");
                    field_Total_OT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_OT.setValue(totalOt);
                    
                    PDTextField field_Total_DT = (PDTextField) acroForm.getField("DT");
                    field_Total_DT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_DT.setValue(totalDt);
                    
                    if(!ProjectName.equalsIgnoreCase("")){
                    document.save("/Users/syamsalik/Developer/GREEN SHEET/ " + name + " - " + date + ".pdf");
                    }
                    
                } 
                
                if(dayOfWeek.equalsIgnoreCase("Sat")) {
                	
                  
                    String st_Time = "";
                    String ot_Time = "";
                    
                     
                    
                  
                    if (hoursWorked > 8) {
                        st_Time = "8";
                     
                        double overtime = hoursWorked - 8;
                      
                        ot_Time = String.valueOf(overtime);
                    } else {
                        st_Time = String.valueOf(hoursWorked);
                        ot_Time = "0";
                    }
                    
                    
                	String stMon = "0";
                	String otMon = String.valueOf(hoursWorked);
                	String dtMon = "0";
                	
                	
                	Set<String> holidaySet = new HashSet<>();
                
                    for(String holiday : dt_Dates) {
                        holidaySet.add(holiday);
                    }

               
                    if(holidaySet.contains(date)) {
                    	dtMon = String.valueOf(hoursWorked);
                    	
                    	stMon = "0";
                    	otMon = "0";
                    	
                        totalSt = "0";
                    	totalOt = "0";
                    	totalDt = String.valueOf(hoursWorked);
                    } 
                    
                    totalSt = stMon;
                	totalOt = otMon;
                	totalDt = dtMon;
                	
                	String startTimeMon = StartTime;
                	String endTimeMon = EndTime;

                    PDTextField fieldMon = (PDTextField) acroForm.getField("DATE-SAT");
                    fieldMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    fieldMon.setValue(dayNumber);
                    
                    PDTextField field_ST_Mon = (PDTextField) acroForm.getField("ST - SAT");
                    field_ST_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_ST_Mon.setValue(stMon);
                    
                    PDTextField field_OT_Mon = (PDTextField) acroForm.getField("OT - SAT");
                    field_OT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_OT_Mon.setValue(otMon);
                    
                    PDTextField field_DT_Mon = (PDTextField) acroForm.getField("DT - SAT");
                    field_DT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_DT_Mon.setValue(dtMon);
                    
                    PDTextField field_StartTimeMon = (PDTextField) acroForm.getField("START TIME - SAT");
                    field_StartTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_StartTimeMon.setValue(startTimeMon);
                    
                    PDTextField field_EndTimeMon = (PDTextField) acroForm.getField("END TIME - SAT");
                    field_EndTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_EndTimeMon.setValue(endTimeMon);
                    
                    PDTextField field_Total_ST = (PDTextField) acroForm.getField("ST");
                    field_Total_ST.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_ST.setValue(totalSt);
                    
                    PDTextField field_Total_OT = (PDTextField) acroForm.getField("OT");
                    field_Total_OT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_OT.setValue(totalOt);
                    
                    PDTextField field_Total_DT = (PDTextField) acroForm.getField("DT");
                    field_Total_DT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_DT.setValue(totalDt);
                    
                    if(!ProjectName.equalsIgnoreCase("")){
                    document.save("/Users/syamsalik/Developer/GREEN SHEET/ " + name + " - " + date + ".pdf");
                    }
                    
                } 
 				
                if(dayOfWeek.equalsIgnoreCase("Sun")) {
                	
                 
                    String st_Time = "";
                    String ot_Time = "";
                    
                     
                    
        
                    if (hoursWorked > 8) {
                        st_Time = "8";
               
                        double overtime = hoursWorked - 8;
                        
                        ot_Time = String.valueOf(overtime);
                    } else {
                        st_Time = String.valueOf(hoursWorked);
                        ot_Time = "0";
                    }
                    
                    
                	String stMon = "0";
                	String otMon = String.valueOf(hoursWorked);
                	String dtMon = "0";
                	
                	
                	Set<String> holidaySet = new HashSet<>();
                  
                    for(String holiday : dt_Dates) {
                        holidaySet.add(holiday);
                    }

            
                    if(holidaySet.contains(date)) {
                    	dtMon = String.valueOf(hoursWorked);
                    	
                    	stMon = "0";
                    	otMon = "0";
                    	
                        totalSt = "0";
                    	totalOt = "0";
                    	totalDt = String.valueOf(hoursWorked);
                    } 
                    
                    totalSt = stMon;
                	totalOt = otMon;
                	totalDt = dtMon;
                	
                	String startTimeMon = StartTime;
                	String endTimeMon = EndTime;

                    PDTextField fieldMon = (PDTextField) acroForm.getField("DATE-SUN");
                    fieldMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    fieldMon.setValue(dayNumber);
                    
                    PDTextField field_ST_Mon = (PDTextField) acroForm.getField("ST - SUN");
                    field_ST_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_ST_Mon.setValue(stMon);
                    
                    PDTextField field_OT_Mon = (PDTextField) acroForm.getField("OT - SUN");
                    field_OT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_OT_Mon.setValue(otMon);
                    
                    PDTextField field_DT_Mon = (PDTextField) acroForm.getField("DT - SUN");
                    field_DT_Mon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_DT_Mon.setValue(dtMon);
                    
                    PDTextField field_StartTimeMon = (PDTextField) acroForm.getField("START TIME - SUN");
                    field_StartTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_StartTimeMon.setValue(startTimeMon);
                    
                    PDTextField field_EndTimeMon = (PDTextField) acroForm.getField("END TIME - SUN");
                    field_EndTimeMon.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_EndTimeMon.setValue(endTimeMon);
                    
                    PDTextField field_Total_ST = (PDTextField) acroForm.getField("ST");
                    field_Total_ST.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_ST.setValue(totalSt);
                    
                    PDTextField field_Total_OT = (PDTextField) acroForm.getField("OT");
                    field_Total_OT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_OT.setValue(totalOt);
                    
                    PDTextField field_Total_DT = (PDTextField) acroForm.getField("DT");
                    field_Total_DT.setDefaultAppearance(((PDTextField)acroForm.getFields().get(0)).getDefaultAppearance());
                    field_Total_DT.setValue(totalDt);
                    
                    if(!ProjectName.equalsIgnoreCase("")){
                    document.save("/Users/syamsalik/Developer/GREEN SHEET/ " + name + " - " + date + ".pdf");
                    }
                    
                } 
 
                name = "";
                dayOfWeek = "";
                date = "";
                month = "";
                year = "";
                StartTime = "";
                EndTime = "";
                hoursWorked = 0.0;
                Subdivision = "";
                protection = "";


                document.close();
            }
            
 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	
    	
    }
    
    public static void printFieldNames(PDDocument document) {
        PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
        
        if (acroForm != null) {
            List<PDField> fields = acroForm.getFields();
            System.out.println("Fields in the document:");
            for (PDField field : fields) {
               
                System.out.println(field.getFullyQualifiedName() + " - Type: " + field.getClass().getSimpleName());
            }
        } else {
            System.out.println("No AcroForm found in the document.");
        }
    }
    
    public static void ScheduleAccess(String filePath) {
    	
    	BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
            	
            	if (currentLine.trim().equals("---------------------------------------")) {
                    continue; // Skip this line and continue to the next one
                }
                if (currentLine.trim().equals("")) {
                    continue; // Skip this line and continue to the next one
                }
                if (currentLine.trim().equals(" ")) {
                    continue; // Skip this line and continue to the next one
                }
                if (currentLine.trim().equals("\n")) {
                    continue; // Skip this line and continue to the next one
                }

                Clean(currentLine);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void Clean(String txt) {

       
        String[] content = txt.split("\\|+"); 

        String date = content[0];
        
        String StartTime = content[1];
        String EndTime = content[2];
        String Subdivision = SubdivisionExtractor(content[3]);
        String zone = ZoneExtractor(Subdivision);

        
        System.out.print("Date: " + date);
        String dayOfWeek = getDayOfWeek(date);
        String monthName = getMonth(date);
        String year = getYear(date);
        String dayNumber = getDayNumber(date);
        
        // Calculate the difference in hours
        double hoursWorked = calculateHoursApart(StartTime.replace(" ", ""), EndTime.replace(" ", ""));
        
        String projectNumber = ProjectNumberExtractor(content);
        String ProjectName = ProjectNameExtractor(content);
        String WorkingLimits = WorkingLimitsExtractor(content);
        String StartLimit = StartLimitExtractor(content);
        String EndLimit = EndLimitExtractor(content);
        String protection = protectionExtractor(content);
        String[][] contractor = processConsultants(content);

        
        GreenSheetGenerator(
        		"Syam Salik",
        		dayOfWeek,
        		dayNumber,
        		date,
        		monthName,
        		year,
        		StartTime,
        		EndTime,
        		hoursWorked,
        		Subdivision,
        		projectNumber,
        		ProjectName,
        		WorkingLimits,
        		StartLimit,
        		EndLimit,
        		zone,
        		protection,
        		contractor
        		);
        
    }
    
    public static String getDayOfWeek(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter)
                                        .atStartOfDay(ZoneId.of("America/Toronto"))
                                        .toLocalDate();
        String dayOfWeek = localDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
        return dayOfWeek;
    }
    
    public static String getDayNumber(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter)
                                        .atStartOfDay(ZoneId.of("America/Toronto"))
                                        .toLocalDate();
        return String.format("%02d", localDate.getDayOfMonth());
    }

    public static String getMonth(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter)
                                        .atStartOfDay(ZoneId.of("America/Toronto"))
                                        .toLocalDate();
        String month = localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US);
        return month;
    }
    
    public static String getYear(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter)
                                        .atStartOfDay(ZoneId.of("America/Toronto"))
                                        .toLocalDate();
        String year = String.valueOf(localDate.getYear());
        return year;
    }
    
    public static double calculateHoursApart(String startTime, String endTime) {
        // Parse the start and end times
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        // Calculate the duration in minutes, considering the possibility of crossing midnight
        long durationMinutes;
        if (end.isBefore(start)) {
            // Correctly calculate the time from start to the end of the day, plus from the beginning of the day to the end
            durationMinutes = ChronoUnit.MINUTES.between(start, LocalTime.MAX) + 1 + ChronoUnit.MINUTES.between(LocalTime.MIN, end);
        } else {
            // Directly calculate the duration if it doesn't cross midnight
            durationMinutes = ChronoUnit.MINUTES.between(start, end);
        }

        // Convert minutes to hours as a floating point number for accuracy
        return durationMinutes / 60.0;
    }

    public static String ProjectNumberExtractor(String[] array ) {
		

        // Variables to hold the extracted data
        String projectNumber = "";
        String projectName = "";

        // Loop through the array to find the string that starts with "Project Name:"
        for (String item : array) {
            if (item.startsWith("Project Name:")) {
                // Extract the substring after "Project Name: "
                String relevantPart = item.substring("Project Name: ".length());

                // Split the extracted string by " -- "
                String[] parts = relevantPart.split(" -- ");

                // Check if the split was successful
                if (parts.length == 2) {
                    projectNumber = parts[0];
                    projectName = parts[1];
                }

                break; // Exit the loop as we found our string
            }
        }


        
        return projectNumber;
        

    	
    }
    
    public static String ProjectNameExtractor(String[] array ) {
		

        // Variables to hold the extracted data
        String projectNumber = "";
        String projectName = "";

        // Loop through the array to find the string that starts with "Project Name:"
        for (String item : array) {
            if (item.startsWith("Project Name:")) {
                // Extract the substring after "Project Name: "
                String relevantPart = item.substring("Project Name: ".length());

                // Split the extracted string by " -- "
                String[] parts = relevantPart.split(" -- ");

                // Check if the split was successful
                if (parts.length == 2) {
                    projectNumber = parts[0];
                    projectName = parts[1];
                }

                break; // Exit the loop as we found our string
            }
        }


        return projectName;
        

    	
    }
    
    public static String WorkingLimitsExtractor(String[] array ) {
		

        // Variables to hold the extracted data
        String projectNumber = "";
        String projectName = "";
        String relevantPart = "";

        // Loop through the array to find the string that starts with "Project Name:"
        for (String item : array) {
            if (item.startsWith("Working Limits:")) {
                // Extract the substring after "Project Name: "
                relevantPart = item.substring("Working Limits: ".length());

                // Split the extracted string by " -- "
                String[] parts = relevantPart.split(" - ");

                // Check if the split was successful
                if (parts.length == 2) {
                    projectNumber = parts[0];
                    projectName = parts[1];
                }

                break; // Exit the loop as we found our string
            }
        }


        return relevantPart;
        

    	
    }
    
    public static String StartLimitExtractor(String[] array ) {
		

        // Variables to hold the extracted data
        String startLimit = "";
        String endLimit = "";

        // Loop through the array to find the string that starts with "Project Name:"
        for (String item : array) {
            if (item.startsWith("Working Limits:")) {
                // Extract the substring after "Project Name: "
                String relevantPart = item.substring("Working Limits: ".length());

                // Split the extracted string by " -- "
                String[] parts = relevantPart.split("-");

                // Check if the split was successful
                if (parts.length == 2) {
                    startLimit = parts[0];
                    endLimit = parts[1];
                }

                break; // Exit the loop as we found our string
            }
        }
        return  startLimit;

    }

    public static String EndLimitExtractor(String[] array ) {
		

        // Variables to hold the extracted data
        String startLimit = "";
        String endLimit = "";

        // Loop through the array to find the string that starts with "Project Name:"
        for (String item : array) {
            if (item.startsWith("Working Limits:")) {
                // Extract the substring after "Project Name: "
                String relevantPart = item.substring("Working Limits: ".length());

                // Split the extracted string by " -- "
                String[] parts = relevantPart.split("-");

                // Check if the split was successful
                if (parts.length == 2) {
                    startLimit = parts[0];
                    endLimit = parts[1];
                }

                break; // Exit the loop as we found our string
            }
        }
        return  endLimit;

    }
   
    public static String ZoneExtractor(String name) {
        // Lists of words to compare against
        List<String> listA = Arrays.asList("Oakville", "Pearson", "Bala", "Newmarket", "Weston");
        List<String> listB = Arrays.asList("Uxbridge", "GO", "Kingston"); 
        
        // Splitting the input string into individual words
        String[] words = name.split("\\s+"); // Splits by whitespace
        
        // Checking each word in the input string
        for (String word : words) {
            // If the word is found in List A
            if (listA.contains(word)) {
            	System.out.println("Zone A");
                return "A";
            }
            // If the word is found in List B
            else if (listB.contains(word)) {
            	System.out.println("Zone B");
                return "B";
            }
        }
        System.out.println("Zone NULL");
        return "";
    }
    
    public static String SubdivisionExtractor(String input) {
    	
    	List<String> PLACE_NAMES = Arrays.asList("Kingston", "Bala", "Newmarket", "Pearson", "Weston", "Oakville", "Uxbridge", "Kingston", "GO");

    	for (String place : PLACE_NAMES) {
            // 
            String[] words = input.split("\\s+");
            for (String word : words) {
                // 
                if (word.equalsIgnoreCase(place)) {
                    
                    return place;
                }
            }
        }

        return input;
    }
    
    public static String protectionExtractor(String[] array ) {
		
        String protection = "";

        for (String item : array) {
            if (item.startsWith("Protection Type:")) {
                String relevantPart = item.substring("Working Limits: ".length());
                protection = relevantPart;
                break; 
            }
        }
        
        if (protection.equalsIgnoreCase(" Sub-Foreman")) {
        	protection = "R842";
            return  protection;
        } else if (protection.equalsIgnoreCase(" CN 842")) {
        	protection = "R842";
            return  protection;
        } else if (protection.equalsIgnoreCase(" CN TOP")) {
        	protection = "TOP";
            return  protection;
        }

        return "";

    }

    public static String[][] processConsultants(String[] array) {
        String[][] results = new String[array.length][];
        int i = 0;

        for (String item : array) {
            if (item.startsWith("Consultant-Contractor:")) {
                // Remove the prefix to only work with the name and phone number
                item = item.replace("Consultant-Contractor: ", "");

                // Find the last space character's index to separate the name and phone number
                int lastSpaceIndex = item.lastIndexOf(" ");
                if (lastSpaceIndex > 0) { // Make sure there is at least one space
                    String name = item.substring(0, lastSpaceIndex);
                    String phoneNumber = item.substring(lastSpaceIndex + 1);
                    results[i++] = new String[]{name, phoneNumber};
                } else {
                    // Handle the case where the format is incorrect, and there is no space
                    results[i++] = new String[]{"Invalid format", "Invalid format"};
                }
            }
        }

        // Optionally, resize the results array to remove null elements
        return java.util.Arrays.copyOf(results, i);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
