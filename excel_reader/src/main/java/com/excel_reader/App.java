package com.excel_reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.entity.categuary;
import com.entity.company_info;

public class App {

/*
  public void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  public int partition(int[] nums, int start, int end) {
    int key = nums[start];
    int i = start + 1;
    for (int k = start + 1; k <= end ; k++) {
      if (nums[k] <= key) {
        if (i != k)
          swap(nums, i, k);
        i++;
      }
    }
    if (start != i - 1)
      swap(nums,start ,i-1);
    return i-1;
  }

  public void quicksort(int[] nums, int start, int end) {
    if (start <= end) {
      int index = partition(nums, start, end);
      quicksort(nums, start, index - 1);
      quicksort(nums, index + 1, end);
    }
  }

  public void quicksort(int[] nums) {
    quicksort(nums, 0, nums.length - 1);
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5, 4, 7, 0};
    new App().quicksort(nums);
    System.out.println("nums = " + Arrays.toString(nums));
  }
*/
	public static void main(String args[]) throws IOException, InvalidFormatException  
	{  
	//obtaining input bytes from a file  
		String name_title = null;
		String tel1 = null;
		String email = null;
		String website = null;
		String mobil1 = null;
		String mobil2 = null;
		String mobil3 = null;
		String mobil4 = null;
		String tel2 = null;
		String mobil5 = null;
		String location=null;
		String categury=null;
		
		OPCPackage pkg = OPCPackage.open(new File("E:\\A\\company2sales.xlsx"));
		ArrayList<String> column_letters=new ArrayList<String>();
		
		
	//FileInputStream fis=new FileInputStream(new File("E:\\A\\company-sales.xlsx"));  
	//creating workbook instance that refers to .xls file  
	//HSSFWorkbook wb=new HSSFWorkbook(pkg);   
	XSSFWorkbook wb = new XSSFWorkbook(pkg);
	//creating a Sheet object to retrieve the object  
	XSSFSheet sheet=wb.getSheetAt(0);  
	//evaluating cell type   
	FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();  
	int columnIndex=0,cellIndex=0;String cat_title=""; 
	
	
			for(Row row: sheet)     //iteration over row using for each loop  
			{
				column_letters.clear();
				for(Cell cell: row)    //iteration over cell using for each loop  
				{  
					String column_letter = CellReference.convertNumToColString(cell.getColumnIndex());
					System.out.print(column_letter + "   \t\t");  
					
					
					column_letters.add(column_letter);
					
					
				switch(cell.getCellType())  
					{  
					case  NUMERIC:   //field that represents numeric cell type  
					//getting the value of the cell as a number  
					System.out.print("0"+String.valueOf( Long.valueOf((long) cell.getNumericCellValue()))+ "   \t\t");   
					break;  
					case STRING:    //field that represents string cell type  
					//getting the value of the cell as a string  
						cat_title=cell.getStringCellValue();
						System.out.print(cell.getStringCellValue()+ "\t\t");  
					break;  
				 
				default:
					System.out.print(cell.getStringCellValue()+ "Hassan Ali Hassan\t\t");  
					break; 
					} 
				
				if(column_letter.equals("B"))
						name_title=cell.getStringCellValue();
				if(column_letter.equals("C")) {
					try {
					tel1=cell.getStringCellValue();
					}catch(Exception e) {
						tel1=String.valueOf( Long.valueOf((long) cell.getNumericCellValue()));
					}}
				if(column_letter.equals("D"))
					tel2=cell.getStringCellValue();
				if(column_letter.equals("E"))
					mobil1="0"+String.valueOf( Long.valueOf((long) cell.getNumericCellValue()));
				if(column_letter.equals("F"))
					email=cell.getStringCellValue();
				if(column_letter.equals("H"))
					mobil3="0"+String.valueOf( Long.valueOf((long) cell.getNumericCellValue()));
				if(column_letter.equals("I"))
					mobil4="0"+String.valueOf( Long.valueOf((long) cell.getNumericCellValue()));
				if(column_letter.equals("J"))
					mobil5="0"+String.valueOf( Long.valueOf((long) cell.getNumericCellValue()));
				if(column_letter.equals("L"))
					location=cell.getStringCellValue();
				  
				
				}  
				
				if(column_letters.size()>1) {
					company_info COMAPNY=new company_info.Builder().setName_title(name_title).setEmail(email).setMobil1(mobil1).setMobil2(mobil2)
							.setMobil3(mobil3).setMobil4(mobil4).setMobil5(mobil5).setTel1(tel1)
							.setTel2(tel2).setWebsite(name_title).setLocation(location).setCategury(categury).build();
					COMAPNY.save();
					  name_title = "";
					  tel1 = "";
					  email = "";
					  website = "";
					  mobil1 = "";
					  mobil2 = "";
					  mobil3 = "";
					  mobil4 = "";
					  tel2 = "";
					  mobil5 = "";
					  location="";
					  //categury="";
				}
				
				  if(column_letters.size()<=1) {
				  System.out.println(column_letters.size()+"it looks categuary"); categuary
				  cat_=new categuary.Builder().build(cat_title); cat_.save(); 
				  categury=cat_title;
				  }
				 
				
				
				
				System.out.println();
				columnIndex++;
			}  
	}  
	
}
