package model;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;


/**
 * A utility class providing methods to manage directories.
 * It includes creating a directory if it does not exist, clearing the contents of a directory,
 * and deleting directories recursively.
 */
public class DirectoryCreator {

  /**
   * Ensures that a directory exists at the specified path. If the directory does not exist,
   * it creates the directory along with any necessary but nonexistent parent directories.
   *
   * @param directoryPath The path of the directory to ensure exists.
   * @throws IOException If an I/O error occurs, which is possible because the creation
   *                     of directories involves file system operations that can fail.
   */
  public static void ensureDirectoryExists(String directoryPath) {
    Path path = Paths.get(directoryPath);
    if (!Files.exists(path)) {
      try {
        Files.createDirectories(path);
        System.out.println("Directory created: " + path.toString());
      } catch (IOException e) {
        System.err.println("Failed to create directory: " + directoryPath);
        e.printStackTrace();
      }
    } else {
      System.out.println("Directory already exists: " + path.toString());
    }
  }


  /**
   * Clears all contents of the specified directory without deleting the directory itself.
   * This includes deleting all files and subdirectories within the directory.
   *
   * @param directory The path to the directory to clear.
   * @throws IOException If an I/O error occurs during file deletion or directory traversal.
   */
  public static void clearDirectory(Path directory) throws IOException {
    if (!Files.exists(directory)) {
      System.out.println("Directory does not exist: " + directory);
      return;
    }

    try (Stream<Path> paths = Files.list(directory)) {
      paths.forEach(path -> {
        try {
          if (Files.isRegularFile(path)) {
            Files.delete(path);
          } else if (Files.isDirectory(path)) {
            deleteDirectoryRecursively(path);
          }
        } catch (IOException e) {
          System.err.println("Failed to delete file: " + path + " - " + e.getMessage());
        }
      });
    }
  }

  /**
   * Recursively deletes a directory and all of its contents.
   *
   * @param directory The path to the directory to delete recursively.
   * @throws IOException If an I/O error occurs during file deletion or directory traversal.
   */
  private static void deleteDirectoryRecursively(Path directory) throws IOException {
    Files.walkFileTree(directory, new SimpleFileVisitor<>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.delete(file);
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        Files.delete(dir);
        return FileVisitResult.CONTINUE;
      }
    });
  }

}

