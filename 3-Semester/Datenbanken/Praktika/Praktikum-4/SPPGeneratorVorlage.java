
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.GregorianCalendar; 

import javax.swing.*;

public class SPPGeneratorVorlage {

	private File databaseFile;
	private Connection conn = null;
	private ArrayList<String> courseNames;
	private String url = "jdbc:sqlite:";
	public static boolean programmBeenden;
	
	private String alleVeranstaltungen = "Alle Veranstaltungen ausgeben";
	private String stringBeenden = "Ende";

	private String querySVP = "SElECT * FROM modul join modul_instanz on modul.id = modul_instanz.mod_id WHERE modul_instanz.studiengang=? order by modulnummer";
	private String queryModule = "SELECT * FROM modul, pruefungsart";
	private String queryAlleStudiengaenge = "SELECT * FROM studiengang;";
	
	private String htmlTableStart = "<html><head>\r\n"
			+ "<style>\n"
			+ "table, th, td {\n"
			+ "  border: 1px solid black; font-size: 10px; font-family: sans-serif; border-collapse: collapse;\n"
			+ "}\n"
			+ "\n"
			+ "th, td {\n"
			+ "  padding: 10px;\n"
			+ "}\n"
			+ "</style>\n"
			+ "</head>\n<body>\n<table>\n";
	private String htmlTableEnd = "</table>\n</body>\n</html>\n";
	private String tagRow = "<tr>\n";
	private String tagRowEnd = "</tr>\n";
	private String tagData = "<td align=\"center\">";
	private String tagDataRed = "<td align=\"center\", style=\"color:red;\">";
	private String tagDataEnd = "</td>\n";
	private String tagRowStart = "<tr><td align=\"center\", colspan=\"12\">Studienverlaufsplan ";
	private String tagHead = "<th>";
	private String tagHeadEnd = "</th>";
	
	
	public File openDatabaseDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Bitte Datenbank zum Öffnen auswählen...");
		int choice = fileChooser.showOpenDialog(null);
		if(choice == JFileChooser.APPROVE_OPTION) {
			try {
				databaseFile = fileChooser.getSelectedFile();
	            conn = DriverManager.getConnection(url+databaseFile.getAbsolutePath());            
	            //System.out.println("Connection to DB has been established.");
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	public ArrayList<String> loadCourses() {
		ArrayList<String> names = new ArrayList<String>();
		try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryAlleStudiengaenge)) {

			
			// Hier Ihr Code
			// ResultSet auslesen und die Namen der Studiengänge in die ArrayList names eintragen

			// ...
			while (rs.next()) {
				String studiengangName = rs.getString("name");
				names.add(studiengangName);
			}

			System.out.println("Studiengänge:");
			for (String name : names) {
				System.out.println(name);
			}
			
            	 
            	 
			} catch (SQLException e) {
            System.err.println(e.getMessage());
        }
		return names;
	}
	
	public SPPGeneratorVorlage() {
		programmBeenden = false;
		databaseFile = openDatabaseDialog();
		if (databaseFile != null) {
			courseNames = loadCourses();
			for (int i = 0; i < courseNames.size(); i++) {
				System.out.println(courseNames.get(i));
			}
		}
	}
	
	public void doDialog() {
		if (databaseFile != null) {
			ArrayList<String> options = new ArrayList<String>(courseNames);
			options.add(alleVeranstaltungen);
			options.add(stringBeenden);
			
			String s = (String)JOptionPane.showInputDialog(
	                null,
	                "Funktion auswählen...",
	                "Welche Ausgabe möchten Sie generieren?",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                options.toArray(),
	                null);
			if (s != null) {
				if (s.equals(stringBeenden)) {
					programmBeenden = true;
				} else
				if (s.equals(alleVeranstaltungen)) {
					generateAllModules_HTML();
					System.out.println("Alle Module ausgeben beendet.");
				} else {
					// Studienverlaufsplan erstellen
					generateSPP_HTML(s);
					System.out.println("Studienverlaufsplan ausgeben beendet.");
				}
			}
		}
		else {
			programmBeenden = true;
			System.out.println("Program ends because no database file selected.");
		}
	}
	
	
	private void generateAllModules_HTML() {
		JFileChooser chooser = new JFileChooser();	
	    chooser.showSaveDialog(null);
	    File saveFile = chooser.getSelectedFile();
	    
		try {
			boolean fileCreated =  saveFile.createNewFile();
			
			if (fileCreated) {
				FileWriter fw = new FileWriter(saveFile);
				// Daten aus der Datenbank holen mit PreparedStatement
				PreparedStatement pstmt = conn.prepareStatement(queryModule);			
				ResultSet rs = pstmt.executeQuery();
				GregorianCalendar now = new GregorianCalendar(); 
				
				fw.write(htmlTableStart);
				fw.write(tagRowStart + "Alle Module, Stand: " + now.getTime() +  tagRowEnd);
				fw.write(tagRow);
				fw.write(tagHead + "Modulname DE" + tagHeadEnd);
				fw.write(tagHead + "Modulname EN" + tagHeadEnd);
				fw.write(tagHead + "Lehrform" + tagHeadEnd);
				fw.write(tagHead + "Online asynchron %" + tagHeadEnd);
				fw.write(tagHead + "Prüfungsart" + tagHeadEnd);
				fw.write(tagHead + "Prüfungsdauer" + tagHeadEnd);
				fw.write(tagRowEnd);

				// Hier Ihr Code
				// ResultSet auslesen und die Spalten im HTML ausfüllen
		
				// ...
				while (rs.next()) {
					fw.write(tagRow); // Neue Tabellenzeile starten
					fw.write(tagData + rs.getString("name_de") + tagDataEnd);
					fw.write(tagData + rs.getString("name_en") + tagDataEnd);
					fw.write(tagData + rs.getString("form") + tagDataEnd);
					fw.write(tagData + rs.getString("kuerzel") + tagDataEnd);
					fw.write(tagData + rs.getString("online_async") + tagDataEnd);
					fw.write(tagData + rs.getString("PruefArt") + tagDataEnd);
					fw.write(tagData + rs.getString("PruefDauer") + tagDataEnd);
					fw.write(tagRowEnd); // Tabellenzeile schließen
				}
				
				fw.write(htmlTableEnd);
				fw.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void generateSPP_HTML(String s) {
		JFileChooser chooser = new JFileChooser();
	    //chooser.setApproveButtonText("Run Application");
	    chooser.showSaveDialog(null);
	    File saveFile = chooser.getSelectedFile();
	    
		try {
			boolean fileCreated =  saveFile.createNewFile();
			
			if (fileCreated) {
				FileWriter fw = new FileWriter(saveFile);
				// Daten aus der Datenbank holen mit PreparedStatement
				PreparedStatement pstmt = conn.prepareStatement(querySVP);
				
				pstmt.setString(1, s);
				ResultSet rs = pstmt.executeQuery();
				GregorianCalendar now = new GregorianCalendar(); 
				
				fw.write(htmlTableStart);
				fw.write(tagRowStart + s + " / Stand: " + now.getTime() +  tagRowEnd);
				fw.write(tagRow);
				fw.write(tagHead + "Modul" + tagHeadEnd);
				fw.write(tagHead + "Modulname" + tagHeadEnd);
				fw.write(tagHead + "Modulname english" + tagHeadEnd);
				fw.write(tagHead + "Art" + tagHeadEnd);
				fw.write(tagHead + "Form" + tagHeadEnd);
				fw.write(tagHead + "Online async." + tagHeadEnd);
				fw.write(tagHead + "Prüfung" + tagHeadEnd);
				fw.write(tagHead + "Dauer Prüfung" + tagHeadEnd);
				fw.write(tagHead + "Notengewicht" + tagHeadEnd);
				fw.write(tagHead + "Semester" + tagHeadEnd);
				fw.write(tagHead + "SWS" + tagHeadEnd);
				fw.write(tagHead + "ECTS" + tagHeadEnd);
				fw.write(tagRowEnd);
				
				// Hier Ihr Code
				// ResultSet auslesen und Daten in HTML eintragen
				
				// ... 
				while (rs.next()) {
					fw.write(tagRow); // Neue Tabellenzeile starten
					fw.write(tagData + rs.getString("modulnummer") + tagDataEnd);
					fw.write(tagData + rs.getString("name_de") + tagDataEnd);
					fw.write(tagData + rs.getString("name_en") + tagDataEnd);
					fw.write(tagData + rs.getString("teilmodul_von") + tagDataEnd);
					fw.write(tagData + rs.getString("form") + tagDataEnd);
					fw.write(tagData + rs.getString("online_async") + tagDataEnd);
					fw.write(tagData + rs.getString("PruefArt") + tagDataEnd);
					fw.write(tagData + rs.getString("PruefDauer") + tagDataEnd);
					fw.write(tagData + rs.getString("notengewicht") + tagDataEnd);
					fw.write(tagData + rs.getString("pruef_sem") + tagDataEnd);
					fw.write(tagData + rs.getString("sws") + tagDataEnd);
					fw.write(tagData + rs.getString("ects") + tagDataEnd);
					fw.write(tagRowEnd); // Tabellenzeile schließen
				}
				
				
				fw.write(htmlTableEnd);
				fw.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SPPGeneratorVorlage sg = new SPPGeneratorVorlage();
		while (!programmBeenden) {
			sg.doDialog();
		}
	}

}
