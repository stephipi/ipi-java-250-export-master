package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class ExportCSVService {

    public void export(Writer printWriter, List<ClientDTO> clients) throws IOException {
        printWriter.write("Nom;");
        printWriter.write("Prenom;");
        printWriter.write("\n");
        
        for (ClientDTO client : clients) {
            printWriter.write(client.getNom());
            printWriter.write(";");
            printWriter.write(client.getPrenom());
            printWriter.write("\n");
        }

    }
}
