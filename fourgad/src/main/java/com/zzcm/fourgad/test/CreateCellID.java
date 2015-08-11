package com.zzcm.fourgad.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

public class CreateCellID {

	public static void main(String[] args) throws Exception {
		Reader read = new FileReader("D://jizhaTest.txt");
		BufferedReader br = new BufferedReader(read);
		FileOutputStream fos=new FileOutputStream(new File("D://jizhaTest.csv"));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
		String line = null;
		String arr[] = null;
		while((line = br.readLine()) != null){
			arr = line.split("x");
			if(!arr[2].equals("-1")){
				bw.write(arr[2]+","+arr[0]+","+arr[1]+"\t\n");
			}
		}
		bw.close();
        osw.close();
        fos.close();
		br.close();
		read.close();
	}
}
