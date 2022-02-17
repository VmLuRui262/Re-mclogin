package org.vmlurui.remclogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.mindrot.jbcrypt.BCrypt;

public class RegisteredPlayersJson {
    private static final File REGISTERED_PLAYERS = new File("registered-players.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static JsonArray registeredPlayers = new JsonArray();

    public static boolean isPlayerRegistered(String username) {
        return findPlayer(username) != null;
    }

    public static boolean isCorrectPassword(String username, String password) {
        JsonObject player = findPlayer(username);
        return player != null && BCrypt.checkpw(password, player.get("password").getAsString());
    }

    private static JsonObject findPlayer(String userName) {
        JsonObject player = null;
        if (registeredPlayers.size() == 0) {
            return null;
        }
        for (int i = 0; i < registeredPlayers.size(); i++) {
            JsonObject playerObjectIndex = registeredPlayers.get(i).getAsJsonObject();
            if (playerObjectIndex.get("name").getAsString().equals(userName)) {
                player = playerObjectIndex;
                break;
            }
        }
        return player;
    }

    public static void save(String UUID, String userName, String password) {
        JsonObject player = new JsonObject();
        player.addProperty("uuid", UUID);
        player.addProperty("name", userName);
        player.addProperty("password", BCrypt.hashpw(password, BCrypt.gensalt()));
        registeredPlayers.add(player);
        try {
            //noinspection UnstableApiUsage
            BufferedWriter bufferedWriter = Files.newWriter(REGISTERED_PLAYERS, StandardCharsets.UTF_8);
            bufferedWriter.write(gson.toJson(registeredPlayers));
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        if (!REGISTERED_PLAYERS.exists()) {
            return;
        }
        try {
            //noinspection UnstableApiUsage
            BufferedReader bufferedReader = Files.newReader(REGISTERED_PLAYERS, StandardCharsets.UTF_8);
            registeredPlayers = gson.fromJson(bufferedReader, JsonArray.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removePlayer(String userName) {
        registeredPlayers.remove(findPlayer(userName));
    }
}

