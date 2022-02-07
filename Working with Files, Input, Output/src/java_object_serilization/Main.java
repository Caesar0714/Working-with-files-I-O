package java_object_serilization;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public final class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: Main [file path]");
            return;
        }

        UdacisearchClient client =
                new UdacisearchClient(
                        "CatFacts LLC",
                        17,
                        8000,
                        5,
                        Instant.now(),
                        Duration.ofDays(180),
                        ZoneId.of("America/Los_Angeles"),
                        "555 Meowmers Ln, Riverside, CA 92501");

        Path outputPath = Path.of(args[0]);

        // Creates a FileOutputStream where objects from ObjectOutputStream are written
        // FileOutputStream file_out = new FileOutputStream(outputPath);

        // Creates the ObjectOutputStream, which is built on top of a file output stream (This means the bytes of serialized data will be written to a file
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(outputPath))) {

            out.writeObject(client);
        }
        System.out.println("Wrote to " + outputPath.toAbsolutePath().toString());


        try(ObjectInputStream in = new ObjectInputStream(Files.newInputStream(outputPath))){
            UdacisearchClient deserialized = (UdacisearchClient) in.readObject();
            System.out.println("Deserialized " + deserialized);
        }
        // TODO: Write the "client" variable to the "outputPath", using a ObjectOutputStream. Then,
        //       read it back and deserialize it using a ObjectInputStream.
    }
}
