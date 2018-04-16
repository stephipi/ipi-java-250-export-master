package com.example.demo.service.export;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;

@Service
public class ExportCSVService {
    
	public void export(Writer printWriter, List<ClientDTO> Clients) throws IOException
	{
		printWriter.write("NOM;PRENOM;ADRESSE;TEL;\n");
		for(int i = 0 ; i < Clients.size() ; i++)
		{
			printWriter.write(Clients.get(i).getNom()+";");//NOM
			printWriter.write(Clients.get(i).getPrenom());//PRENOM
			//ADRESSE
			//TEL
			printWriter.write("\n");//HashMap
		}
	}
}
