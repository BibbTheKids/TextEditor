import java.util.Scanner;
import java.util.Stack;

class TextEditor {
    private StringBuilder currentText;
    private final Stack<String> undoStack;
    private final Stack<String> redoStack;

    public TextEditor() {
        currentText = new StringBuilder();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void show() {
        System.out.println("Teks saat ini: " + currentText.toString());
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText.toString());
            currentText = new StringBuilder(undoStack.pop());
            System.out.println("Undo berhasil.");
        } else {
            System.out.println("Tidak ada perubahan untuk di-undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText.toString());
            currentText = new StringBuilder(redoStack.pop());
            System.out.println("Redo berhasil.");
        } else {
            System.out.println("Tidak ada perubahan untuk di-redo.");
        }
    }

    public void write(String text) {
        undoStack.push(currentText.toString());
        currentText.append(text);
        redoStack.clear(); // Hapus redo stack setelah melakukan penulisan
        System.out.println("Teks ditambahkan: " + text);
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Show Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Write Text");
            System.out.println("5. Exit");

            System.out.print("Pilih opsi (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Mengonsumsi newline

            switch (choice) {
                case 1:
                    textEditor.show();
                    break;
                case 2:
                    textEditor.undo();
                    break;
                case 3:
                    textEditor.redo();
                    break;
                case 4:
                    System.out.print("Masukkan teks: ");
                    String text = scanner.nextLine();
                    textEditor.write(text);
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opsi tidak valid, silakan coba lagi.");
            }
        }
    }
}
