import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    // --- Append a new note to the file ---
    private static void addNote(String note) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(note);
            System.out.println("✅ Note added successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    // --- Read all notes from the file ---
    private static void readNotes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No notes found yet!");
            return;
        }

        System.out.println("\n---- Your Notes ----");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(count++ + ". " + line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }

    // --- Clear all notes (optional feature) ---
    private static void clearNotes() {
        try (FileWriter fw = new FileWriter(FILE_NAME, false)) { // false = overwrite
            // just overwrite with empty content
            System.out.println("✅ All notes cleared.");
        } catch (IOException e) {
            System.out.println("❌ Error clearing notes: " + e.getMessage());
        }
    }

    // --- Main Menu ---
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Notes App Menu ---");
            System.out.println("1. Add a new note");
            System.out.println("2. View all notes");
            System.out.println("3. Clear all notes");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your note: ");
                    String note = sc.nextLine();
                    addNote(note);
                }
                case 2 -> readNotes();
                case 3 -> clearNotes();
                case 4 -> {
                    System.out.println("Exiting Notes App. Bye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
