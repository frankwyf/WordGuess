import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class AppPaths {
  private static final String APP_DIR_NAME = "WordGuess";

  private AppPaths() {
  }

  public static Path dataFile(String fileName) {
    return resolveDataDirectory().resolve(fileName);
  }

  public static Path stateFile(String fileName) {
    Path stateDirectory = resolveStateDirectory();
    try {
      Files.createDirectories(stateDirectory);
    } catch (IOException exception) {
      throw new GameException("Failed to create application data directory.");
    }
    return stateDirectory.resolve(fileName);
  }

  private static Path resolveDataDirectory() {
    Path currentDirectoryData = Paths.get("data").toAbsolutePath().normalize();
    if (Files.exists(currentDirectoryData.resolve("words.txt"))) {
      return currentDirectoryData;
    }

    Path appHomeData = resolveAppHome().resolve("data");
    if (Files.exists(appHomeData.resolve("words.txt"))) {
      return appHomeData;
    }

    return currentDirectoryData;
  }

  private static Path resolveStateDirectory() {
    String localAppData = System.getenv("LOCALAPPDATA");
    if (localAppData != null && !localAppData.isBlank()) {
      return Paths.get(localAppData, APP_DIR_NAME);
    }

    return Paths.get(System.getProperty("user.home"), ".wordguess");
  }

  private static Path resolveAppHome() {
    try {
      Path codeSource = Paths.get(
        AppPaths.class.getProtectionDomain().getCodeSource().getLocation().toURI()
      );

      Path location = Files.isRegularFile(codeSource) ? codeSource.getParent() : codeSource;
      if (location == null) {
        return Paths.get("").toAbsolutePath().normalize();
      }

      String folderName = location.getFileName() != null ? location.getFileName().toString() : "";
      if (folderName.equals("app") || folderName.equals("lib")) {
        Path parent = location.getParent();
        if (parent != null) {
          return parent;
        }
      }

      return location;
    } catch (URISyntaxException exception) {
      return Paths.get("").toAbsolutePath().normalize();
    }
  }
}