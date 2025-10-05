import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileOrganizerBot {

    public static void main(String[] args) throws Exception {
        String watchFolder = "C:/Users/HP/Downloads"; // 👀 Watching downloads
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(watchFolder);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        System.out.println("📂 File Organizer Bot is running...");

        while (true) {
            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    String fileName = event.context().toString();
                    File file = new File(watchFolder + "/" + fileName);

                    // Run each file move in a separate thread to avoid blocking
                    new Thread(() -> {
                        try {
                            organizeFile(file);
                        } catch (IOException e) {
                            System.out.println("❌ Error organizing file: " + file.getName());
                        }
                    }).start();
                }
            }
            key.reset();
        }
    }

    private static void organizeFile(File file) throws IOException {
        if (file.isDirectory()) return; // Skip folders

        // Wait until file exists (handle partial downloads)
        int tries = 0;
        while (!file.exists() && tries < 5) {
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            tries++;
        }
        if (!file.exists()) {
            System.out.println("❌ File not found or inaccessible: " + file.getName());
            return;
        }

        String extension = getExtension(file.getName());
        String targetFolder = "Others";

        // More file types support
        switch (extension) {
            case "pdf", "docx", "txt", "xlsx" -> targetFolder = "Documents";
            case "jpg", "jpeg", "png", "gif", "webp" -> targetFolder = "Images";
            case "mp3", "wav" -> targetFolder = "Music";
            case "mp4", "mkv" -> targetFolder = "Videos";
            case "exe", "msi" -> targetFolder = "Software";
        }

        File destDir = new File(file.getParent() + "/" + targetFolder);
        if (!destDir.exists()) destDir.mkdir();

        // Skip if file already in target folder
        if (file.getParentFile().getName().equals(targetFolder)) return;

        File destFile = new File(destDir.getPath() + "/" + file.getName());

        // Handle duplicate file names
        if (destFile.exists()) {
            String baseName = file.getName().substring(0, file.getName().lastIndexOf('.'));
            String ext = file.getName().substring(file.getName().lastIndexOf('.'));
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            destFile = new File(destDir.getPath() + "/" + baseName + "_" + timestamp + ext);
        }

        Files.move(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("✅ Moved " + file.getName() + " → " + targetFolder);
    }

    private static String getExtension(String fileName) {
        int dot = fileName.lastIndexOf(".");
        return (dot == -1) ? "" : fileName.substring(dot + 1).toLowerCase();
    }
}
