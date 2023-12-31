package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class SerializationExercises implements Serializable {
    /*
        Should define the class for the concepts Movie, Theater and Session.
        A session is a play of movie in a theater.
        Create 2 instances of each class and relate among them.
        Serialize to Json all objects and save then in different files.
     */
    public static class Exercise1 {
        public static void main(String[] args) throws FileNotFoundException {
            Movie movie1 = new Movie("The Godfather", "Martin Scorsese", 1956);
            Movie movie2 = new Movie("Corpse Bride", "Tim Burton", 2011);

            Theater theater1 = new Theater("New Orleans Theatre", "New Times Road 2", 12);
            Theater theater2 = new Theater("Times Square Theater", "Times Square Avenue St. 23", 16);


            Session session1 = new Session(10, 12.35, movie1, theater1);
            Session session2 = new Session(5, 10.20, movie1, theater2);

            Gson gson = new Gson();
            String movie1Json = gson.toJson(movie1);
            String movie2Json = gson.toJson(movie2);
            String theater1Json = gson.toJson(theater1);
            String theater2Json = gson.toJson(theater2);
            String session1Json = gson.toJson(session1);
            String session2Json = gson.toJson(session2);
            List<String> objectsToSerialize = new ArrayList<>(List.of(movie1Json, movie2Json, theater1Json,
                    theater2Json, session1Json, session2Json));


            List<String> nameOfFiles = new ArrayList<>(List.of("Movie1", "Movie2",
                    "Theater1", "Theater2", "Session1", "Session2"));
            try {
                for (int outputIndex = 0; outputIndex < 6; outputIndex++) {
                    FileOutputStream outputStream = null;
                    outputStream = new FileOutputStream(nameOfFiles.get(outputIndex)
                            + ".txt");
                    outputStream.write(objectsToSerialize.get(outputIndex).getBytes());
                    outputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /*
        Deserialize the objects created in exercise 1.
        Now serialize them using ObjectOutputStream
     */
    public static class Exercise2 {

        public static void main(String[] args) {

            Gson gson = new Gson();
            String[] fileNames = {"Movie1", "Movie2", "Theater1",
                    "Theater2", "Session1", "Session2"};
            List<Movie> movies = new ArrayList<>();
            List<Session> sessions = new ArrayList<>();
            List<Theater> theaters = new ArrayList<>();

            try {
                for (String file : fileNames) {
                    FileInputStream fileInputStream = new FileInputStream(file + ".txt");
                    InputStreamReader reader = new InputStreamReader(fileInputStream, "UTF-8");


                    if (file.startsWith("Movie1") || file.startsWith("Movie2")) {
                        Movie movie = gson.fromJson(reader, Movie.class);
                        movies.add(movie);
                    } else if (file.startsWith("Theater1") || file.startsWith("Theater2")) {
                        Theater theater = gson.fromJson(reader, Theater.class);
                        theaters.add(theater);
                    } else if (file.startsWith("Session1") || file.startsWith("Session2")) {
                        Session session = gson.fromJson(reader, Session.class);
                        sessions.add(session);
                    }

                    fileInputStream.close();
                    reader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            List<List<?>> listOfObjectsList = new ArrayList<>();
            listOfObjectsList.add(movies);
            listOfObjectsList.add(theaters);
            listOfObjectsList.add(sessions);

            fileNames = new String[]{"Movie", "Session", "Theater"};

            try {
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j < listOfObjectsList.get(i).size(); j++) {
                        FileOutputStream fileOutputStream = new FileOutputStream(fileNames[i] + j + ".bin");
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject(listOfObjectsList.get(i).get(j));
                        objectOutputStream.flush();
                        objectOutputStream.close();
                        fileOutputStream.close();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
       Deserialize the objects from the binary files created in exercise 2.
    */
    public static class Exercise3 {

        public static void main(String[] args) {

            String[] fileNames = {"Movie0", "Movie1", "Theater0",
                    "Theater1", "Session0", "Session1"};

            List<Movie> movies = new ArrayList<>();
            List<Session> sessions = new ArrayList<>();
            List<Theater> theaters = new ArrayList<>();

            try {
                for (String file : fileNames) {
                    FileInputStream fileInputStream = new FileInputStream(file + ".bin");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                    if (file.startsWith("Movie0") || file.startsWith("Movie1")) {
                        movies.add((Movie) objectInputStream.readObject());
                    } else if (file.startsWith("Theater0") || file.startsWith("Theater1")) {
                        theaters.add((Theater) objectInputStream.readObject());
                    } else if (file.startsWith("Session0") || file.startsWith("Session1")) {
                        sessions.add((Session) objectInputStream.readObject());
                    }
                    fileInputStream.close();
                    objectInputStream.close();
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
